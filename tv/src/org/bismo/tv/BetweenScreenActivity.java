package org.bismo.tv;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BetweenScreenActivity extends BaseActivity {
    
	public final static int PAUSE_TIME=30000; // in ms
	
	private ProgressBar progress;
	private long pause_start;
	private Handler hndl;
	private TextView next_tv;
	
	class Show {
		private String name;
		private String intent_action;
		private String param;

		public Show(String name,String intent_action,String param) {
			this.name=name;
			this.intent_action=intent_action;
			this.param=param;
		}
				
		public String getName(){
			return name;
		}
		
		public String getIntentAction() {
			return intent_action;
		}

		public String getParam() {
			return param;
		}
	}
	
	
	public Show[] shows = new Show[] {
			new Show("GobanDroid ","org.ligi.gobandroid.NOIF",""),
			new Show("EyeEmTV","com.eyeem.tv.NOIF",""),
			new Show("TwitterWall","org.twitterwall.show","")
	};
	
	private int act_show_pos=0;
	private Show act_show;
	
	public void prepare_next() {
		
		act_show=shows[act_show_pos];
		
		next_tv.setText(act_show.getName());
		
		if (act_show_pos<shows.length-1)
			act_show_pos++;
		else
			act_show_pos=0;
		
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ImageLoader img_loader= ImageLoader.getInstance();
        img_loader.init(ImageLoaderConfiguration.createDefault(this));
        
        String device_id=Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID);
        
        setContentView(R.layout.main);
        
        ImageView img_v=(ImageView)this.findViewById(R.id.barcode_img);
        
        img_loader.displayImage("http://chart.apis.google.com/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=http%3A%2F%2Fbismoapp.appspot.com/tv/"+device_id, img_v);

        progress=(ProgressBar)findViewById(R.id.progress_bar);
        progress.setMax(PAUSE_TIME);
        
        pause_start=System.currentTimeMillis();
        
        hndl=new Handler();
        
        next_tv=(TextView)this.findViewById(R.id.nextshow);
        
        prepare_next();
        
        new Thread(new ProgressUpdaterThread()).start();
	}

	class ProgressUpdaterThread implements Runnable {
		
		@Override
		public void run() {
			while ((System.currentTimeMillis()-pause_start)<PAUSE_TIME) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {		}
				
				hndl.post(new ProgressUpdater());
			}
			startNext();		
		}
		
	}
	
	public void startNext() {
		this.startActivityForResult(new Intent(act_show.getIntentAction()),0);
	}
	
	class ProgressUpdater implements Runnable {
		@Override
		public void run() {
			progress.setProgress(progress.getMax()-(int)(System.currentTimeMillis()-pause_start));
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		pause_start=System.currentTimeMillis();
		prepare_next();
		new Thread(new ProgressUpdaterThread()).start();
	}
    
}