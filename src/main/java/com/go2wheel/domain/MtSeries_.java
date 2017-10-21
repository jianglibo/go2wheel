package com.go2wheel.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-17T15:47:28.045+0800")
@StaticMetamodel(MtSeries.class)
public class MtSeries_ extends BaseEntity_ {
	public static volatile SingularAttribute<MtSeries, String> name;
	public static volatile SingularAttribute<MtSeries, String> description;
	public static volatile ListAttribute<MtSeries, MtModel> models;
	public static volatile SingularAttribute<MtSeries, Manufacturer> manufacturer;
}
