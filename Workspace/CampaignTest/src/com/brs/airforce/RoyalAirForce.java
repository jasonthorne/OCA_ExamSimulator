package com.brs.airforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.period.Period;
import com.brs.period.Periods;
import com.brs.period.Block;
import com.brs.period.Year;
import com.brs.plane.Plane;
import com.brs.plane.Model;
import com.brs.plane.Status;


public class RoyalAirForce extends AirForce{ /////////https://stackoverflow.com/questions/4176205/creating-a-list-in-mysql
	
	//RoyalAirForce name:
	private static final AirForceName NAME = AirForceName.RAF; 
	
	//RoyalAirForce models of plane:		//////////////////////Airforce model obj instead with name, periods and statuses
	private static final List<Model>MODELS = Arrays.asList(
			Model.HURRICANE_I, Model.HURRICANE_II, Model.MOSQUITO_II, Model.MOSQUITO_VI, Model.SPITFIRE_II,
			Model.SPITFIRE_V, Model.SPITFIRE_IX, Model.SPITFIRE_XIV, Model.TEMPEST_V, Model.TYPHOON_IB);

	//creates a HashMap of periods and their statuses for the model of plane passed to it: 
	@Override 
	protected void setPeriodToStatus(Model model) {
		periodToStatus = new HashMap<Period, Status>(); //(re)set HashMap
		
		switch(model) { //populate periods and statuses, according to model:
		  case HURRICANE_I:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY), new Period(Block.LATE, Year.FORTY)); 
			  statuses = Arrays.asList(Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case HURRICANE_II:
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY), new Period(Block.EARLY, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT, 
			  Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case MOSQUITO_II:
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case MOSQUITO_VI:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO);
			  break;
		  case SPITFIRE_II:
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY), new Period(Block.LATE, Year.FORTY_ONE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT);
			  break;
		  case SPITFIRE_V:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.LIMIT);
			  break; 
		  case SPITFIRE_IX:
			  periods = Periods.getPeriods(new Period(Block.LATE, Year.FORTY_TWO), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.LIMIT);
			  break;
		  case SPITFIRE_XIV:
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case TEMPEST_V:
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case TYPHOON_IB:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		}
		 //add periods and statuses to HashMap:
		for (int i=0, j=periods.size(); i<j; i++) { periodToStatus.put(periods.get(i), statuses.get(i)); }
	}
	
	@Override
	public String getName() {return NAME.toString();} //get name of air force
	@Override
	public List<Model> getAllModels() {return new ArrayList<Model>(MODELS);} //get copy of models of plane available
	
}
