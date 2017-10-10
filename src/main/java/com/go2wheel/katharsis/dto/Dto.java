package com.go2wheel.katharsis.dto;

import java.util.Set;

public interface Dto {
	Long getId();
	String getDtoApplyTo();
	String getDtoAction();
	Set<String> calDtoApplyToSet();
}
