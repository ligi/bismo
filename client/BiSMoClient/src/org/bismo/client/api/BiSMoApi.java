package org.bismo.client.api;

import java.util.ArrayList;

import org.bismo.client.ApplicationController;
import org.bismo.client.http.RestClient;
import org.bismo.client.models.BiSMoShow;
import org.bismo.client.parser.BiSMoShowParser;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class BiSMoApi {
	
	public static final String URL_BASIC = "http://bismoapp.appspot.com/";
	public static final String URL_TV = "tv/";
	public static final String URL_VOTE = "vote/";
	public static final String URL_SHOW = "show/";
	public static final String URL_SHOWS = "shows";
	public static final String URL_CLIENT = "client/";
	public static final String URL_NEXT_SHOW = "nextShow";
	
	public static boolean registerTv(ApplicationController ac){
		RestClient client = new RestClient(URL_BASIC+URL_TV+ac.tvId+"/"+URL_CLIENT+ac.clientId);
		client.AddHeader("client_id", ac.clientId);
        try {
			client.Execute(RestClient.HTTP_POST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String response = client.getResponse();
        try {
        	JSONObject obj = new JSONObject(response);
			if (obj.get("message").equals("Client registered")) {
				return true;
			}else{
				return false;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	public static BiSMoShow getNextShow(ApplicationController ac){
		RestClient client = new RestClient(URL_BASIC+URL_TV+ac.tvId+"/"+URL_NEXT_SHOW);
		client.AddHeader("client_id", ac.clientId);
        try {
			client.Execute(RestClient.HTTP_GET);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String response = client.getResponse();
        
        BiSMoShowParser parser = new BiSMoShowParser();
        try {
			return parser.parse(new JSONObject(response));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return null;
		}
        
		
	}
	
	public static boolean voteShow(ApplicationController ac,int showId){
		RestClient client = new RestClient(URL_BASIC+URL_SHOW+showId+"/"+URL_CLIENT+ac.clientId);
		client.AddHeader("client_id", ac.clientId);
		 try {
				client.Execute(RestClient.HTTP_POST);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String response = client.getResponse();
	        
	        JSONObject obj;
			try {
				obj = new JSONObject(response);
				if (obj.get("message").equals("Vote registered")) {
					return true;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				return false;
			}
			return false;
	}
	
	public static ArrayList<BiSMoShow> getShows(ApplicationController ac){
		ArrayList<BiSMoShow> shows = new ArrayList<BiSMoShow>();
		
		RestClient client = new RestClient(URL_BASIC+URL_TV+ac.tvId+"/"+URL_SHOWS);
		client.AddHeader("client_id", ac.clientId);
		 try {
				client.Execute(RestClient.HTTP_GET);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String response = client.getResponse();
	        BiSMoShowParser parser = new BiSMoShowParser();
	        try {
	        	shows = parser.parseShows(new JSONObject(response));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        Log.d("response", response);
		return shows;
	}
	
	

}
