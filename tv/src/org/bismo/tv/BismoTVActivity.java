package org.bismo.tv;

import android.content.Intent;
import android.os.Bundle;

public class BismoTVActivity extends BaseActivity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
    }
    
    
    @Override
	protected void onResume() {
    	super.onResume();
    	
    	new RegisterAtServerTask(this).execute();
        
        this.startActivity(new Intent(this,BetweenScreenActivity.class));
		
		finish();
	}
    
}