package com.go2wheel.katharsis.rest.mtseries;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.Manufacturer;
import com.go2wheel.domain.MtSeries;
import com.go2wheel.katharsis.rest.manufacturer.ManufacturerTUtil;
import com.go2wheel.repository.MtSeriesRepository;

@Component
public class MtSeriesTUtil {
	
	@Autowired
	private MtSeriesRepository mtSeriesReop;
	
	@Autowired
	private ManufacturerTUtil mfUtil;
	
	public MtSeries createOne(Manufacturer mf) {
		return createOne(mf, "kawasaki");
	}
	
	public MtSeries createOne(Manufacturer mf, String name) {
		MtSeries ms = new MtSeries();
		ms.setName(name);
		ms.setDescription("dd");
		ms.setManufacturer(mf);
		return mtSeriesReop.save(ms);
	}
	
	public MtSeries createOne(String name) {
		Manufacturer mf = mfUtil.createOne();
		MtSeries ms = new MtSeries();
		ms.setName(name);
		ms.setDescription("dd");
		ms.setManufacturer(mf);
		return mtSeriesReop.save(ms);
	}

	
	public List<MtSeries> createMany(Manufacturer mf, int num) {
		return IntStream.rangeClosed(1, num).mapToObj(i -> {
			return createOne(mf, "kawasaki-" + i);
		}).collect(Collectors.toList());
	}
}
