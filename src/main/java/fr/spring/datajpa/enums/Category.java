package fr.spring.datajpa.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
	
	MICRO_URBAINES,
	MINI_CITADINES,
	CITADINES_POLYVALENTES,
	COMPACTES,
	BERLINE_S,
	BERLINE_M,
	BERLINE_L,
	SUV;
	
	@JsonValue
	public String getValue() {
		return this.name();
	}
	
	@JsonCreator
	public static Role fromValue(String value) {
		for(Role r: Role.values()) {
			if(r.name().equals(value)) {
				return r;
			}
		}
		throw new IllegalArgumentException("Unexpected value '" + value + "'");
	}
	
}
