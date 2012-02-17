package org.bismo.client.api;

import org.bismo.client.http.RestClient;

import android.util.Log;

public class BiSMoApi {
	
	public static final String BASIC_URL = "http://bismoapp.appspot.com/";
	public static final String TV_URL = "tv/";
	
	
	public static void sendClientId(String clientId){
		RestClient client = new RestClient(BASIC_URL+TV_URL+clientId);
        try {
			client.Execute(RestClient.HTTP_POST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String response = client.getResponse();
        Log.d("response", response);
	}
	

}
