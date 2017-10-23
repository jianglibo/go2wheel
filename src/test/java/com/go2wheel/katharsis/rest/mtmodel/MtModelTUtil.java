package com.go2wheel.katharsis.rest.mtmodel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.MtModel;
import com.go2wheel.domain.MtSeries;
import com.go2wheel.katharsis.rest.mtseries.MtSeriesTUtil;
import com.go2wheel.repository.MtModelRepository;

@Component
public class MtModelTUtil {
	
	@Autowired
	private MtModelRepository mtModelRepo;
	
	@Autowired
	private MtSeriesTUtil msUtil;
	
	
	public MtModel createOne(MtSeries ms) {
		return createOne(ms, "versys");
	}
	
	public MtModel createOne(MtSeries ms, String name) {
		MtModel mm = new MtModel();
		mm.setName(name);
		mm.setDescription("dd");
		mm.setMtSeries(ms);
		return mtModelRepo.save(mm);
	}
	
	public MtModel createOne(String name) {
		MtSeries ms = msUtil.createOne("versys");
		return createOne(ms, name);
	}
	
	public List<MtModel> createMany(MtSeries ms, int num) {
		return IntStream.rangeClosed(1, num).mapToObj(i -> {
			return createOne(ms, "versys-" + i);
		}).collect(Collectors.toList());
	}
	
	public List<MtModel> createMany(int num) {
		MtSeries ms = msUtil.createOne("versys");
		return createMany(ms, num);
	}

}
