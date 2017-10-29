package com.go2wheel.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-29T08:52:33.960+0800")
@StaticMetamodel(BaseEntity.class)
public class BaseEntity_ {
	public static volatile SingularAttribute<BaseEntity, Long> id;
	public static volatile SingularAttribute<BaseEntity, Integer> version;
	public static volatile SingularAttribute<BaseEntity, Date> createdAt;
	public static volatile SingularAttribute<BaseEntity, Boolean> archived;
}
