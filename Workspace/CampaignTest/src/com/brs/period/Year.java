package com.brs.period;

//Period year values:
public enum Year {
	
	FORTY("1940"), 
	FORTY_ONE("1941"), 
	FORTY_TWO("1942"), 
	FORTY_THREE("1943"), 
	FORTY_FOUR("1944"), 
	FORTY_FIVE("1945");
	
	private final String year; //name of chosen year
	private Year(String year) { this.year = year; } //constructor sets name of chosen year
	@Override 
	public String toString() { return year; }  //return chosen year
		
}
