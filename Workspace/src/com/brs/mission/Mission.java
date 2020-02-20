package com.brs.mission;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

import com.brs.die.Die;
import com.brs.period.Period;
import com.brs.player.Player;


//---------------------------

/*
 * 
 * logbook contains logs

----------------------
log has: period and turn (2 keys)

player faced

reults to squad
-----------------------




mission creates the logs:
make a player log (containing opponent, date and results )


mission saves the logs to player's mission books. 

after conflict: 


 * 
 * 
 */
//---------------------

public class Mission implements Die{
	
	//+++++++++++ADD FINALS TO THESE LATER!! 
	private List<String>players; //players involved
	private Period period; //period represented
	private int turnNum;
	//private final String date; //date played
	//private Results resutls; ??????????????????
	
	////A mission contains 2 mission logs (player 1 & p2). when they're both filled out, then the mission logs arer saved to the players 
	
	////////public Mission(List<String>players, Period period, String date) {
	///////////////public Mission(List<String>players, Period period) {
	public Mission(List<String>players, Period period, int turnNum) { //add turn num too for mission log creation
		this.players = players; //set list of players
		this.period = period; //set period
		this.turnNum = turnNum;
		///////////this.date = date; //set date played
	}
	
	List<MissionLog>missionLogs;
	
	
	public Mission(List<MissionLog>missionLogs) {
		this.missionLogs = missionLogs;
		
	}
	
	//+++++++++++++++++++https://www.geeksforgeeks.org/parse-json-java/
	
	//imitates D6 roll by providing random number from 1-6:
	//private static final Supplier<Integer>D6=()->new Random().nextInt(6)+1; //MOVE THIS TO IT's OWN INTERFACE!! 
	
	
	
	//post mission stuff: 
	
	public void shotDownRoll() {
		
		switch(D6.get()) {
		 case 6: case 5: 
			 System.out.println("Forced landing"); 
			 break;
		 case 4: case 3: 
			 System.out.println("Bail out");
			 bailOutRoll(); //get bailout result
			 break;
		 case 2: case 1: 
			 System.out.println("KIA"); 
			 break;
		}
	}
	
	
	public void bailOutRoll() {
		
		switch(D6.get()) {
		 case 6: case 5: case 4:
			 System.out.println("OK"); 
			 break;
		 case 3: case 2: 
			 System.out.println("Bad landing");
			 injuryRoll();
			  break;
		 case 1: 
			 System.out.println("Chute failure"); 
			 break;
		}
	}
	
	
	public void injuryRoll() {
		
		switch(D6.get()) {
		 case 6: case 5: 
			 System.out.println("Just a scratch"); 
			 break;
		 case 4: case 3: 
			 System.out.println("Down but not out");
			 break;
		 case 2: 
			 System.out.println("Major Injury");
			 break;
		 case 1: 
			 System.out.println("Crippling injury"); 
			 break;
		}
	}
	
	
	public void damagedPlaneRoll() {
		
		switch(D6.get()) {
		 case 6: case 5: case 4:
			 System.out.println("RTB"); 
			 break;
		  case 3: case 2:
			 System.out.println("Forced Landing");
			 break;
		  case 1:
			 System.out.println("Bail out");
			 bailOutRoll(); //get bailout result
			 break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void getPlayersTEST() {
		System.out.println(players);
	}

	
	@Override
	public String toString() {
		return "Mission [players=" + players + ", period=" + period  + "]";
	}

	
	//trigger the post mission stuff once BOTH players have added all their input.
	

}
	