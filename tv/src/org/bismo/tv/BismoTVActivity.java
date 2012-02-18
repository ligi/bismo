package org.bismo.tv;

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class BismoTVActivity extends BaseActivity {
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

    }
    
    
    @Override
	protected void onResume() {
    	super.onResume();
    	
    	new RegisterAtServerTask().execute();
        
        this.startActivity(new Intent(this,BetweenScreenActivity.class));
		
		finish();
	}



	class RegisterAtServerTask extends AsyncTask<Void,Void,Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Log.w("BismoREST"," start2");
				RestClient rc=new RestClient("https://bismoapp.appspot.com/tv/"+getTVID());
				rc.Execute(RestClient.HTTP_POST);
				Log.i("BismoREST" , " reg response " + rc.getResponse());

				
				
				//new JSONObject (RESTHelper.doREST("https://bismoapp.appspot.com/show?tvId=" + getTVID() + "&showName=foo&appId=bar&showDuration=10",RESTHelper.METHOD_POST ));
				
				rc=new RestClient("https://bismoapp.appspot.com/tv/" + getTVID() + "/shows");
				rc.Execute(RestClient.HTTP_GET);
				
				Log.i("BismoREST" , " shows  " + rc.getResponse());
				
				JSONArray shows_json= new JSONObject (rc.getResponse()).getJSONArray("shows");
				HashSet<String> hs=new HashSet<String>();
				
				for (int i=0;i<shows_json.length();i++) 
					hs.add(shows_json.getJSONObject(i).getString("appId"));
				
				for (Show s:shows) 
					if (!hs.contains(s.getIntentAction())) { // if the shows are not existing - close them
						rc=new RestClient("https://bismoapp.appspot.com/show");
						rc.AddParam("tvId",getTVID());
						rc.AddParam("showName",s.getName());
						rc.AddParam("appId",s.getIntentAction());
						rc.AddParam("showDuration","10");
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
    
}