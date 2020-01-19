package com.brs.campaign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.brs.player.Player;
import com.brs.squadron.Squadron;
import com.brs.turn.Turn;
import com.brs.airforce.AirForceName;
import com.brs.date.Date;
import com.brs.event.EventFactory;
import com.brs.event.EventName;
import com.brs.event.Event;
import com.brs.mission.Mission;
import com.brs.mission.MissionBuilder;
import com.brs.period.Period;
import com.brs.period.Year;

public class Campaign { 
	
	private final String name; //name of campaign
	private final String date = Date.getDate(); //date of creation
	private final Event event; //historical event chosen
	private Period period; //current period represented
	private final ListIterator<Period>periodsIterator; //iterator for advancing period
	private Map<String, Player>nameToPlayer = new TreeMap<String, Player>(); //map of players involved  ////+++++++MAYBE USE SET HERE INSTEAD OF HASHMAP - then use streams for playing about with it.
	
	//=======================================months 1 - 4. after month 4, then move one period
	//private Month month; //current month represented
	private static final int TURNS_PER_PERIOD = 4; //amount of turns played per period
	private int turnNum; //number of turns in campaign
	///////////private List<Turn>turns; 
	private static final String BYE = "bye"; //bye entry for pairing odd number of players
	
	//=======================================
	private List<List<String>>pairings = new ArrayList<List<String>>(); //combinations of player pairings 
	
	/////////++++++++++++++++missions: ++++++++++++++++++++++++
	private Map<Period, List<Mission>>periodToMission = new HashMap<Period, List<Mission>>(); 
	
	////////////////////private Map<Mission, List<Player>>missionToPlayers = new HashMap<Mission, List<Player>>(); //map of current missions
	
	public Campaign(EventName eventName) {
		event = new EventFactory().getEvent(eventName); //create event from EventFactory
		name = "Campaign: " + event.getName(); //create campaign name
		periodsIterator = event.getPeriods().listIterator(); //create iterator from periods
	}
	
	//add a new player to map of players:
	public void setPlayer(String name, AirForceName airForceName) { 
		//attempt to add player to map using their name as key. Warn user if unsuccessful (returned value other than null): 
		if(!(nameToPlayer.putIfAbsent(name, new Player(name, event.getAirForce(airForceName), period))==null)){
			System.out.println("Error: Player name already exists!"); 
		}else { System.out.println("Player added."); }
	}
	
	//////////////List<String>unpairedPlayers;
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	//Make a turn
	Turn currTurn;
	
	
	public void beginTurns() {
		turnNum = 1; //set turnNum
		movePeriod(); //set period iterator to starting period
		setOpponents(); //set each player's opponents
		//pairPlayers2(); //pair players 
		
	}
	
	
	Map<String, List<String>>playerToOpponents = new TreeMap<String, List<String>>(); //+++++++++++TEST FOR BELOW
	
	public void setOpponents(){
		
		
		//List<String>keys = new ArrayList<String>(nameToPlayer.keySet());
		//List<String>values = new ArrayList<String>(nameToPlayer.keySet().remove(arg0));
		
		List<String>players = new ArrayList<String>(nameToPlayer.keySet());
		
		//if odd number of players, add a bye:
		if(players.size()%2==1) { players.add(BYE); }
		
		players.forEach(player ->{
			
			List<String>opps = new ArrayList<String>(players);
			opps.remove(player);
			
			///System.out.println("opps are: " + opps);
			playerToOpponents.put(player, opps);
			
		});
		
		
		
		//System.out.println("opps are: " + opps);
		//(new ArrayList<String>(nameToPlayer.keySet()));
		
		
		for (Entry<String, Player> entry : nameToPlayer.entrySet()) { //for each player in map:
			List<String>opponents = new ArrayList<String>(nameToPlayer.keySet()); //create a list of opponents from player names
			opponents.remove(entry.getKey()); //remove current player from list
			entry.getValue().setOpponents(opponents); //set player's list of opponents
			
			//=========================
			entry.getValue().setCouldPlayNow(opponents); //set player's list of potential opponents
			//==========================
			
			//==========================================================================
			/*
			 * 	MAKE MAP, HERE OF PLAYER AND OPPONENTS.
			 * MAKE PAIRING FROM RANDOM KEY AND A RANDOM ONE OF ITS VALUES,
			 * REMOVE THAT ENTRY AND ITS VALUE ENTRY AND ALL INSTANCES OF BOTH KEY AND VAL FROM ALL OTHER MAPS
			 * 
			 * return the values to other key's hashmaps after picking though. 
			 * RINSE AND REPEAT,PICKING RANDOM KEY, AND A RANDOM VAL FROM IT, UNTILL ALL KEYS HAVE HAD PAIRS MADE FROM THEM 
			 * 
			 * THIS ***SHOULD*** BE US SORTED!!!! HOPEFULLY :p
			 * 
			 */
			//playerToOpponents.put(entry.getKey(), opponents);
			
			
			
		
			//===========================================================================
		}
		
		System.out.println("playerToOpponents: " + playerToOpponents);
		
		
	}
	
	
	public void makeMissions(){
		
		List<List<String>>pairingsCOPY = new ArrayList<List<String>>(pairings); //combinations of player pairings 
		//Collections.shuffle(pairings2);
		System.out.println("pairingsCOPY: " + pairingsCOPY);
		
		List<List<String>>usedPairings = new ArrayList<List<String>>();
		
		List<String>pairing;
		
		/*
		 * have duplicates of each set. (A,B) (B,A)
		 * then go from A - F and randomly grab an A at either index 0 or 1, then delete all other A instances (from BOTH indexes)
		 * then go rinse and repeat
		 */
		
		//==============================================================================
		
		/*
		List<String>players = new ArrayList<String>(nameToPlayer.keySet()); //+++++++++++++++++
		
		//long randIndex = Arrays.asList(0,1);
		
		///players.forEach(player ->{
		for(int i=0; i<players.size();i++) {
			
			//Collections.shuffle(pairings2);
			//System.out.println("pairings2: " + pairings2);
			
			String player = players.get(i); //ughhhh!
			
			List<String>pairingpTEST = Arrays.asList(pairings2.get(p))	//((int)(Math.random()*1)));
			//pairing = pairings2.remove(new Random().nextInt(pairings2.size()));
			
					
		
			
			//pairing = pairings2.remove(new Random().nextInt(pairings2.size()));
			
			//make mission here, adding pairing ++++++++++++++++++++++++++++++++++
			System.out.println("player is: " + player);
			///////////System.out.println("pairingpTEST: " + pairingpTEST);
			
			//////////////usedPairings.add(pairingpTEST);
			
			pairing.forEach(pairedPlayer -> {
				pairings2.removeIf(n -> (n.contains(pairedPlayer)));
				/////////System.out.println("player is: " + player);
			});
	
		}*/
	
		
		
		for(int i=0; i<(nameToPlayer.size()/2);i++) {
			
			pairing = pairingsCOPY.remove(new Random().nextInt(pairingsCOPY.size()));
			
			//make mission here, adding pairing ++++++++++++++++++++++++++++++++++
			System.out.println("picked: " + pairing + " left: "+ pairingsCOPY);
			
			usedPairings.add(pairing);
			
			pairing.forEach(player -> {
				pairingsCOPY.removeIf(n -> (n.contains(player)));
				/////////System.out.println("player is: " + player);
			});
	
		}
		
		//=========================+++++++++++++++++
		
		
		System.out.println("usedPairings: " + usedPairings);
		System.out.println("pairingsCOPY: "+ pairingsCOPY);
		
		
		pairings.removeAll(usedPairings); //remove all used pairings from pairings
		
		System.out.println("pairings: " + pairings);
		
		
	
		
		/* 
		 * https://www.baeldung.com/java-remove-value-from-list
		 */
	}
	
	
	

	/////////////////Map<String, List<String>>playerToOpponents = new HashMap<String, List<String>>(); //players and a list of their opponents
	
	
	//public void makePairings(String... players){ //++++++++++++++++++PASS IN THE LIST INSTEAD SO YOU CAN ADD A NEW PLAYER (with maybe a bye) AT A LATER POINT
	public void makePairings(){ 
		
		//players assigned a list of their opponents:
		List<String>pairedPlayers = new ArrayList<String>();
		
		//list of player's names to draw pairings from:
		List<String>unpairedPlayers = new ArrayList<String>(nameToPlayer.keySet()); 
		
		//if odd number of players, add a bye:
		if(unpairedPlayers.size()%2==1) { unpairedPlayers.add(BYE); }
		
		unpairedPlayers.forEach(player ->{
			
			List<String>opponents = new ArrayList<String>(unpairedPlayers); //create a list of opponents from player names
			pairedPlayers.add(player); //add current player to list, for removal from current (and future) player's opponents list
			opponents.removeAll(pairedPlayers); //remove already paired players from current player's opponents list
			
			//if there are still opponents to allocate, add player and list of player's opponents to map:
			if(!opponents.isEmpty()) { 
				
				///////////////////playerToOpponents.put(player, opponents); 
			
				
				opponents.forEach(opponent ->{
					pairings.add(new ArrayList<String>(Arrays.asList(player, opponent)));
					//pairings.add(new ArrayList<String>(Arrays.asList(opponent, player))); /////////////++++++++++add reversed list
				});
				
			} 
			
			System.out.println("player: " + player + ". opponents: " + opponents); //++++++++++++++++++++
			
		});
		
		////////////////System.out.println("playerToOpponentsTEST: " + playerToOpponents); //+++++++++++++++++++
		
		/*
		 * randomly pick a key
		 * then randomly pick a value
		 * then remove all instances of keys an values
		 */
		
		
		///////////////////pairings.removeIf(n -> (n.contains("A"))); //BOOM!!! :)
		
		/////////////////////////System.out.println("pairings: " + pairings); //+++++++++++++++++++students.removeIf(n -> (n.charAt(0) == 'A')); 
	}
	
	

	//++++++++++++++++CAN ALSO BE USED FOR STARTING GAME! 
	public void advanceTurn() {
		//increment turnNum and check if period should be moved:
		if(++turnNum>TURNS_PER_PERIOD) { 
			turnNum = 1; //reset turnNum 
			movePeriod(); //advance to next period
		}
		pairPlayers(); //pair players
		//if() { //if all players have played all other players. THEN empty their "played buckets and reseed"
			
		//}
		//////////////////currTurn = new Turn();	
	}
	
	
	public void ughhhh() {
		List<String>unpairedPlayers = new ArrayList<String>(nameToPlayer.keySet()); 
		//Collections.shuffle(unpairedPlayers);
		System.out.println("unpairedPlayers: " + unpairedPlayers);
		
		//pick player1:
		int player1Index = new Random().nextInt(unpairedPlayers.size());
		String player1 = unpairedPlayers.get(player1Index);
		System.out.println("p1: " + player1);
		
		//pick player2:
		int player2Index = new Random().nextInt(nameToPlayer.get(player1).getOpponents().size()); //random index taken from player1's opponents list size
		String player2 = nameToPlayer.get(player1).getOpponents().get(player2Index); //random player pulled from player1's list
		System.out.println("p2: " +player2);
		
		//remove player 2 from player 1 opps list:
		System.out.println("p1 opps b4 removal: " + nameToPlayer.get(player1).getOpponents());
		nameToPlayer.get(player1).getOpponents().remove(player2);
		System.out.println("p1 opps after removal: " + nameToPlayer.get(player1).getOpponents());
		
		//remove player 1 from player 2 opps list:
		System.out.println("p2 opps b4 removal: " + nameToPlayer.get(player2).getOpponents());
		nameToPlayer.get(player2).getOpponents().remove(player1);
		System.out.println("p2 opps after removal: " + nameToPlayer.get(player2).getOpponents());
		
		//remove players from unpairedPlayers;
		System.out.println("unpairedPlayers b4 removal: " + unpairedPlayers);
		unpairedPlayers.remove(player1);
		unpairedPlayers.remove(player2);
		System.out.println("unpairedPlayers after removal: " + unpairedPlayers);
		
	}
	
	public void swapTEST() {
		for (Entry<String, Player> entry : nameToPlayer.entrySet()) {
			entry.getValue().swapListsTEST();
		}
	}
	
	
	public void pairPlayers4() {
		
		List<String>unpairedPlayers = new ArrayList<String>(nameToPlayer.keySet()); 
		//Collections.shuffle(unpairedPlayers);
		System.out.println("unpairedPlayers: " + unpairedPlayers);
		
		String player1 = "";
		String player2 = "";
		
		//while there are unpaired players:
		while(!unpairedPlayers.isEmpty()) { 
			
			
			System.out.println("unpairedPlayers b4 removal: " + unpairedPlayers);
			
			
			//pick player1:
			int player1Index = new Random().nextInt(unpairedPlayers.size());
			player1 = unpairedPlayers.get(player1Index);
			System.out.println("p1: " + player1);
			
			unpairedPlayers.remove(player1); //remove player 1 from unpairedPlayers
			
			//pick player2:
			
			
			//int player2Index = new Random().nextInt(nameToPlayer.get(player1).getOpponents().size()); //random index taken from player1's opponents list size
			//player2 = nameToPlayer.get(player1).getOpponents().get(player2Index); //random player pulled from player1's list
			
			//-------------------------
			int player2Index = new Random().nextInt(nameToPlayer.get(player1).getCouldPlayNow().size());
			player2 = nameToPlayer.get(player1).getCouldPlayNow().get(player2Index); //grab player from could play now list
			//------------------------
			System.out.println("p2: " +player2);
			
			unpairedPlayers.remove(player2);
			
			
			/*
			int player2Index = new Random().nextInt(unpairedPlayers.size());
			player2 = unpairedPlayers.get(player2Index);
			System.out.println("p2: " + player2);
			
			unpairedPlayers.remove(player2);
			*/
			
			//------------------------
			//remove player 2 from player 1 opps list:
			System.out.println("p1 opps b4 removal: " + nameToPlayer.get(player1).getCouldPlayNow());
			nameToPlayer.get(player1).getCouldPlayNow().remove(player2);
			System.out.println("p1 opps after removal: " + nameToPlayer.get(player1).getCouldPlayNow());
			
			//remove player 1 from player 2 opps list:
			System.out.println("p2 opps b4 removal: " + nameToPlayer.get(player2).getCouldPlayNow());
			nameToPlayer.get(player2).getCouldPlayNow().remove(player1);
			System.out.println("p2 opps after removal: " + nameToPlayer.get(player2).getCouldPlayNow());
			
			
			/*
			//remove player 2 from player 1 opps list:
			System.out.println("p1 opps b4 removal: " + nameToPlayer.get(player1).getOpponents());
			nameToPlayer.get(player1).getOpponents().remove(player2);
			System.out.println("p1 opps after removal: " + nameToPlayer.get(player1).getOpponents());
			
			//remove player 1 from player 2 opps list:
			System.out.println("p2 opps b4 removal: " + nameToPlayer.get(player2).getOpponents());
			nameToPlayer.get(player2).getOpponents().remove(player1);
			System.out.println("p2 opps after removal: " + nameToPlayer.get(player2).getOpponents());
			*/
			
			/*
			for (Entry<String, Player> entry : nameToPlayer.entrySet()) {
				entry.getValue().getOpponents().remove(player1);
				entry.getValue().getOpponents().remove(player2);
			}
			*/
			
			//--------------------------
			
			
			for (Entry<String, Player> entry : nameToPlayer.entrySet()) {
				
				if((!entry.getKey().equals(player1)) && (!entry.getKey().equals(player2))) {
					entry.getValue().getCouldPlayNow().remove(player1);
					entry.getValue().getCouldPlayNow().remove(player2);
					
					entry.getValue().setCanPlayLater(player1);
					entry.getValue().setCanPlayLater(player2);
					
					//---------------------------------------
					System.out.println(entry.getKey() + " can play later: " + entry.getValue().getCanPlayLater());
					
					//----------------------------------------
				}
				
			}
			
			
			//remove players from unpairedPlayers;
			//System.out.println("unpairedPlayers b4 removal: " + unpairedPlayers);
			//unpairedPlayers.remove(player1);
			//unpairedPlayers.remove(player2);
			System.out.println("unpairedPlayers after removal: " + unpairedPlayers);
		}
		
		System.out.println("empty unpairedPlayers: " + unpairedPlayers);
		
	}
	

	
	//Make x pairings (give each pairing a mission)
	public void pairPlayers() { 
		
		//list of player's names to draw pairings from:
		List<String>unpairedPlayers = new ArrayList<String>(nameToPlayer.keySet()); 
		
		//if odd number of players, add a bye:
		if(nameToPlayer.size()%2==1) { unpairedPlayers.add(BYE); }
		
		//while there are unpaired players:
		while(!unpairedPlayers.isEmpty()) { 
			
			List<String>pairedCouple = new ArrayList<String>(); //make list for holding 2 paired players
			
			while(pairedCouple.size()!=2) { //while a couple haven't been picked
	
				//pick a random pos to select an unpaired player from: 
				int randomIndex = new Random().nextInt(unpairedPlayers.size());
				
				//add player at random pos to pairedCouple:
				pairedCouple.add(unpairedPlayers.get(randomIndex));
				
				//remove paired player from list of unpaired players:
				unpairedPlayers.remove(randomIndex);
			}
			
			
			System.out.println(pairedCouple);
			//mission = new Mission(pair);
			////missionToPlayers.put(new Mission(players), players);
			//MAKE NEW MISSION, ADDING THESE PLAYERS,
			
			//LIST OF NAME<played>
			
			//+++++++++BOTH PLAYERS MARK THAT THEY@VE BEEN PAIRED AGAINST EACH OTHER. 
			//AND NEXT PAIRING SESSION HAVE EACH PLAYER CHECK THAT THEYVE NOT JUST PLAYED THIS PERSON +++++++++++++++++
			
			//HOLIDAYS: 17th - 24th. 
			
		}
		
		
	}
	
	
	
	
	/*
	
	-
	- once everyones finished their missions: check if everyones now played everyone else:
	 If so, reseed. 
	Check if everyones now played 4 missions: 
	if so move period, update planes, force players to change planes if their now unavailable.

	 
	 - reseed once each player has played everyone else (Including new players added before last reseed)
	-Once everyone's played 4 missions: move periods, update planes, force players to change planes if their now unavailable.
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	
	public String getName() { return name; } //get name of campaign
	public String getDate() { return date; } //get date of creation
	public List<AirForceName> getAirForceNames() { return event.getAirForceNames(); } //get air forces involved
	public List<Period> getPeriods() { return event.getPeriods(); } //get periods of history covered
	//////////////////////public static Period getPeriod() { return period; }
	
	
	//+++++++++++++++++++++++++++++++++++++++++++++TEST methods below:
	 public void getPeriodTEST() { 
		 movePeriod();
		// currPeriod = periods.get(0);
		//System.out.println("period is: " + period);
	}	
	 
	public void updateTEST() {
		//updatePlayers();
		movePeriod();
		getMissions();
		
	}
	
	public void getMissions(){
		//missionToPlayers = new MissionBuilder
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++
	
	//advance period to following period of history
	private void movePeriod() {
		
		if(periodsIterator.hasNext()) {
			period = periodsIterator.next();
			System.out.println(period); //++++++++++++++
		}
		else {  
			System.out.println("end of periods - game is over"); //++++++++++++++
		}
	}
		
	
	public void test() {
		for (Entry<String, Player> entry : nameToPlayer.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		
		//+++++++++++++++++++
		//MissionBuilder.getMissions(new ArrayList<Player>(nameToPlayer.values()));
		//MissionBuilder.getMissions(new ArrayList<String>(nameToPlayer.keySet()));
		MissionBuilder test = new MissionBuilder(new ArrayList<String>(nameToPlayer.keySet()));
		test.setUnpairedPlayers();
		test.pairPlayers();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void setMissions(){
		
		//randomly assign pairings
		//create missions for each pairing, add to list of missions
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	

}
