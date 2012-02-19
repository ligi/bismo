package org.bismo.client.tasks;

import org.bismo.client.ApplicationController;
import org.bismo.client.BiSMoClientActivity;
import org.bismo.client.api.BiSMoApi;
import org.bismo.client.models.BiSMoShow;

import android.os.AsyncTask;

public class AddShowTask extends AsyncTask<String, Void, BiSMoShow> {

	private ApplicationController ac;
	private BiSMoClientActivity mActivity;
	private Exception mException=null;
	
	public AddShowTask(ApplicationController ac, BiSMoClientActivity mActivity) {
		this.ac = ac;
		this.mActivity = mActivity;
	}
	
	@Override
	protected BiSMoShow doInBackground(String... params) {
		try{
			return BiSMoApi.addShow(ac, params[0]);
		}catch (Exception e) {
			mException = e;
			return null;
		}
	}

	@Override
	protected void onPostExecute(BiSMoShow result) {
		// TODO Auto-generated method stub
		if (mException != null) {
			//TODO:handle Exception
			mException = null;
		}
		mActivity.userMessage(result);
	}
}