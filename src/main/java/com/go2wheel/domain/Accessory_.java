package com.go2wheel.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-07T09:18:57.163+0800")
@StaticMetamodel(Accessory.class)
public class Accessory_ extends BaseEntity_ {
	public static volatile SingularAttribute<Accessory, String> cat;
	public static volatile SingularAttribute<Accessory, String> name;
	public static volatile SingularAttribute<Accessory, String> description;
	public static volatile SetAttribute<Accessory, Tag> tags;
	public static volatile SetAttribute<Accessory, MtModel> mtModels;
}
