package com.brs.events;

public interface InsertEventData {
	
	static void insert() { 
		new Events().getEvents().forEach((event)->{ //forEach event:
			
			InsertEventAirForces.insert(event.getName(), event.getAirForces()); //populate 'event_airforces'
			//populate 'event_periods':
			
		});
	}
	
}