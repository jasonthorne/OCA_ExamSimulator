package com.brs.airforce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.period.Period;
import com.brs.period.PeriodData.Block;
import com.brs.period.PeriodData.Year;
import com.brs.plane.Plane;
import com.brs.plane.Plane.Availability;
import com.brs.plane.PlaneData.Model;
import com.brs.plane.PlaneData.Status;
import com.brs.airforce.AirForce;


public class AirForceUSAAF extends AirForce{
	
	@Override
	protected void setName() { name = AirForceName.USAAF.toString(); }  //name of AirForce
		
	@Override
	protected void setModels() { //models of plane available: 
		models = Arrays.asList(
				Model.F4F_WILDCAT, Model.F4U_CORSAIR, Model.F6F_HELLCAT, Model.P_38E_LIGHTNING, Model.P_38J_LIGHTNING, 
	    		Model.P_39_AIRCOBRA, Model.P_40B_WARHAWK, Model.P_40E_TOMAHAWK, Model.P_40N_KITTYHAWK, Model.P_47C_THUNDERBOLT, 
	    		Model.P_47D_THUNDERBOLT, Model.P_51B_MUSTANG, Model.P_51D_MUSTANG);
	}

	
	public AirForceUSAAF(){
		setName(); //set name of of AirForce
		setModels(); //set models of plane available
		//ADD NAME AND MODELS TO MAP OF ALL MODELS AND THEIR NAMES +++++++++++++
	}
	
	
	//private static final String name = AirForceName.USAAF.toString(); //name of AirForce
	
		/*
		//models of plane available: 
		private static final List<Model>models = Arrays.asList(
				Model.F4F_WILDCAT, Model.F4U_CORSAIR, Model.F6F_HELLCAT, Model.P_38E_LIGHTNING, Model.P_38J_LIGHTNING, 
	    		Model.P_39_AIRCOBRA, Model.P_40B_WARHAWK, Model.P_40E_TOMAHAWK, Model.P_40N_KITTYHAWK, Model.P_47C_THUNDERBOLT, 
	    		Model.P_47D_THUNDERBOLT, Model.P_51B_MUSTANG, Model.P_51D_MUSTANG);
		*/
		
	
	
	//creates a HashMap of periods and their statuses for the model of plane passed to it:  
	@Override 
	protected void setPeriodToStatus(Model model) throws Exception {	
		periodToStatus = new HashMap<Period, Status>(); //(re)set HashMap
		
		switch(model) { //populate periods and statuses, according to model:
		  case F4F_WILDCAT:
			  periods = Period.getPeriods(new Period(Block.LATE, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_FOUR)); //++++++++CHECK FOR COLLISIONS FROM PREVIOUS FILLS HERE!!!
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, //++++++++CHECK FOR COLLISIONS FROM PREVIOUS FILLS HERE!!!
			  Status.LIMIT, Status.LIMIT);
			  break;
		  case F4U_CORSAIR:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case F6F_HELLCAT:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case P_38E_LIGHTNING:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_TWO), new Period(Block.LATE, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case P_38J_LIGHTNING:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case P_39_AIRCOBRA:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break; 
		  case P_40B_WARHAWK:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_TWO));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT);
			  break;
		  case P_40E_TOMAHAWK:
			  periods = Period.getPeriods(new Period(Block.LATE, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case P_40N_KITTYHAWK:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_THREE), new Period(Block.LATE, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case P_47C_THUNDERBOLT:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case P_47D_THUNDERBOLT:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case P_51B_MUSTANG:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case P_51D_MUSTANG:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  default: throw new Exception("Error: model not found.");
		}
		 //add periods and statuses to HashMap:
		for (int i=0; i<periods.size(); i++) { periodToStatus.put(periods.get(i), statuses.get(i)); }
		
	}
	
	
	//Getters: //++++++++++++++++++++CHANGE PRIVACY OF THESE +++++++++
	@Override
	public String getDescription() { 
		return "AirForceUSAAF description..."; 	//description of air force
	}


	
	
	
	String test() {
		return name;
	}


	//@Override
	//public static String getName() { return name; }


	

	/*
	//+++++++++++++THIS SHOULD PROB BE NON STATIC AND KEPT PUBLIC, but be in parent class, not here :P
	public Map<Period, Status>getPeriodToStatus(Model model) throws Exception{ //+++++++++++++all the throws here minght not be needed. We;ll see!! 
		setPeriodToStatus(model);
		return periodToStatus;
	}*/

	//HERE we OVERRIDE THE getairForceModels method THAT YOU NEED TO PUT IN SUPER with the returning of all american planes (other classes obv retunr their ariforce planes)
	
}