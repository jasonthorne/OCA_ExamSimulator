package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Event;
/*import model.Event.EventBuilder;*/
import model.AirForce;
/*import model.AirForce.AirForceBuilder;*/
import model.Period;
import model.Period.Block;
import model.Plane;
/*import model.Plane.PlaneBuilder;*/
import model.Plane.Status;

/** selects events & their dependencies from db. 
 * returning a list of event objects, for displaying to user when choosing an event during campaign creation. */

public interface SelectEvents {

	public static List<Event> select() {
		
		List<Event>events = new ArrayList<Event>(); //list for events
		
		try (Connection connection = ConnectDB.getConnection();  //connect to DB
			//statements for selecting events and their children:
			CallableStatement eventsStatement = connection.prepareCall("{CALL select_events()}");
			CallableStatement periodsStatement = connection.prepareCall("{CALL select_event_periods(?)}");	
			CallableStatement airForcesStatement = connection.prepareCall("{CALL select_event_airforces(?)}");
			CallableStatement planesStatement = connection.prepareCall("{CALL select_airforce_planes(?)}");
			CallableStatement availabilitiesStatement = connection.prepareCall("{CALL select_plane_availabilities(?,?)}");
			ResultSet eventsRS = eventsStatement.executeQuery();) { //execute events statement
			
			//result sets for nested data:
			ResultSet periodsRS = null; //periods result set
			ResultSet airForcesRS = null; //air forces result set
			ResultSet planesRS = null; //air force planes result set
			ResultSet availabilitiesRS = null; //plane availabilities result set
			
			while(eventsRS.next()) {
				
				//get event name: 
				String eventName = eventsRS.getString("event_name");
				
				//create list for event periods:
				List<Period>eventPeriods = new ArrayList<>();
				
				//set statement input with event id:
				periodsStatement.setInt(1, eventsRS.getInt("event_ID")); 
				periodsRS = periodsStatement.executeQuery(); //execute event periods query
				
				while(periodsRS.next()) {
					
					//add period to list:
					eventPeriods.add(new Period(
							Block.valueOf(periodsRS.getString("period_block").toUpperCase()),
							periodsRS.getInt("period_year"))); 
				}
				
				//+++++++++++++++++++++++++++++++++++++++PASS THE LIST OF PERIODS TO EVENT, INSTEAD OF JUST THE STARRT & END :P 
				//get start & end periods from list:
				Period startPeriod = eventPeriods.get(0);
				Period endPeriod = eventPeriods.get(eventPeriods.size()-1);
				
				//get period total:
				int periodTotal = eventPeriods.size(); 
				
				//create list for event air forces:
				List<AirForce>eventAirForces = new ArrayList<>();
				
				//set statement input with event id:
				airForcesStatement.setInt(1, eventsRS.getInt("event_ID")); 
				airForcesRS = airForcesStatement.executeQuery(); //execute air forces query
				
				while(airForcesRS.next()) {
					
					String airForceName = airForcesRS.getString("airforce_name"); //get air force name
					boolean hasHomeAdv = airForcesRS.getBoolean("home_advantage_value"); //get home adv value
					
					//create list for air force planes:
					List<Plane>airForcePlanes = new ArrayList<>();
			
					//set statement input with air force id:
					planesStatement.setInt(1, airForcesRS.getInt("airforce_ID")); 
					planesRS = planesStatement.executeQuery(); //execute planes query
					
					while(planesRS.next()) {
						
						String planeNane = planesRS.getString("plane_name"); //get plane name
						
						//set statement input parameters with air force plane id & event id:
						availabilitiesStatement.setInt(1, planesRS.getInt("airforce_plane_ID"));
						availabilitiesStatement.setInt(2, eventsRS.getInt("event_ID"));
						availabilitiesRS = availabilitiesStatement.executeQuery(); //execute availabilities query
						
						if (availabilitiesRS.isBeforeFirst()){ //if availabilities where found
							
							//create map for availabilities, using event periods:
							Map<Period, Status>planeAvailabilities = eventPeriods.stream()
									.collect(Collectors.toMap(period -> period, status -> Status.UNAVAILABLE));
							
							while(availabilitiesRS.next()) {
								
								//add plane's availabilities to map:
								planeAvailabilities.put(new Period(
										Block.valueOf(availabilitiesRS.getString("block_option").toUpperCase()),
										availabilitiesRS.getInt("year_value")),
										Status.valueOf(availabilitiesRS.getString("status_option").toUpperCase()));
							}
							
							//ad plane to air force planes:
							airForcePlanes.add(new Plane(planeNane, planeAvailabilities));
						}
						
					}//planesRS
					
					//add air force to event air forces:
					eventAirForces.add(new AirForce(airForceName, hasHomeAdv, airForcePlanes));
					
				}//airForcesRS
				
				//add event to events:
				//////////////////////##########events.add(new Event(eventName, startPeriod, endPeriod, periodTotal, eventAirForces));
				events.add(new Event(eventName, eventPeriods, eventAirForces));
				
			}//eventsRS
			
		} catch(Exception e) { e.printStackTrace(); }
	
		return events; //return events
	}
}
