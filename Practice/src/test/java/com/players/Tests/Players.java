package com.players.Tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Players {
	HashMap<String,Integer> countryMap;
	HashMap<String,Integer> roleMap;
	@BeforeMethod()
	public void loadJsonFile() throws IOException, ParseException  {
		JSONParser jsonParser=new JSONParser();
		FileReader fileReader=new FileReader("src\\test\\java\\TestData\\Players.json");
		Object obj=jsonParser.parse(fileReader);
		JSONObject playersObject=(JSONObject)obj;
		//System.out.println(empjsonobject.get("name"));
		JSONArray array=(JSONArray)playersObject.get("player");
		countryMap=new HashMap<String,Integer>();
		roleMap=new HashMap<String,Integer>();
		for(int i=0;i<array.size();i++) {
			JSONObject players =(JSONObject)array.get(i);
			String countryDetails=(String) players.get("country");
			String role=(String) players.get("role");
			//storing the roles data
			if(roleMap.containsKey(role)) {
				int countRole=roleMap.get(role)+1;
				roleMap.put(role, countRole++);
			}
			else {
				roleMap.put(role, 1);
			}
			
			//storing the  country data
			if(countryMap.containsKey(countryDetails)) {
				int countCountry=countryMap.get(countryDetails)+1;
				countryMap.put(countryDetails, countCountry++);
			}
			else {
				countryMap.put(countryDetails, 1);
			}
			
		}
	}
	
	@Test()
	public void verifyTotalForeighPlayers() {
		int countForeighPlayers = 0;
		for (Map.Entry<String, Integer> entry : countryMap.entrySet()) {
		    String key = entry.getKey();
		    if(!key.equals("India")) {
		    	countForeighPlayers=countForeighPlayers+entry.getValue();
		    }
		}
		if(countForeighPlayers <= 4) {
			Assert.assertTrue(true, "Max 4 foreigh players can play");
			
		}
		else {
			Assert.assertFalse(true, "Max 4 foreigh players can play");	
		}
	}
	
	@Test()
	public void verifyAtLeastOneWicketKeeper() {
		int countRole = 0;
		for (Map.Entry<String, Integer> entry : roleMap.entrySet()) {
		    String key = entry.getKey();
		    if(key.equals("Wicket-keeper")) {
		    	countRole=countRole+entry.getValue();
		    }
		}
		if(countRole >= 1) {
			Assert.assertTrue(true, "Minimum One Wicket Keeper");
			
		}
		else {
			Assert.assertFalse(true, "Minimum One Wicket Keeper");	
		}
	}
	
}
