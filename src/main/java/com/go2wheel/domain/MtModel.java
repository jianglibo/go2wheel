package com.go2wheel.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.go2wheel.constant.EnginType;
import com.go2wheel.constant.Market;

@Entity
@Table(name = "mtmodel")
public class MtModel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	private MtSeries mtSeries;
	
	@Enumerated(EnumType.STRING)
	private Market market = Market.ALL;
	
	private String description;
	
	private int cylinders;
	
	private int enginCapacity;
	
	private int bore; // multiply by 10
	private int stroke; // multiply by 10
	
	private int compressionRatio; // multiply by 10
	
	private int valvesPerCylinder;
	
	private String induction;
	
	private int transmissionSteps;
	
	private String transmission;
	
	private String finalDrive;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private EnginType.FinalDriveType finalDriveType;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private EnginType.CoolType coolType;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private EnginType.CylinderHead cylinderHead;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private EnginType.EnginePosition enginPosition;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private EnginType.IgnitionType ignitionType;
	
	private String frontTire;
	private String rearTire;
	
	private boolean frontAbs;
	private boolean rearAbs;
	
	private String frontBrake;
	private String rearBrake;
	
	private String frontSuspension;
	private String rearSuspension;
	
	private int casterAngle; // multiply by 10
	
	private int trail; // multiply by 10
	
	private int wheelbase; 
	private int seatHeight;
	
	private int fuelCapacity;
	
	private int curbWeight;
	private String emissions;
	
	private String colors;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}


	public int getCylinders() {
		return cylinders;
	}

	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}

	public int getEnginCapacity() {
		return enginCapacity;
	}

	public void setEnginCapacity(int enginCapacity) {
		this.enginCapacity = enginCapacity;
	}

	public int getBore() {
		return bore;
	}

	public void setBore(int bore) {
		this.bore = bore;
	}

	public int getStroke() {
		return stroke;
	}

	public void setStroke(int stroke) {
		this.stroke = stroke;
	}

	public int getCompressionRatio() {
		return compressionRatio;
	}

	public void setCompressionRatio(int compressionRatio) {
		this.compressionRatio = compressionRatio;
	}

	public int getValvesPerCylinder() {
		return valvesPerCylinder;
	}

	public void setValvesPerCylinder(int valvesPerCylinder) {
		this.valvesPerCylinder = valvesPerCylinder;
	}

	public String getInduction() {
		return induction;
	}

	public void setInduction(String induction) {
		this.induction = induction;
	}

	public int getTransmissionSteps() {
		return transmissionSteps;
	}

	public void setTransmissionSteps(int transmissionSteps) {
		this.transmissionSteps = transmissionSteps;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getFinalDrive() {
		return finalDrive;
	}

	public void setFinalDrive(String finalDrive) {
		this.finalDrive = finalDrive;
	}

	public EnginType.FinalDriveType getFinalDriveType() {
		return finalDriveType;
	}

	public void setFinalDriveType(EnginType.FinalDriveType finalDriveType) {
		this.finalDriveType = finalDriveType;
	}

	public EnginType.CoolType getCoolType() {
		return coolType;
	}

	public void setCoolType(EnginType.CoolType coolType) {
		this.coolType = coolType;
	}

	public EnginType.CylinderHead getCylinderHead() {
		return cylinderHead;
	}

	public void setCylinderHead(EnginType.CylinderHead cylinderHead) {
		this.cylinderHead = cylinderHead;
	}

	public EnginType.EnginePosition getEnginPosition() {
		return enginPosition;
	}

	public void setEnginPosition(EnginType.EnginePosition enginPosition) {
		this.enginPosition = enginPosition;
	}

	public EnginType.IgnitionType getIgnitionType() {
		return ignitionType;
	}

	public void setIgnitionType(EnginType.IgnitionType ignitionType) {
		this.ignitionType = ignitionType;
	}

	public String getFrontTire() {
		return frontTire;
	}

	public void setFrontTire(String frontTire) {
		this.frontTire = frontTire;
	}

	public String getRearTire() {
		return rearTire;
	}

	public void setRearTire(String rearTire) {
		this.rearTire = rearTire;
	}

	public boolean isFrontAbs() {
		return frontAbs;
	}

	public void setFrontAbs(boolean frontAbs) {
		this.frontAbs = frontAbs;
	}

	public boolean isRearAbs() {
		return rearAbs;
	}

	public void setRearAbs(boolean rearAbs) {
		this.rearAbs = rearAbs;
	}

	public String getFrontBrake() {
		return frontBrake;
	}

	public void setFrontBrake(String frontBrake) {
		this.frontBrake = frontBrake;
	}

	public String getRearBrake() {
		return rearBrake;
	}

	public void setRearBrake(String rearBrake) {
		this.rearBrake = rearBrake;
	}

	public String getFrontSuspension() {
		return frontSuspension;
	}

	public void setFrontSuspension(String frontSuspension) {
		this.frontSuspension = frontSuspension;
	}

	public String getRearSuspension() {
		return rearSuspension;
	}

	public void setRearSuspension(String rearSuspension) {
		this.rearSuspension = rearSuspension;
	}

	public int getCasterAngle() {
		return casterAngle;
	}

	public void setCasterAngle(int casterAngle) {
		this.casterAngle = casterAngle;
	}

	public int getTrail() {
		return trail;
	}

	public void setTrail(int trail) {
		this.trail = trail;
	}

	public int getWheelbase() {
		return wheelbase;
	}

	public void setWheelbase(int wheelbase) {
		this.wheelbase = wheelbase;
	}

	public int getSeatHeight() {
		return seatHeight;
	}

	public void setSeatHeight(int seatHeight) {
		this.seatHeight = seatHeight;
	}

	public int getFuelCapacity() {
		return fuelCapacity;
	}

	public void setFuelCapacity(int fuelCapacity) {
		this.fuelCapacity = fuelCapacity;
	}

	public int getCurbWeight() {
		return curbWeight;
	}

	public void setCurbWeight(int curbWeight) {
		this.curbWeight = curbWeight;
	}

	public String getEmissions() {
		return emissions;
	}

	public void setEmissions(String emissions) {
		this.emissions = emissions;
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String[] propertiesOnCreating() {
		return null;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public MtSeries getMtSeries() {
		return mtSeries;
	}

	public void setMtSeries(MtSeries mtSeries) {
		this.mtSeries = mtSeries;
	}

	
}
