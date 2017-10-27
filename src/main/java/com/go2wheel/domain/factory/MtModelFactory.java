package com.go2wheel.domain.factory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.api.util.SizeConverter;
import com.go2wheel.domain.MtModel;
import com.go2wheel.domain.MtSeries;
import com.go2wheel.facade.MtSeriesFacadeRepository;
import com.go2wheel.katharsis.exception.MissingRequiredFieldException;

@Component
public class MtModelFactory {
	
	private static String[] mtModelFields = Stream.of(MtModel.class.getDeclaredFields()).map(f -> f.getName()).toArray(length -> new String[length]);
	
	private static char zero = '0';
	private static char nine = '9';
	private static char dot = '.';
	
	@Autowired
	private MtSeriesFacadeRepository mtSeriesRepo;
	
	private static Logger logger = LoggerFactory.getLogger(MtModelFactory.class);
	
	public MtModel fromPropertyFile(InputStream inStream) throws IOException {
		MtModel mm = new MtModel();
		Properties prop = new Properties();
		prop.load(inStream);
		inStream.close();
		Stream.of(mtModelFields).forEach(fn -> {
			if (prop.containsKey(fn)) {
				Field f = null;
				Class<?> tp = null;
				try {
					f = MtModel.class.getDeclaredField(fn);
					tp = f.getType();
					String v = prop.getProperty(fn);
					Object o = null;
					if (tp == int.class) {
						o = SizeConverter.toOneTenth(v);
					} else if(tp == MtSeries.class) {
						MtSeries ms = mtSeriesRepo.findByName(v);
						if (ms == null) {
							throw new MissingRequiredFieldException("mtseries with name " + fn + " cannot be found.") ;
						}
						o = ms;
					} else if(Enum.class.isAssignableFrom(tp)) {
						o = getEnumFromString(tp, v);
					} else if (List.class.isAssignableFrom(tp)) {
						String[] ss = v.trim().split(",");
						o = Arrays.asList(ss);
					} else {
						o = v;
					}
					BeanUtils.setProperty(mm, fn, o);
				} catch (IllegalAccessException | InvocationTargetException | NoSuchFieldException | SecurityException | ConversionException e ) {
					logger.info("filed '{}' of type {} cannot be processed.", tp.getName());
					e.printStackTrace();
				}
			}
		});
		return mm;
	}
	
	
	@SuppressWarnings("unchecked")
	public <T extends Enum<T>> T getEnumFromString(Class<?> c, String string) {
	    if( c != null && string != null ) {
	        try {
	            return Enum.valueOf((Class<T>) c, string.trim().toUpperCase());
	        } catch(IllegalArgumentException ex) {
	        }
	    }
	    return null;
	}

}
