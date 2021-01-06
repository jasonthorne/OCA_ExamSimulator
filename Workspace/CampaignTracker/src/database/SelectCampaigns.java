package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.Campaign;
import model.Campaign.CampaignBuilder;
import model.Event;
import model.Period;
import model.Event.EventBuilder;
import model.Period.Block;
import model.Plane.Status;
import model.Player;

public interface SelectCampaigns {
	
	public static List<Campaign> select() {
		
		List<Campaign>campaigns = new ArrayList<>(); //list for campaigns
		
		try (Connection connection = ConnectDB.getConnection();  //connect to DB
				
			//statements for selecting campaigns and their players:
			CallableStatement campaignsStatement = connection.prepareCall("{CALL select_campaigns()}");
			CallableStatement playerNamesStatement = connection.prepareCall("{CALL select_player_names(?)}"); /** +++++++++++should just give player names! */
			ResultSet campaignsRS = campaignsStatement.executeQuery(); ) { //execute campaigns statement
			
			ResultSet playerNamesRS = null; //player names result set
			
			while(campaignsRS.next()) {
				
				//create campaign builder:
				CampaignBuilder campaignBuilder = new Campaign.CampaignBuilder(); 
				campaignBuilder.setId(campaignsRS.getInt("campaign_ID")); //set id
				campaignBuilder.setCreated(campaignsRS.getTimestamp("date_time")); //set created
				campaignBuilder.setHostName(campaignsRS.getString("host_name")); //set host name
				
				//set period:
				campaignBuilder.setPeriod((new Period(
						Block.valueOf(campaignsRS.getString("period_block").toUpperCase()),
						campaignsRS.getInt("period_year")))); 
				
				//create event builder:
				EventBuilder eventBuilder = new Event.EventBuilder(); 
				eventBuilder.setName(campaignsRS.getString("event_name")); //set event name
				eventBuilder.setMaxTurns(campaignsRS.getInt("periods_count")); //set max turns
				campaignBuilder.setEvent(eventBuilder.build()); //add event to campaign builder
				
				//+++++++++++++++++++++++++++++++++++++++++++++++
				
				//if user was found in campaign:
				/*if(!resultSet.getString("user_name").equals("N/A")) {
					//++++++++++++++++++++++++++++++++++++just get ALL player names here, and add to list. 
					//add player with name to players:
					campaignBuilder.setPlayer(
							new Player.PlayerBuilder()  // #WHY IS THIS A PLAYER AND NOT JUST A STRING???
								.setName(resultSet.getString("user_name")).build());
				}*/
				
				//set statement input with campaign id:
				playerNamesStatement.setInt(1, campaignsRS.getInt("campaign_ID")); 
				playerNamesRS = playerNamesStatement.executeQuery(); //execute player names query
				
				while(playerNamesRS.next()) {
					//add player with user name to campaign::
					campaignBuilder.setPlayer(playerNamesRS.getString("name"));
				}
				
				//+++++++++++++++++++++++++++++++++++++++++++++++
				
				//add built campaign to campaigns:
				campaigns.add(campaignBuilder.build());
			}
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return campaigns; //return campaigns
	}
}