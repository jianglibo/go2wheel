package com.go2wheel.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-21T09:09:33.027+0800")
@StaticMetamodel(Manufacturer.class)
public class Manufacturer_ extends BaseEntity_ {
	public static volatile SingularAttribute<Manufacturer, String> name;
	public static volatile SingularAttribute<Manufacturer, String> nationality;
	public static volatile ListAttribute<Manufacturer, MtSeries> mtSerieses;
	public static volatile SingularAttribute<Manufacturer, String> founder;
	public static volatile SingularAttribute<Manufacturer, Date> foundTime;
	public static volatile SingularAttribute<Manufacturer, String> logo;
	public static volatile SingularAttribute<Manufacturer, String> legend;
}
