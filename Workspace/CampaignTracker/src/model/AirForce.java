package model;

import java.util.ArrayList;
import java.util.List;

public final class AirForce {
	
	private final String name; //name of air force
	private final boolean hasHomeAdv; //if air force has home adv status
	private final List<Plane>planes; //planes available to air force
	
	//////////////private AirForce() {} //blank constructor
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++MAKE ALL PROPERTIES FINAL TOO!! 
	public AirForce(String name, boolean hasHomeAdv, List<Plane>planes){
		this.name = name;
		this.hasHomeAdv = hasHomeAdv;
		this.planes = new ArrayList<Plane>(planes);
	}
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	/*
	//builder class:
	public static class AirForceBuilder {
		
		//create air force:
		private AirForce airForce = new AirForce();
		
		//set air force name:
		public AirForceBuilder setAirForceName(String name) {
			airForce.name = name;
			return this;
		}
		
		//set home advantage status:
		public AirForceBuilder setHasHomeAdv(boolean bool) {
			airForce.hasHomeAdv = bool;
			return this;
		}
		
		//set planes:
		public AirForceBuilder setPlanes(List<Plane>planes) { //+++++++++++++++++MAKE STRONGER :P
			airForce.planes = new ArrayList<Plane>(planes);
			return this;
		}
		
		//return built event air force:
		public AirForce build() { return airForce; } 
	}
	*/
	
	public String getAirForceName() { return name; } //get air force name
	public boolean getHasHomeAdv() { return hasHomeAdv; } //get home adv status
	
	public List<Plane> getAirForcePlanes() { //+++++++++++++++++MAKE STRONGER :P
		return new ArrayList<Plane>(planes);
	} 
	
	@Override
	public String toString() {
		return "AirForce [name=" + name + ", hasHomeAdv=" + hasHomeAdv + ", planes=" + planes + "]";
	}
	
	
	
	
	
	
	
	
	
	
	// https://stackoverflow.com/questions/1488472/best-practices-throwing-exceptions-from-properties
	
	
	
	
}
