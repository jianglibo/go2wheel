package com.go2wheel.katharsis.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.go2wheel.annotation.DtoToEntity;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.MtSeries;

import io.katharsis.resource.annotations.JsonApiRelation;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.LookupIncludeBehavior;
import io.katharsis.resource.annotations.SerializeType;

@JsonApiResource(type = JsonApiResourceNames.MT_SERIES)
@DtoToEntity(entityClass=MtSeries.class)
public class MtSeriesDto extends DtoBase {
	
	@NotNull
	private String name;
	
	private String description;
	
	@NotNull
	@JsonApiRelation(lookUp=LookupIncludeBehavior.AUTOMATICALLY_WHEN_NULL,serialize=SerializeType.LAZY, opposite="mtSerieses")
	private ManufacturerDto manufacturer;
	
	
	@JsonApiRelation(lookUp=LookupIncludeBehavior.NONE,serialize=SerializeType.LAZY, opposite="mtSeries")
	private List<MtModelDto> models;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public List<MtModelDto> getModels() {
		return models;
	}

	public void setModels(List<MtModelDto> models) {
		this.models = models;
	}

	@Override
	public String toString() {
		return String.format("[%s,%s]", getId(), getName());
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ManufacturerDto getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(ManufacturerDto manufacturer) {
		this.manufacturer = manufacturer;
	}
}
