package com.go2wheel.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.go2wheel.annotation.EntityToDtoIgnore;

@Entity
@Table(name = "manufactory")
public class Manufacturer extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8189805474312842749L;

	private String name;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private Map<String, String> websites;
	
	private String founder;
	
	@Temporal(TemporalType.DATE)
	private Date foundTime;
	
	private String nationality;
	
	private String logo;
	
	@Lob
	private String legend;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="manufacturer")
	@EntityToDtoIgnore
	private List<MtSeries> mtSerieses = new ArrayList<>();

	public String getName() {
		return name;
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

	public void setName(String name) {
		this.name = name;
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

	public List<MtSeries> getMtSerieses() {
		return mtSerieses;
	}

	public void setMtSerieses(List<MtSeries> mtSerieses) {
		this.mtSerieses = mtSerieses;
	}

	public Map<String, String> getWebsites() {
		return websites;
	}

	public void setWebsites(Map<String, String> websites) {
		this.websites = websites;
	}
}
