package org.bismo.client.api;

import java.util.ArrayList;

import org.bismo.client.ApplicationController;
import org.bismo.client.http.RestClient;
import org.bismo.client.models.BiSMoShow;
import org.bismo.client.parser.BiSMoShowParser;
import org.bismo.client.util.BismoHelper;
import org.json.JSONObject;

public class BiSMoApi {
	
	public static final String URL_BASIC = "http://bismobackend.appspot.com/";
	public static final String URL_TV = "tv/";
	public static final String URL_VOTE = "vote/";
	public static final String URL_SHOW = "show/";
	public static final String URL_SHOWS = "shows";
	public static final String URL_ADD_SHOW = "show";
	public static final String URL_CLIENT = "client/";
	public static final String URL_NEXT_SHOW = "nextShow";
	
	
	public static boolean registerTv(ApplicationController ac) throws Exception{
		RestClient client = new RestClient(URL_BASIC+URL_TV+ac.tvId+"/"+URL_CLIENT+ac.clientId);
		client.AddHeader("client_id", ac.clientId);
		client.Execute(RestClient.HTTP_POST);
        String response = client.getResponse();
    	JSONObject obj = new JSONObject(response);
		if (obj.get("message").equals("Client registered")) {
			return true;
		}else{
			return false;
		}
	}
	
	public static BiSMoShow getNextShow(ApplicationController ac) throws Exception{
		RestClient client = new RestClient(URL_BASIC+URL_TV+ac.tvId+"/"+URL_NEXT_SHOW);
		client.AddHeader("client_id", ac.clientId);
		client.Execute(RestClient.HTTP_GET);
        String response = client.getResponse();
		if (!new JSONObject(response).has("error")) {
	    	BiSMoShowParser parser = new BiSMoShowParser();
				return parser.parse(new JSONObject(response));
		}else{
			return null;
		}	
	}
	
	public static ArrayList<BiSMoShow> voteShow(ApplicationController ac,int showId) throws Exception{
		ArrayList<BiSMoShow> shows = null;
		RestClient client = new RestClient(URL_BASIC+URL_SHOW+showId+"/"+URL_CLIENT+ac.clientId);
		client.AddHeader("client_id", ac.clientId);
		client.Execute(RestClient.HTTP_POST);
	    String response = client.getResponse();
	    BiSMoShowParser parser = new BiSMoShowParser();
	    shows = parser.parseShows(new JSONObject(response));
	    return shows;
	}
	
	public static ArrayList<BiSMoShow> getShows(ApplicationController ac) throws Exception{
		ArrayList<BiSMoShow> shows = new ArrayList<BiSMoShow>();
		RestClient client = new RestClient(URL_BASIC+URL_TV+ac.tvId+"/"+URL_SHOWS);
		client.AddHeader("client_id", ac.clientId);
		client.Execute(RestClient.HTTP_GET);
	    String response = client.getResponse();
	    BiSMoShowParser parser = new BiSMoShowParser();
	    shows = parser.parseShows(new JSONObject(response));
		return shows;
	}
	
	
	public static BiSMoShow addShow(ApplicationController ac, String url) throws Exception{
		RestClient client = new RestClient(URL_BASIC+URL_ADD_SHOW);
		client.AddHeader("client_id", ac.clientId);
		client.AddParam("tvId", ac.getLastKnownTVId());
		client.AddParam("showDuration", "10");
		
		if(url.toLowerCase().endsWith(".3gp") || url.toLowerCase().endsWith(".mp4") || url.toLowerCase().endsWith(".avi") || url.toLowerCase().endsWith(".mkv")){
			client.AddParam("showName", "VideoPlayer");
			client.AddParam("showParameter",url);
			client.AddParam("appId", "org.apache.android.media.show");
		}else if(url.toLowerCase().contains("http://www.eyeem.com/s/")){
			client.AddParam("showName", "EyeEmTV");
			client.AddParam("appId", "com.eyeem.tv.NOIF");
			client.AddParam("showParameter", BismoHelper.retrieveLinks(url).get(0));
		}else if(url.toLowerCase().endsWith(".sgf")){
			client.AddParam("appId", "org.ligi.gobandroid.NOIF");
			client.AddParam("showName", "GobanDroid");
			client.AddParam("showParameter",url);
		}else{
			if (BismoHelper.retrieveLinks(url).size() == 1) {
				client.AddParam("appId", "org.NOIF.webview");
				client.AddParam("showName", "WebView");
				client.AddParam("showParameter", url);
			}else{
				return null;
			}
		}
		client.Execute(RestClient.HTTP_POST);
		String response = client.getResponse();
        BiSMoShowParser parser = new BiSMoShowParser();
        return parser.parse(new JSONObject(response));
	}
		
}
	

