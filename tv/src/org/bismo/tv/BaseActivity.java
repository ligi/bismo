package org.bismo.tv;

import android.app.Activity;
import android.provider.Settings.Secure;

public class BaseActivity extends Activity {

	public final static Show[] shows = new Show[] {
			//	new Show("VideoPlayer ","org.apache.android.media.show","http://daily3gp.com/vids/747.3gp"),
			//new Show("TwitterWall (#eyeem)","org.twitterwall.show","%23eyeem")//		
			//new Show("TwitterWall (#bhnt)","org.twitterwall.show","%23bhnt")
			//new Show("TwitterWall (#cbase)","org.twitterwall.show","%23cbase")
		new Show("Conference Schedule (droidcon)","org.conschedtv.show","droidcon"),	
		new Show("TwitterWall (#droidcon)","org.twitterwall.show","%23droidcon")
			//,new Show("TwitterWall (#android)","org.twitterwall.show","%23android")
			,new Show("G+Wall (droidcon)","org.gpluswall.show","droidcon")
			
		,new Show("Frapny","com.frapny.NOIF","Frapny")
			//,new Show("G+Wall (android)","org.gpluswall.show","android")
			///,new Show("DroidCon Website","org.NOIF.webview","http://de.droidcon.com/")
			,new Show("Jemeinsam Droidcon 2012","org.NOIF.webview","http://jemeinsam.appspot.com/e/154199?j=1331626672&g=88057")
			//,new Show("PhotoHackDay Website","org.NOIF.webview","http://photohackday.com/")
			//,new Show("Ligi's Blog ","org.NOIF.webview","http://ligi.de")
			//,new Show("Dev Camp Blog","org.NOIF.webview","http://android-dev-camp-2012.blogspot.com/")
			//,new Show("Hackatron","org.cbase.hackatron.NOIF","")
			//,new Show("C-Base Calendar","org.NOIF.webview","http://www.c-base.org/cal")
			
			,new Show("EyeEmTV DroidCon","com.eyeem.tv.NOIF","139007")
			,new Show("EyeEmTV C-Base","com.eyeem.tv.NOIF","16987")
			//,new Show("Android in Berlin Blog","org.NOIF.webview","http://www.android-in-berlin.de/2012/02/android-stammtisch-am-29022012.html")
			//
			//,new Show("Hack&Tell Website","org.NOIF.webview","http://www.meetup.com/berlin-hack-and-tell")
			//,new Show("EyeEmTV PhotoHackday","com.eyeem.tv.NOIF","125363")
			//,new Show("EyeEmTV Cbase Hackathon","com.eyeem.tv.NOIF","120557")
			//,new Show("EyeEmTV Hack&Tell","com.eyeem.tv.NOIF","127448")
			//,new Show("EyeEmTV Berlin","com.eyeem.tv.NOIF","17")
			//,new Show("SoundCloudTV","org.NOIF.soundcloud","http://soundcloud.com/lockets/camera-shy")
			//,new Show("EyeEmTV PhotoHackDay","com.eyeem.tv.NOIF","125363")
			//,new Show("TwitterWall (photohackday)","org.twitterwall.show","photohackday")//
			//,new Show("VideoPlayer ","org.apache.android.media.show","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
			//,new Show("GobanDroid ","org.ligi.gobandroid.NOIF","http://gogameguru.com/i/2012/02/Wang-Chenxing-vs-Yashiro-Kumiko-20120206.sgf")
	};
	
	public String getTVID() { 
		return "droidcon";//Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID)+"n1_hack9";
	}
    
}
