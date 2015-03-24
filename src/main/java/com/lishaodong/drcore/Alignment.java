package com.lishaodong.drcore;

public enum Alignment {
	LAWFUL("lawful"),
	NEUTRAL("neutral"),
	CHAOTIC("chaotic"),
	NONE("none");
	
	public String name;
	
	Alignment(String name){
		this.name = name;
	}
}
