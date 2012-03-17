package org.bismo.tv;

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class RegisterAtServerTask extends AsyncTask<Void,Void,Void> {

		private BaseActivity ctx;
		
		public RegisterAtServerTask(BaseActivity ctx) {
			this.ctx=ctx;
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Log.w("BismoREST"," start2");
				RestClient rc=new RestClient(BismoConfig.SERVER_URL+"/tv/"+ctx.getTVID());
				rc.Execute(RestClient.HTTP_POST);
				Log.i("BismoREST" , " reg response" + rc.getResponse());
				
				
				//new JSONObject (RESTHelper.doREST("https://bismoapp.appspot.com/show?tvId=" + getTVID() + "&showName=foo&appId=bar&showDuration=10",RESTHelper.METHOD_POST ));
				
				rc=new RestClient(BismoConfig.SERVER_URL+"/tv/" + ctx.getTVID() + "/shows");
				rc.Execute(RestClient.HTTP_GET);
				
				Log.i("BismoREST" , " shows  " + rc.getResponse());
				
				JSONArray shows_json= new JSONObject (rc.getResponse()).getJSONArray("shows");
				HashSet<String> hs=new HashSet<String>();
				
				for (int i=0;i<shows_json.length();i++) 
					hs.add(shows_json.getJSONObject(i).getString("name"));
				
				for (Show s:ctx.shows) 
					if (!hs.contains(s.getName())) { // if the shows are not existing - close them
						rc=new RestClient(BismoConfig.SERVER_URL+"/show");
						rc.AddParam("tvId",ctx.getTVID());
						rc.AddParam("showName",s.getName());
						rc.AddParam("appId",s.getIntentAction());
						rc.AddParam("showDuration","10");
						rc.AddParam("showParameter",s.getParam());
						rc.Execute(RestClient.HTTP_POST);
						Log.i("BismoREST" , " add response " + rc.getResponse());
					}
				
				
				Log.i("BismoREST" , " shows next  " + rc.getResponse());
				
			} catch (Exception e) {
				Log.w("BismoREST"," err"+e);
			}
			return null;
		}
    	
    }