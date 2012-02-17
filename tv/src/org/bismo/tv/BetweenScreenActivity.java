package org.bismo.tv;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class BetweenScreenActivity extends BaseActivity {
    
	public final static int PAUSE_TIME=30000; // in ms
	private ProgressBar progress;
	private long pause_start;
	private Handler hndl;
	
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
		}

		
	}
	class ProgressUpdater implements Runnable {
		@Override
		public void run() {
			progress.setProgress(progress.getMax()-(int)(System.currentTimeMillis()-pause_start));
		}
	}
	
    
}