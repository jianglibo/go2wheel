package com.go2wheel.katharsis.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import com.go2wheel.annotation.DtoToEntity;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.Manufacturer;

import io.katharsis.resource.annotations.JsonApiRelation;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.LookupIncludeBehavior;
import io.katharsis.resource.annotations.SerializeType;

@JsonApiResource(type = JsonApiResourceNames.MANUFACTURER)
@DtoToEntity(entityClass=Manufacturer.class)
public class ManufacturerDto extends DtoBase {
	
	@NotNull
	private String name;
	
	private Map<String, String> websites;
	
	private String founder;
	
	private Date foundTime;
	
	private String nationality;
	
	private String logo;
	
	private String slogan;
	
	@Lob
	private String legend;
	
	/**
	 * 
	 */
    @JsonApiRelation(lookUp=LookupIncludeBehavior.NONE, serialize=SerializeType.LAZY, opposite="manufacturer")
    private List<MtSeriesDto> mtSerieses = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}

	public Date getFoundTime() {
		return foundTime;
	}

	public void setFoundTime(Date foundTime) {
		this.foundTime = foundTime;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLegend() {
		return legend;
	}

	public void setLegend(String legend) {
		this.legend = legend;
	}

	public Map<String, String> getWebsites() {
		return websites;
	}

	public void setWebsites(Map<String, String> websites) {
		this.websites = websites;
	}

	public List<MtSeriesDto> getMtSerieses() {
		return mtSerieses;
	}

	public void setMtSerieses(List<MtSeriesDto> mtSerieses) {
		this.mtSerieses = mtSerieses;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

}
