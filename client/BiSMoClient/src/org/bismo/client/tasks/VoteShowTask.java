package org.bismo.client.tasks;

import org.bismo.client.ApplicationController;
import org.bismo.client.api.BiSMoApi;
import org.bismo.client.widgets.ShowListFragment;

import android.os.AsyncTask;

public class VoteShowTask extends AsyncTask<Integer, Void, Boolean> {
		private ShowListFragment mFragment;
		private ApplicationController ac;
		
		public VoteShowTask(ApplicationController ac, ShowListFragment fragment) {
			// TODO Auto-generated constructor stub
			mFragment = fragment;
			this.ac = ac;
		}
		
		@Override
		protected Boolean doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			return BiSMoApi.voteShow(ac, params[0].intValue());
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				GetShowsTask task = new GetShowsTask(mFragment, ac);
				task.execute();
			}
		}
	}