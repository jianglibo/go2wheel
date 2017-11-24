package com.go2wheel.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-30T13:49:57.087+0800")
@StaticMetamodel(OwnerShip.class)
public class OwnerShip_ extends BaseEntity_ {
	public static volatile SingularAttribute<OwnerShip, BootUser> owner;
	public static volatile SingularAttribute<OwnerShip, MtModel> mtModel;
	public static volatile SingularAttribute<OwnerShip, Date> startDate;
	public static volatile SingularAttribute<OwnerShip, Date> endDate;
}
