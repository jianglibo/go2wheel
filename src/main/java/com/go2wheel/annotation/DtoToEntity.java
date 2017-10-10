package com.go2wheel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.go2wheel.domain.BaseEntity;

@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface DtoToEntity {
	Class<? extends BaseEntity> entityClass();
}
