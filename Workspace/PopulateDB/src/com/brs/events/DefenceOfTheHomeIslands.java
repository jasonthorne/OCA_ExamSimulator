package com.brs.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.brs.airforces.AirForce;
import com.brs.blocks.Block;
import com.brs.periods.Period;
import com.brs.years.Year;

public class DefenceOfTheHomeIslands extends EventData {
	
	//name of event:
	private static final Event NAME = Event.DEFENCE_OF_THE_HOME_ISLANDS;
	
	//air forces involved, and their home advantage status:
	private static final List<EventAirForce>AIR_FORCES = Arrays.asList(
			new EventAirForce(AirForce.USAAF, HomeAdvantage.FALSE),
			new EventAirForce(AirForce.IJAAF, HomeAdvantage.TRUE));
	
	//periods of history covered:
	private static final Period START_PERIOD = new Period(Block.MID, Year.FORTY_FOUR); 
	private static final Period END_PERIOD = new Period(Block.MID, Year.FORTY_FIVE);
	
	@Override
	protected Event getName() {return NAME;} //return name of event
	@Override
	protected List<EventAirForce> getAirForces() {return new ArrayList<EventAirForce>(AIR_FORCES);} //return air forces involved
	@Override
	protected Period getStartPeriod() {return new Period(START_PERIOD.getBlock(), START_PERIOD.getYear());} //return start period
	@Override
	protected Period getEndPeriod() {return new Period(END_PERIOD.getBlock(), END_PERIOD.getYear());} //return end period
	
}