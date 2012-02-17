package org.bismo.tv;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.widget.ImageView;

public class BismoTVActivity extends Activity {
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID);
        
        setContentView(R.layout.main);
        
        ImageView img_v=(ImageView)this.findViewById(R.id.barcode_img);
    }
}