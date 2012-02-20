package org.bismo.client.tasks;

import java.util.ArrayList;

import org.bismo.client.ApplicationController;
import org.bismo.client.BiSMoShowList;
import org.bismo.client.api.BiSMoApi;
import org.bismo.client.models.BiSMoShow;
import org.bismo.client.widgets.ShowListFragment;

import android.os.AsyncTask;

public class GetShowsTask extends AsyncTask<String, Void, ArrayList<BiSMoShow>> {
		private ShowListFragment fragment;
		private ApplicationController ac;
		private Exception mException;
	
		@Override
		protected ArrayList<BiSMoShow> doInBackground(String... params) {
			// TODO Auto-generated method stub
			try{
				return BiSMoApi.getShows(ac);
			}catch (Exception e) {
				mException = e;
				return null;
			}
		}
		
		public GetShowsTask(ShowListFragment fragment,ApplicationController ac) {
			// TODO Auto-generated constructor stub
			this.fragment = fragment;
			this.ac = ac;
		}
		
		@Override
		protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
			fragment.mActivity.progress.setMessage("Loading Shows...");
			fragment.mActivity.progress.show();
			
		}
		
		@Override
		protected void onPostExecute(ArrayList<BiSMoShow> result) {
			fragment.mActivity.progress.dismiss();
			
			if (mException != null) {
				mException = null;
				//TODO:handle Exception
			}else{
				fragment.setShows(result);
			}
		}
	}