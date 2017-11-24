package com.go2wheel.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ownership")
public class OwnerShip extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private BootUser owner;
	
	@OneToOne
	@JoinColumn(name = "MODEL_ID")
	private MtModel mtModel;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;

	public BootUser getOwner() {
		return owner;
	}

	public void setOwner(BootUser owner) {
		this.owner = owner;
	}

	public MtModel getMtModel() {
		return mtModel;
	}

	public void setMtModel(MtModel mtModel) {
		this.mtModel = mtModel;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
