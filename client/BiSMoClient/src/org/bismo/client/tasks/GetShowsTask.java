package org.bismo.client.tasks;

import java.util.ArrayList;

import org.bismo.client.ApplicationController;
import org.bismo.client.api.BiSMoApi;
import org.bismo.client.models.BiSMoShow;
import org.bismo.client.widgets.ShowListFragment;

import android.os.AsyncTask;

public class GetShowsTask extends AsyncTask<String, Void, ArrayList<BiSMoShow>> {
		private ShowListFragment fragment;
		private ApplicationController ac;
	
		@Override
		protected ArrayList<BiSMoShow> doInBackground(String... params) {
			// TODO Auto-generated method stub
			return BiSMoApi.getShows(ac);
		}
		
		public GetShowsTask(ShowListFragment fragment,ApplicationController ac) {
			// TODO Auto-generated constructor stub
			this.fragment = fragment;
			this.ac = ac;
		}
		
		@Override
		protected void onPostExecute(ArrayList<BiSMoShow> result) {
			fragment.setShows(result);
		}
	}