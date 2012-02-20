package org.noif.webview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class NOIFWebViewActivity extends Activity {
	public final static int PAUSE_TIME=30000; // in ms
	private ProgressBar progress;
	private long pause_start;
	private Handler hndl;
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
        String url="http://ligi.de";
        
        if ((extras!=null)&&(!extras.getString("PARAM").equals(""))) {
        	url = extras.getString("PARAM");
        }
        setContentView(R.layout.main);
        WebView wv=(WebView)this.findViewById(R.id.webview);
        wv.loadUrl(url);
        
        progress=(ProgressBar)findViewById(R.id.progress_bar);
        progress.setMax(PAUSE_TIME);
        
        pause_start=System.currentTimeMillis();
        
        hndl=new Handler();
        
        new Thread(new ProgressUpdaterThread()).start();
    }
    
    public void done() {
    	finish();
    }
    
    public class ProgressUpdaterThread implements Runnable {
		
		@Override
		public void run() {
			while ((System.currentTimeMillis()-pause_start)<PAUSE_TIME) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {		}
				
				hndl.post(new ProgressUpdater());
			}	
			done();
		}
	}

    public class ProgressUpdater implements Runnable {
		@Override
		public void run() {
			progress.setProgress(progress.getMax()-(int)(System.currentTimeMillis()-pause_start));
		}
	}
    
}