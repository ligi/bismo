package org.bismo.client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
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
        
        
        String tvId = null;
        Uri data = getIntent().getData();
        if (data != null) {
        	tvId = data.toString();
        	tvId = tvId.replace("http://bismoapp.appspot.com/tv/", "");
        	if (tvId != null) {
        		editor.putString("serverId", tvId);
        		ac.tvId = tvId;
        		
        		Intent showList = new Intent(getApplicationContext(), BiSMoShowList.class);
        		startActivity(showList);
    		}
		}
        
        Intent showList = new Intent(getApplicationContext(), BiSMoShowList.class);
		startActivity(showList);
    }
}