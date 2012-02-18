package org.bismo.client.tasks;

import org.bismo.client.ApplicationController;
import org.bismo.client.api.BiSMoApi;
import org.bismo.client.models.BiSMoShow;

import android.os.AsyncTask;

public class AddShowTask extends AsyncTask<String, Void, BiSMoShow> {

	private ApplicationController ac;
	
	public AddShowTask(ApplicationController ac) {
		this.ac = ac;
	}
	
	@Override
	protected BiSMoShow doInBackground(String... params) {
		return BiSMoApi.addShow(ac, params[0]);
	}

	@Override
	protected void onPostExecute(BiSMoShow result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}
}