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
import com.brs.airforce.AirForce;


public class UnitedStates extends AirForce{
	
	//UnitedStates name:
	private static final AirForceName NAME = AirForceName.USAAF; 

	//UnitedStates models of plane:
	private static final List<Model>MODELS = Arrays.asList(
			Model.F4F_WILDCAT, Model.F4U_CORSAIR, Model.F6F_HELLCAT, Model.P_38E_LIGHTNING, Model.P_38J_LIGHTNING, 
			Model.P_39_AIRACOBRA, Model.P_40B_WARHAWK, Model.P_40E_TOMAHAWK, Model.P_40N_KITTYHAWK, Model.P_47C_THUNDERBOLT, 
			Model.P_47D_THUNDERBOLT, Model.P_51B_MUSTANG, Model.P_51D_MUSTANG);

	//creates a HashMap of periods and their statuses for the model of plane passed to it: 
	@Override 
	protected void setPeriodToStatus(Model model) {
		periodToStatus = new HashMap<Period, Status>(); //(re)set HashMap
		
		switch(model) { //populate periods and statuses, according to model:
		  case F4F_WILDCAT:
			  periods = Periods.getPeriods(new Period(Block.LATE, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_FOUR)); 
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.LIMIT, Status.LIMIT);
			  break;
		  case F4U_CORSAIR:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case F6F_HELLCAT:
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case P_38E_LIGHTNING:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_TWO), new Period(Block.LATE, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case P_38J_LIGHTNING:
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case P_39_AIRACOBRA:
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break; 
		  case P_40B_WARHAWK:
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_TWO));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT);
			  break;
		  case P_40E_TOMAHAWK:
			  periods = Periods.getPeriods(new Period(Block.LATE, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case P_40N_KITTYHAWK:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_THREE), new Period(Block.LATE, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case P_47C_THUNDERBOLT:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case P_47D_THUNDERBOLT:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case P_51B_MUSTANG:
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case P_51D_MUSTANG:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
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
