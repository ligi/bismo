package org.bismo.client;

import org.bismo.client.http.RestClient;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Log;


public class BiSMoClientActivity extends Activity {
    
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final String android_id = Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID);
        
        SharedPreferences prefs = getSharedPreferences("bismo", MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putString("uuid", android_id);
        
        String id = null;
        Uri data = getIntent().getData();
        if (data != null) {
        	id = data.toString();
        	id = id.replace("http://bismoapp.appspot.com/tv/", "");
		}
        
        if (id != null) {
        	Log.d("received id", id);
		}
    }
    
    private void startServer(){
    	RestClient client = new RestClient("http://bismoapp.appspot.com/ping");
        try {
			client.Execute(RestClient.HTTP_GET);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String response = client.getResponse();
        Log.d("response", response);
    	
    }
    
    
    
}