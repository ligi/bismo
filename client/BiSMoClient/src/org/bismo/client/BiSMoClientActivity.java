package org.bismo.client;

import org.bismo.client.api.BiSMoApi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;


public class BiSMoClientActivity extends Activity {
	private ApplicationController ac;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ac = (ApplicationController)getApplication();
        
        final String client_id = Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID);
        
        SharedPreferences prefs = getSharedPreferences("bismo", MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putString("uuid", client_id);
        ac.clientId = client_id;
//        ac.tvId = "8fe695a3f0dd2e5f";
//        
//        Intent showList = new Intent(getApplicationContext(), BiSMoShowList.class);
//		startActivity(showList);
        
        String tvId = null;
        Uri data = getIntent().getData();
        if (data != null) {
        	tvId = data.toString();
        	tvId = tvId.replace("http://bismoapp.appspot.com/tv/", "");
        	if (tvId != null) {
        		editor.putString("serverId", tvId);
        		ac.tvId = tvId;
        		new RegisterTask().execute();
    		}
		}
        
    }
    
    
    private class RegisterTask extends AsyncTask<String, Void, Boolean> {
		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			return BiSMoApi.registerTv(ac);
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			if (result) {
				Intent showList = new Intent(getApplicationContext(), BiSMoShowList.class);
	    		startActivity(showList);
			}
		}
    }
}