package com.go2wheel.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-23T14:35:05.900+0800")
@StaticMetamodel(BootUser.class)
public class BootUser_ {
	public static volatile CollectionAttribute<BootUser, Object> posts;
	public static volatile CollectionAttribute<BootUser, Object> postShares;
	public static volatile CollectionAttribute<BootUser, Object> sentApproves;
	public static volatile CollectionAttribute<BootUser, Object> receivedApproves;
	public static volatile CollectionAttribute<BootUser, Object> notifies;
	public static volatile CollectionAttribute<BootUser, Object> bootGroups;
	public static volatile CollectionAttribute<BootUser, Object> ownedGroups;
	public static volatile CollectionAttribute<BootUser, Object> followers;
	public static volatile CollectionAttribute<BootUser, Object> followeds;
	public static volatile CollectionAttribute<BootUser, Object> media;
	public static volatile CollectionAttribute<BootUser, Object> unreads;
	public static volatile CollectionAttribute<BootUser, Object> roles;
}
