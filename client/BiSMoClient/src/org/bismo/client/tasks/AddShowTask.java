package org.bismo.client.tasks;

import org.bismo.client.ApplicationController;
import org.bismo.client.BiSMoClientActivity;
import org.bismo.client.api.BiSMoApi;
import org.bismo.client.models.BiSMoShow;

import android.drm.DrmStore.Action;
import android.os.AsyncTask;

public class AddShowTask extends AsyncTask<String, Void, BiSMoShow> {

	private ApplicationController ac;
	private BiSMoClientActivity mActivity;
	
	public AddShowTask(ApplicationController ac, BiSMoClientActivity mActivity) {
		this.ac = ac;
		this.mActivity = mActivity;
	}
	
	@Override
	protected BiSMoShow doInBackground(String... params) {
		return BiSMoApi.addShow(ac, params[0]);
	}

	@Override
	protected void onPostExecute(BiSMoShow result) {
		// TODO Auto-generated method stub
		mActivity.userMessage(result);
	}
}