package com.go2wheel.domain;

import com.go2wheel.constant.Market;
import com.go2wheel.constant.EnginType.CoolType;
import com.go2wheel.constant.EnginType.CylinderHead;
import com.go2wheel.constant.EnginType.EnginePosition;
import com.go2wheel.constant.EnginType.FinalDriveType;
import com.go2wheel.constant.EnginType.IgnitionType;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-29T08:52:34.082+0800")
@StaticMetamodel(MtModel.class)
public class MtModel_ extends BaseEntity_ {
	public static volatile SingularAttribute<MtModel, String> name;
	public static volatile SingularAttribute<MtModel, MtSeries> mtSeries;
	public static volatile SetAttribute<MtModel, Accessory> accessories;
	public static volatile SingularAttribute<MtModel, Integer> year;
	public static volatile SingularAttribute<MtModel, Market> market;
	public static volatile SingularAttribute<MtModel, String> frameType;
	public static volatile SingularAttribute<MtModel, Integer> groundClearance;
	public static volatile SingularAttribute<MtModel, String> description;
	public static volatile SingularAttribute<MtModel, Integer> cylinders;
	public static volatile SingularAttribute<MtModel, Integer> displacement;
	public static volatile SingularAttribute<MtModel, Integer> bore;
	public static volatile SingularAttribute<MtModel, Integer> stroke;
	public static volatile SingularAttribute<MtModel, Integer> compressionRatio;
	public static volatile SingularAttribute<MtModel, Integer> valvesPerCylinder;
	public static volatile SingularAttribute<MtModel, String> induction;
	public static volatile SingularAttribute<MtModel, Integer> transmissionSteps;
	public static volatile SingularAttribute<MtModel, String> transmission;
	public static volatile SingularAttribute<MtModel, String> finalDrive;
	public static volatile SingularAttribute<MtModel, FinalDriveType> finalDriveType;
	public static volatile SingularAttribute<MtModel, CoolType> coolType;
	public static volatile SingularAttribute<MtModel, CylinderHead> cylinderHead;
	public static volatile SingularAttribute<MtModel, EnginePosition> enginPosition;
	public static volatile SingularAttribute<MtModel, IgnitionType> ignitionType;
	public static volatile SingularAttribute<MtModel, String> ignition;
	public static volatile SingularAttribute<MtModel, String> frontTire;
	public static volatile SingularAttribute<MtModel, String> rearTire;
	public static volatile SingularAttribute<MtModel, Boolean> frontAbs;
	public static volatile SingularAttribute<MtModel, Boolean> rearAbs;
	public static volatile SingularAttribute<MtModel, String> frontBrake;
	public static volatile SingularAttribute<MtModel, String> rearBrake;
	public static volatile SingularAttribute<MtModel, String> frontSuspension;
	public static volatile SingularAttribute<MtModel, String> rearSuspension;
	public static volatile SingularAttribute<MtModel, Integer> rake;
	public static volatile SingularAttribute<MtModel, Integer> trail;
	public static volatile SingularAttribute<MtModel, Integer> wheelbase;
	public static volatile SingularAttribute<MtModel, Integer> seatHeight;
	public static volatile SingularAttribute<MtModel, Integer> frontWheelTravel;
	public static volatile SingularAttribute<MtModel, Integer> rearWheelTravel;
	public static volatile SingularAttribute<MtModel, Integer> fuelCapacity;
	public static volatile SingularAttribute<MtModel, Integer> overallLength;
	public static volatile SingularAttribute<MtModel, Integer> widthHeight;
	public static volatile SingularAttribute<MtModel, Integer> overallHeight;
	public static volatile SingularAttribute<MtModel, Integer> curbWeight;
	public static volatile SingularAttribute<MtModel, String> emissions;
	public static volatile ListAttribute<MtModel, String> colors;
}
