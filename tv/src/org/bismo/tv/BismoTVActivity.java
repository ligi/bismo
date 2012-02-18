package org.bismo.tv;

import android.content.Intent;
import android.os.Bundle;

public class BismoTVActivity extends BaseActivity {
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       
       this.startActivity(new Intent(this,BetweenScreenActivity.class));
              
    }
}