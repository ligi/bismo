package org.bismo.client;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.Secure;


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
        
        String serverId = null;
        Uri data = getIntent().getData();
        if (data != null) {
        	serverId = data.toString();
        	serverId = serverId.replace("http://bismoapp.appspot.com/tv/", "");
        	if (serverId != null) {
        		editor.putString("serverId", serverId);
    		}
		}
    }
}