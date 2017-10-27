package com.go2wheel.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.go2wheel.annotation.DtoToEntityIgnore;
import com.go2wheel.annotation.EntityToDtoIgnore;

@Entity
@Table(name = "mtseries", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "MANUFACTURER_ID"})})
public class MtSeries extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotEmpty
	private String name;
	
	@NotNull
	@NotEmpty
	private String description;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="mtSeries")
	@EntityToDtoIgnore
	@DtoToEntityIgnore
	private List<MtModel> models = new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull
	@EntityToDtoIgnore
	@DtoToEntityIgnore
	@JoinColumn(name = "MANUFACTURER_ID")
	private Manufacturer manufacturer;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MtModel> getModels() {
		return models;
	}

	public void setModels(List<MtModel> models) {
		this.models = models;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
}
