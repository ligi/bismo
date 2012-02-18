package org.bismo.client.tasks;

import org.bismo.client.ApplicationController;
import org.bismo.client.BiSMoShowList;
import org.bismo.client.api.BiSMoApi;
import org.bismo.client.models.BiSMoShow;

import android.os.AsyncTask;

public class GetNextShowTask extends AsyncTask<String, Void, BiSMoShow> {

	private ApplicationController ac;
	private BiSMoShowList mActivity;
	
	public GetNextShowTask(ApplicationController ac, BiSMoShowList activity) {
		mActivity = activity;
		this.ac = ac;
	}
	
	@Override
	protected BiSMoShow doInBackground(String... params) {
		// TODO Auto-generated method stub
		return BiSMoApi.getNextShow(ac);
	}
	
	@Override
	protected void onPostExecute(BiSMoShow result) {
		if (result!=null) {
			mActivity.setNextShowTitle(result.getShowTitle());
		}
	}
}