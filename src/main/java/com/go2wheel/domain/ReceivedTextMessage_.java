﻿package com.go2wheel.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-29T08:52:34.125+0800")
@StaticMetamodel(ReceivedTextMessage.class)
public class ReceivedTextMessage_ extends BaseEntity_ {
	public static volatile SingularAttribute<ReceivedTextMessage, String> toUserName;
	public static volatile SingularAttribute<ReceivedTextMessage, String> fromUserName;
	public static volatile SingularAttribute<ReceivedTextMessage, Long> createTime;
	public static volatile SingularAttribute<ReceivedTextMessage, String> content;
	public static volatile SingularAttribute<ReceivedTextMessage, String> msgType;
	public static volatile SingularAttribute<ReceivedTextMessage, String> msgId;
}
