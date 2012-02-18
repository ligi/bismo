package org.bismo.client;

import org.bismo.client.api.BiSMoApi;
import org.bismo.client.models.BiSMoShow;
import org.bismo.client.tasks.AddShowTask;
import org.bismo.client.util.BismoHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.widget.Toast;


public class BiSMoClientActivity extends Activity {
	private ApplicationController ac;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        setContentView(R.layout.main);
        
        ac = (ApplicationController)getApplication();
        
        final String client_id = Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID);
        
        ac.clientId = client_id;
        ac.tvId = ac.getLastKnownTVId();
        
        if (ac.tvId != "-1") {
        	findViewById(R.id.useExistingTV).setVisibility(View.VISIBLE);
		}
        analyseIntent();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, intent);
    	
    	switch (requestCode) {
		case BismoHelper.QR_CODE_RESULT:
			analyseIntent();
			
			break;

		default:
			break;
		}
    }

	private void analyseIntent() {
//		SharedPreferences prefs = getSharedPreferences("bismo", MODE_PRIVATE);
//        Editor editor = prefs.edit();
//        editor.putString("uuid", ac.clientId).commit();
//        
//        String intentConent = null;
//        Uri data = getIntent().getData();
//        
//        if (data != null) {
//        	intentConent = data.toString();
//        	
//        	if (intentConent.contains("http://bismoapp.appspot.com/tv/")) {
//        		intentConent = intentConent.replace("http://bismoapp.appspot.com/tv/", "");
//            	if (intentConent != null) {
//            		editor.putString("tvId", intentConent).commit();
//            		ac.tvId = intentConent;
//            		new RegisterTask().execute();
//        		}
//			}
//		}else if(getIntent().getStringExtra(Intent.EXTRA_TEXT) != null){
//			new AddShowTask(ac).execute(getIntent().getStringExtra(Intent.EXTRA_TEXT));
//		}
		SharedPreferences prefs = getSharedPreferences("bismo", MODE_PRIVATE);
		Editor editor = prefs.edit();
		String intentConent = null;
		 Uri data = getIntent().getData();
		    
		    if (data != null) {
		    	intentConent = data.toString();
		    	
		    	if (intentConent.contains("http://bismoapp.appspot.com/tv/")) {
		    		intentConent = intentConent.replace("http://bismoapp.appspot.com/tv/", "");
		        	if (intentConent != null) {
		        		editor.putString("tvId", intentConent).commit();
		        		ac.tvId = intentConent;
		        		
		        		new RegisterTask().execute();
		    		}
				}else{
					Toast.makeText(getApplicationContext(), "Sorry, but we couldn't use your QR-Code. Watch out for more plugins!", Toast.LENGTH_LONG).show();
				}
			}else if(getIntent().getStringExtra(Intent.EXTRA_TEXT) != null){
				new AddShowTask(ac,this).execute(getIntent().getStringExtra(Intent.EXTRA_TEXT));
			}else{
//				Toast.makeText(getApplicationContext(), "Sorry, but we couldn't use your QR-Code. Watch out for more plugins!", Toast.LENGTH_LONG).show();
			}
	}
    
    
    public void onClick(View view){
    	switch (view.getId()) {
		case R.id.openQRCode:
			 Intent scan_intent = new Intent("com.google.zxing.client.android.SCAN");
	         scan_intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
	         startActivityForResult(scan_intent, BismoHelper.QR_CODE_RESULT);
	         break;
	         
		case R.id.useExistingTV:
			Intent showList = new Intent(getApplicationContext(), BiSMoShowList.class);
    		startActivity(showList);
    		break;

		default:
			break;
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
    
    public void userMessage(BiSMoShow show){
    	if (show == null) {
    		Toast.makeText(getApplicationContext(), "Sorry, but we couldn't use your QR-Code. Watch out for more plugins!", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(getApplicationContext(), "The show "+show.getShowTitle()+" was successfully added.", Toast.LENGTH_LONG).show();
		}
    }
}