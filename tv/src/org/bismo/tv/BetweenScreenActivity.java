package org.bismo.tv;

import org.json.JSONObject;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BetweenScreenActivity extends BaseActivity {
    
	public final static int PAUSE_TIME=30000; // in ms
	
	private ProgressBar progress;
	private long pause_start;
	private Handler hndl;
	private TextView next_tv;
	private Show act_show;
	public ImageLoader img_loader;
	TextView coach_barcode_tv;
	
	public void prepare_next() {
		act_show=shows[0];
		next_tv.setText("");
		new CloseVoteTask().execute();
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    	
        img_loader= ImageLoader.getInstance();
        img_loader.init(ImageLoaderConfiguration.createDefault(this));
        
        
        setContentView(R.layout.main);
        
        ImageView img_v=(ImageView)this.findViewById(R.id.barcode_img);

        
        img_loader.displayImage("http://chart.apis.google.com/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=http%3A%2F%2Fbismoapp.appspot.com/tv/"+getTVID(), img_v);

        
        progress=(ProgressBar)findViewById(R.id.progress_bar);
        progress.setMax(PAUSE_TIME);
        
        pause_start=System.currentTimeMillis();
        
        hndl=new Handler();
        
        next_tv=(TextView)this.findViewById(R.id.nextshow);
    
        coach_barcode_tv=(TextView)this.findViewById(R.id.coach_barcode_tv);
        
        
        
        TextView avail_tv=(TextView)this.findViewById(R.id.availshows_tv);
        String avail_txt="Available Shows:";
        for (Show show:shows)
        	avail_txt+="\n\t - " +show.getName();
        		
        avail_tv.setText(avail_txt);
        prepare_next();
        
        new Thread(new ProgressUpdaterThread()).start();
	}

	
	
	@Override
	protected void onResume() {
		
		super.onResume();
        ImageView img_v=(ImageView)this.findViewById(R.id.barcode_img);
        img_loader.displayImage("http://chart.apis.google.com/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=http%3A%2F%2Fbismoapp.appspot.com/tv/"+getTVID(), img_v);
        
        coach_barcode_tv.setText(R.string.scan_to_vote);
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
		Intent i=new Intent(act_show.getIntentAction());
		i.putExtra("PARAM", act_show.getParam());
		this.startActivityForResult(i,0);
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
	

	class CloseVoteTask extends AsyncTask<Void,Void,Show> {

		@Override
		protected Show doInBackground(Void... params) {
			try {
				
				RestClient rc=new RestClient("https://bismoapp.appspot.com/tv/" + getTVID()+ "/closeVoting");
				rc.Execute(RestClient.HTTP_POST);
				
				JSONObject next_show_json=new JSONObject(rc.getResponse());
				
				Log.i("BismoREST"," resp next" + rc.getResponse());
				
				Show show=new Show(next_show_json.getString("name"),next_show_json.getString("appId"),next_show_json.getString("parameter"));
				show.setTotalVotes(next_show_json.getInt("totalVotes"));
				return show;
	
			} catch (Exception e) {
				Log.w("BismoREST","  err"+e);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Show result) {
			if (result==null)
				result=shows[1];
			next_tv.setText(result.getName() + "(won with " + result.getTotalVotes() + " Votes for param: " + result.getParam() + ")");
			act_show=result;
			

	        if (act_show.getIntentAction().contains("hackatron")) {
	        	ImageView img_v_ht=(ImageView)BetweenScreenActivity.this.findViewById(R.id.barcode_img);
	        	BetweenScreenActivity.this.img_loader.displayImage("http://chart.apis.google.com/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=http%3A%2F%2Fhackatronapp.appspot.com?ip=84.22.107.42", img_v_ht);
	        	coach_barcode_tv.setText("Scan Barcode to easy participate in Hack-a-tron - give IP to GTV");                      
	        } 
					
			super.onPostExecute(result);
		}
    
		
    }
    
}