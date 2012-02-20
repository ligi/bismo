package org.bismo.client.tasks;

import org.bismo.client.ApplicationController;
import org.bismo.client.BiSMoShowList;
import org.bismo.client.api.BiSMoApi;
import org.bismo.client.models.BiSMoShow;

import android.os.AsyncTask;

public class GetNextShowTask extends AsyncTask<String, Void, BiSMoShow> {

	private ApplicationController ac;
	private BiSMoShowList mActivity;
	private Exception mException;
	
	public GetNextShowTask(ApplicationController ac, BiSMoShowList activity) {
		mActivity = activity;
		this.ac = ac;
	}
	
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	
	@Override
	protected BiSMoShow doInBackground(String... params) {
		// TODO Auto-generated method stub
		try{
			return BiSMoApi.getNextShow(ac);
		}catch (Exception e) {
			mException = e;
			return null;
		}
		
		
	}
	
	@Override
	protected void onPostExecute(BiSMoShow result) {
		if (mException != null) {
			//TODO:handle Exception
			mException = null;
		}else{
			if (result!=null) {
				mActivity.setNextShowTitle(result);
			}else{
				mActivity.setNextShowTitle(null);
			}
		}
	}
}