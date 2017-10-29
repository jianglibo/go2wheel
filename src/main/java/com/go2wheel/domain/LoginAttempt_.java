package com.go2wheel.domain;

import com.go2wheel.domain.ThirdPartLogin.Provider;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-29T08:52:34.022+0800")
@StaticMetamodel(LoginAttempt.class)
public class LoginAttempt_ extends BaseEntity_ {
	public static volatile SingularAttribute<LoginAttempt, String> username;
	public static volatile SingularAttribute<LoginAttempt, String> password;
	public static volatile SingularAttribute<LoginAttempt, String> remoteAddress;
	public static volatile SingularAttribute<LoginAttempt, String> sessionId;
	public static volatile SingularAttribute<LoginAttempt, Provider> provider;
	public static volatile SingularAttribute<LoginAttempt, Boolean> success;
}
