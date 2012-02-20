package org.bismo.client.tasks;

import java.util.ArrayList;

import org.bismo.client.ApplicationController;
import org.bismo.client.R;
import org.bismo.client.api.BiSMoApi;
import org.bismo.client.models.BiSMoShow;
import org.bismo.client.util.BismoHelper;
import org.bismo.client.widgets.ShowListFragment;

import android.os.AsyncTask;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class VoteShowTask extends AsyncTask<Integer, Void, ArrayList<BiSMoShow>> {
		private ShowListFragment mFragment;
		private ApplicationController ac;
		private Exception mException;
		private int showId;
		
		public VoteShowTask(ApplicationController ac, ShowListFragment fragment) {
			// TODO Auto-generated constructor stub
			mFragment = fragment;
			this.ac = ac;
		}
		
		@Override
		protected  ArrayList<BiSMoShow> doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			showId = params[0].intValue();
			try{
				return BiSMoApi.voteShow(ac, params[0].intValue());
			}catch (Exception e) {
				mException = e;
				return null;
			}
		}
		
		@Override
		protected void onPostExecute( ArrayList<BiSMoShow> result) {
			if (mException != null) {
				//TODO:handle Exception
				mException = null;
			}else{
				if (result!=null) {
					mFragment.setShows(result);
					for (int i = 0; i < result.size(); i++) {
						
						if (result.get(i).getShowId() == showId) {
							mFragment.getListView().setSelection(i);
							BismoHelper.LAST_SELECTED_SHOW_INDEX=i;
							break;
						}
					}
				}
			}
		}
	}