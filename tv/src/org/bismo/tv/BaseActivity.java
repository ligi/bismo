package org.bismo.tv;

import android.app.Activity;
import android.provider.Settings.Secure;

public class BaseActivity extends Activity {

	public final static Show[] shows = new Show[] {
			//	new Show("VideoPlayer ","org.apache.android.media.show","http://daily3gp.com/vids/747.3gp"),
			new Show("TwitterWall (#eyeem)","org.twitterwall.show","eyeem")//		
			//,new Show("PhotoHackDay Website","org.NOIF.webview","http://photohackday.com/")
			//,new Show("Ligi's Blog ","org.NOIF.webview","http://ligi.de")
			//,new Show("SoundCloudTV","org.NOIF.soundcloud","http://soundcloud.com/lockets/camera-shy")
			//,new Show("Dev Camp Blog","org.NOIF.webview","http://android-dev-camp-2012.blogspot.com/")
			//,new Show("Hackatron","org.cbase.hackatron.NOIF","")
			,new Show("EyeEmTV PhotoHackday","com.eyeem.tv.NOIF","125363")
			,new Show("EyeEmTV Cbase Hackathon","com.eyeem.tv.NOIF","120557")
			,new Show("EyeEmTV Berlin","com.eyeem.tv.NOIF","17")
			
			//,new Show("VideoPlayer ","org.apache.android.media.show","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
			//,new Show("GobanDroid ","org.ligi.gobandroid.NOIF","http://gogameguru.com/i/2012/02/Wang-Chenxing-vs-Yashiro-Kumiko-20120206.sgf")
	};
	
	public String getTVID() { 
		return Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID)+"new2";
	}
    
}
