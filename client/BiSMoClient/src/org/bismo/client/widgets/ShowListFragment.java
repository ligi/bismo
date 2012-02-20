package org.bismo.client.widgets;

import java.util.ArrayList;

import org.bismo.client.ApplicationController;
import org.bismo.client.BiSMoShowList;
import org.bismo.client.R;
import org.bismo.client.models.BiSMoShow;
import org.bismo.client.tasks.GetNextShowTask;
import org.bismo.client.tasks.VoteShowTask;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

public class ShowListFragment extends ListFragment{
	
	private ShowListAdapter mAdapter;
	private ArrayList<BiSMoShow> mShows;
	private ApplicationController ac;
	public BiSMoShowList mActivity;
	
	
	
	public ShowListFragment(ApplicationController ac, BiSMoShowList mActivity) {
		// TODO Auto-generated constructor stub
		this.ac = ac;
		this.mActivity = mActivity;
	}
	
	public ShowListFragment() {
		
	}
	
	public void setShows(ArrayList<BiSMoShow> shows){
		this.mShows = shows;
		mAdapter.clear();
		
		for(BiSMoShow show : shows){
			mAdapter.add(show);
		}
		mAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		VoteShowTask voteTask = new VoteShowTask(ac, this);
		voteTask.execute(mShows.get(position).getShowId());
		
		GetNextShowTask nextShowTask = new GetNextShowTask(ac, ((BiSMoShowList)getActivity()));
		nextShowTask.execute();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mShows = new ArrayList<BiSMoShow>();
		mAdapter = new ShowListAdapter(getActivity(), R.layout.showlistadapter, R.id.title, mShows, getLayoutInflater(savedInstanceState), ac, null);
		setListAdapter(mAdapter);
//		getListView().setSelector(Color.GRAY);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		getListView().setSelector(android.R.color.transparent);
	}
}
