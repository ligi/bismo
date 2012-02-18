package org.bismo.client;

import android.app.Application;
import android.content.SharedPreferences;

public class ApplicationController extends Application{
	public String clientId;
	public String tvId;
	
	public String getLastKnownTVId(){
		SharedPreferences prefs = getSharedPreferences("bismo", MODE_PRIVATE);
		return prefs.getString("tvId", "-1");
	}

}
