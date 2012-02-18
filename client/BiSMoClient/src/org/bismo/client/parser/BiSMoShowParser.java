package org.bismo.client.parser;

import java.util.ArrayList;

import org.bismo.client.models.BiSMoShow;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BiSMoShowParser {
	
	public BiSMoShow parse(JSONObject json) throws JSONException {
		BiSMoShow show = new BiSMoShow();
		
		
		return show;
	}
	
	public ArrayList<BiSMoShow> parseShows(JSONObject json) throws JSONException{
		ArrayList<BiSMoShow> mShows = new ArrayList<BiSMoShow>();
		JSONArray shows;
		
		if(json.has("items")){
			shows = json.getJSONArray("items");
			for(int i=0;i<shows.length();i++){
				mShows.add(parse(shows.getJSONObject(i)));
			}
		}
		return mShows;
	}
}
