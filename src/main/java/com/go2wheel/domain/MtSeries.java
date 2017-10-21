package com.go2wheel.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.go2wheel.annotation.ToDtoIgnore;

@Entity
@Table(name = "mtseries")
public class MtSeries extends BaseEntity {
	
	private static String[] initProperties = new String[] {"name", "description"};

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
	@ToDtoIgnore
	private List<MtModel> models = new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull
	@ToDtoIgnore
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

	@Override
	public String[] propertiesOnCreating() {
		return MtSeries.initProperties;
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
