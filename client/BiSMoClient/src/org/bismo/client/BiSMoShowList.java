package org.bismo.client;

import org.bismo.client.api.BiSMoApi;
import org.bismo.client.widgets.ShowListAdapter;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

public class BiSMoShowList extends ListActivity {
	ApplicationController ac;
	
	ShowListAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showlist);
		ac = (ApplicationController)getApplication();
		
		mAdapter = new ShowListAdapter(getApplicationContext(), R.layout.showlistadapter, R.id.title, BiSMoApi.getShows(ac), (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), ac);
		getListView().setAdapter(mAdapter);
	}
}
