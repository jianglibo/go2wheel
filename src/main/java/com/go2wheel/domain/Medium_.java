﻿package com.go2wheel.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-24T16:07:25.030+0800")
@StaticMetamodel(Medium.class)
public class Medium_ extends BaseEntity_ {
	public static volatile SingularAttribute<Medium, String> originName;
	public static volatile SingularAttribute<Medium, BootUser> creator;
	public static volatile SingularAttribute<Medium, String> url;
	public static volatile SingularAttribute<Medium, String> contentType;
	public static volatile SingularAttribute<Medium, String> localPath;
	public static volatile SingularAttribute<Medium, Long> size;
	public static volatile ListAttribute<Medium, Post> posts;
}
