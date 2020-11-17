package model;

import java.util.ArrayList;
import java.util.List;

public class Event {
	
	private String name; //name of event
	private Period startPeriod; //start period
	private Period endPeriod; //end period
	///////////////////private AirForce airForce; //an air force involved
	private List<AirForce>airForces; //list of air forces involved
	
	private Event() {} //blank constructor
	
	//builder class:
	public static class EventBuilder {
		
		private Event event = new Event(); //create event
		
		//set name:
		public EventBuilder setEventName(String name) {
			event.name = name;
			return this;
		}
		
		//set start period:
		public EventBuilder setStartPeriod(Period period) {
			event.startPeriod = new Period(period.getBlock(), period.getYear());
			return this;
		}
		
		//set end period:
		public EventBuilder setEndPeriod(Period period) {
			event.endPeriod = new Period(period.getBlock(), period.getYear());
			return this;
		}
		
		/*
		//set event air force: //+++++++++++++++++PROB DONT NEED! :P
		public EventBuilder setEventAirForce(AirForce airForce) { //+++++++++++++++++++++++MAKE STRONGER
			event.airForce = new AirForce.AirForceBuilder()
					.setAirForceName(airForce.getAirForceName())
					.setHasHomeAdv(airForce.getHasHomeAdv())
					.build();
			///////event.airForce = airForce; +++++++++++
			return this;
		}*/
		
		//set list of event air forces:
		//+++++++++++++++++++++++++++++++loop through passed list and make a new one with each val
		public EventBuilder setEventAirForces(List<AirForce>airForces) { //++++++++++++++++++++MAKE STRONGER
			event.airForces = new ArrayList<AirForce>(airForces);
			return this;
		}
		
		//return built event:
		public Event build() { return event; } 
	}
	
	//get event name:
	public String getEventName() { return name; }
	
	//get start period:
	public Period getStartPeriod() {
		return new Period(startPeriod.getBlock(), startPeriod.getYear());
	}
	
	//get end period:
	public Period getEndPeriod() {
		return new Period(endPeriod.getBlock(), endPeriod.getYear());
	}
	
	
	/*
	//get event air force:
	public AirForce getEventAirForce() {
		
		return new AirForce.AirForceBuilder()
				.setAirForceName(airForce.getAirForceName())
				.setHasHomeAdv(airForce.getHasHomeAdv())
				.build();
	}*/
	
	//get event air forces:
	public List<AirForce> getEventAirForces() {
		//+++++++++++++++++++++++++++++++loop through passed list and make a new one with each val & pass that! 
		return new ArrayList<AirForce>(airForces);
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", startPeriod=" + startPeriod + ", endPeriod=" + endPeriod
				 + ", airForces=" + airForces + "]";
	}

}