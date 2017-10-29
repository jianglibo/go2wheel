package com.go2wheel.domain;

import com.go2wheel.eu.ApproveState;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-29T08:52:33.950+0800")
@StaticMetamodel(Approve.class)
public class Approve_ extends BaseEntity_ {
	public static volatile SingularAttribute<Approve, BootUser> requester;
	public static volatile SingularAttribute<Approve, BootUser> receiver;
	public static volatile SingularAttribute<Approve, String> targetType;
	public static volatile SingularAttribute<Approve, Long> targetId;
	public static volatile SingularAttribute<Approve, ApproveState> state;
	public static volatile SingularAttribute<Approve, String> forwhat;
	public static volatile SingularAttribute<Approve, String> descriptionTpl;
}
