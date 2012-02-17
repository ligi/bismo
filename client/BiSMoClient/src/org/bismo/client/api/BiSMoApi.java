package org.bismo.client.api;

import java.util.ArrayList;
import java.util.Vector;

import org.bismo.client.ApplicationController;
import org.bismo.client.http.RestClient;
import org.bismo.client.models.BiSMoShow;

import android.util.Log;

public class BiSMoApi {
	
	public static final String URL_BASIC = "http://bismoapp.appspot.com/";
	public static final String URL_TV = "tv/";
	public static final String URL_VOTE = "vote/";
	public static final String URL_SHOWS = "shows/";
	
	public static void registerTv(String id,ApplicationController ac){
		RestClient client = new RestClient(URL_BASIC+URL_TV+ac.tvId);
		client.AddHeader("client_id", ac.clientId);
        try {
			client.Execute(RestClient.HTTP_POST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String response = client.getResponse();
        Log.d("response", response);
	}
	
	public static BiSMoShow getNextShow(){
		
		return null;
	}
	
	public static void voteShow(ApplicationController ac,int showId){
		RestClient client = new RestClient(URL_BASIC+URL_TV+ac.tvId+"/"+URL_VOTE+showId);
		client.AddHeader("client_id", ac.clientId);
		 try {
				client.Execute(RestClient.HTTP_POST);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String response = client.getResponse();
	        Log.d("response", response);
	}
	
	public static ArrayList<BiSMoShow> getShows(ApplicationController ac){
		ArrayList<BiSMoShow> shows = new ArrayList<BiSMoShow>();
		
//		BiSMoShow show = new BiSMoShow();
//		show.setShowTitle("EyeEm Album Berlin");
//		shows.add(show);
//		
//		show = new BiSMoShow();
//		show.setShowTitle("Youporn Ramz Mum");
//		shows.add(show);
//	
//		show = new BiSMoShow();
//		show.setShowTitle("Ligi's private show");
//		shows.add(show);
//		
		RestClient client = new RestClient(URL_BASIC+URL_TV+ac.tvId+"/"+URL_SHOWS);
		client.AddHeader("client_id", ac.clientId);
		 try {
				client.Execute(RestClient.HTTP_GET);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String response = client.getResponse();
	        Log.d("response", response);
		return shows;
	}
	
	

}
