package com.go2wheel.katharsis.rest.manufacturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.Manufacturer;
import com.go2wheel.repository.ManufacturerRepository;

@Component
public class ManufacturerTUtil {
	
	@Autowired
	private ManufacturerRepository manufacturerReop;
	
	public Manufacturer createOne() {
		return createOne("kawasaki");
	}
	
	public Manufacturer createOne(String name) {
		Manufacturer mf = new Manufacturer();
		mf.setName(name);
		return manufacturerReop.save(mf);
	}
}
