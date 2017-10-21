package com.go2wheel.katharsis.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.go2wheel.annotation.DtoToEntity;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.Tag;

import io.katharsis.resource.annotations.JsonApiResource;

@JsonApiResource(type = JsonApiResourceNames.TAG)
@DtoToEntity(entityClass=Tag.class)
public class TagDto extends DtoBase {
	
	@NotNull
	@Size(min=3, max=30)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("[%s,%s]", getId(), getName());
	}

}
