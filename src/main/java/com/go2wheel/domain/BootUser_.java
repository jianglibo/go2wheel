﻿package com.go2wheel.domain;

import com.go2wheel.domain.BootUser.Gender;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-07T09:18:57.440+0800")
@StaticMetamodel(BootUser.class)
public class BootUser_ extends BaseEntity_ {
	public static volatile SingularAttribute<BootUser, String> displayName;
	public static volatile SingularAttribute<BootUser, String> avatar;
	public static volatile SingularAttribute<BootUser, Boolean> emailVerified;
	public static volatile SingularAttribute<BootUser, Boolean> mobileVerified;
	public static volatile ListAttribute<BootUser, Post> posts;
	public static volatile ListAttribute<BootUser, OwnerShip> ownerships;
	public static volatile SingularAttribute<BootUser, Gender> gender;
	public static volatile ListAttribute<BootUser, PostShare> postShares;
	public static volatile ListAttribute<BootUser, Approve> sentApproves;
	public static volatile ListAttribute<BootUser, Approve> receivedApproves;
	public static volatile ListAttribute<BootUser, MessageNotify> notifies;
	public static volatile SingularAttribute<BootUser, String> name;
	public static volatile SingularAttribute<BootUser, String> email;
	public static volatile SingularAttribute<BootUser, String> password;
	public static volatile SingularAttribute<BootUser, String> mobile;
	public static volatile SingularAttribute<BootUser, Boolean> accountNonExpired;
	public static volatile SingularAttribute<BootUser, Boolean> accountNonLocked;
	public static volatile SingularAttribute<BootUser, Boolean> credentialsNonExpired;
	public static volatile SingularAttribute<BootUser, Boolean> enabled;
	public static volatile SingularAttribute<BootUser, String> openId;
	public static volatile SingularAttribute<BootUser, String> city;
	public static volatile SingularAttribute<BootUser, String> country;
	public static volatile SingularAttribute<BootUser, String> language;
	public static volatile SingularAttribute<BootUser, String> province;
	public static volatile ListAttribute<BootUser, GroupUserRelation> bootGroups;
	public static volatile ListAttribute<BootUser, BootGroup> ownedGroups;
	public static volatile ListAttribute<BootUser, FollowRelation> followers;
	public static volatile ListAttribute<BootUser, FollowRelation> followeds;
	public static volatile ListAttribute<BootUser, Medium> media;
	public static volatile ListAttribute<BootUser, Unread> unreads;
	public static volatile SetAttribute<BootUser, Role> roles;
}
