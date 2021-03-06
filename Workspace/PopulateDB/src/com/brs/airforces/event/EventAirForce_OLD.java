package com.brs.airforces.event;

import com.brs.airforces.AirForce;
import com.brs.events.Event;

public class EventAirForce_OLD {
	
	private final Event event; //event
	private final AirForce airForce; //air force
	private final boolean hasHomeAdvantage; //home advantage status
	
	protected EventAirForce_OLD(Event event, AirForce airForce, boolean hasHomeAdvantage) {
		this.event = event;
		this.airForce = airForce;
		this.hasHomeAdvantage = hasHomeAdvantage;
	}

	@Override
	public String toString() {
		return "EventAirForce_OLD [event=" + event + ", airForce=" + airForce + ", hasHomeAdvantage=" + hasHomeAdvantage + "]";
	}
	

}
