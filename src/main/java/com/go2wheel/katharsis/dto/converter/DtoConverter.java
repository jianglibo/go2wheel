package com.go2wheel.katharsis.dto.converter;

public interface DtoConverter<E, D> {
	
	public static enum Scenario {
		NEW, MODIFY, UNKNOWN, IN_RELATION, FIND_ONE, FIND_LIST, RELATION_LIST
	}
	
	D entity2Dto(E entity, Scenario scenario);
}
