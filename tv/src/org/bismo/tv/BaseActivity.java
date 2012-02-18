package org.bismo.tv;

import android.app.Activity;
import android.provider.Settings.Secure;

public class BaseActivity extends Activity {

	public final static Show[] shows = new Show[] {
		//	new Show("VideoPlayer ","org.apache.android.media.show","http://daily3gp.com/vids/747.3gp"),
			new Show("TwitterWall","org.twitterwall.show","")
			,new Show("EyeEmTV","com.eyeem.tv.NOIF","")
			,new Show("VideoPlayer ","org.apache.android.media.show","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
			,new Show("GobanDroid ","org.ligi.gobandroid.NOIF","")
	};
	
	public String getTVID() { 
		return Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID);
	}
    
}
