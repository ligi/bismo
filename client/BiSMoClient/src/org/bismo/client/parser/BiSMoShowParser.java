package org.bismo.client.parser;

import java.util.ArrayList;

import org.bismo.client.models.BiSMoShow;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BiSMoShowParser {
	
	public BiSMoShow parse(JSONObject json) throws JSONException {
		BiSMoShow show = new BiSMoShow();
		if (json.has("appId")) {
			show.setAppId(json.getString("appId"));
		}
		
		if (json.has("totalVotes")) {
			show.setTotalVotes(json.getInt("totalVotes"));
		}
		
		if (json.has("name")) {
			show.setShowTitle(json.getString("name"));
		}

		if (json.has("showId")) {
			show.setShowId(json.getInt("showId"));
		}

		if (json.has("showDuration")) {
			show.setShowDuration(json.getInt("showDuration"));
		}
		
		return show;
	}
	
	public ArrayList<BiSMoShow> parseShows(JSONObject json) throws JSONException{
		ArrayList<BiSMoShow> mShows = new ArrayList<BiSMoShow>();
		JSONArray shows;
		
		if(json.has("shows")){
			shows = json.getJSONArray("shows");
			for(int i=0;i<shows.length();i++){
				mShows.add(parse(shows.getJSONObject(i)));
			}
		}
		return mShows;
	}
}
