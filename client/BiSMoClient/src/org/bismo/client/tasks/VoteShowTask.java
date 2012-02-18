package org.bismo.client.tasks;

import org.bismo.client.ApplicationController;
import org.bismo.client.api.BiSMoApi;
import org.bismo.client.widgets.ShowListAdapter;

import android.os.AsyncTask;

public class VoteShowTask extends AsyncTask<Integer, Void, Boolean> {
		private ShowListAdapter mAdapter;
		private ApplicationController ac;
		
		public VoteShowTask(ApplicationController ac, ShowListAdapter adapter) {
			// TODO Auto-generated constructor stub
			mAdapter = adapter;
			this.ac = ac;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				GetShowsTask task = new GetShowsTask(mAdapter, ac);
				task.execute();
			}
		}

		@Override
		protected Boolean doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			return BiSMoApi.voteShow(ac, params[0].intValue());
		}
	}