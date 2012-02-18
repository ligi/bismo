package org.bismo.client;

import java.util.ArrayList;

import org.bismo.client.api.BiSMoApi;
import org.bismo.client.models.BiSMoShow;
import org.bismo.client.tasks.GetShowsTask;
import org.bismo.client.widgets.ShowListAdapter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItem;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.TextView;

public class BiSMoShowList extends  FragmentActivity{
	ApplicationController ac;
	ShowListAdapter mAdapter;
	ListView showList;
	ArrayList<BiSMoShow> mShows;
	public TextView nextShowTitle;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showlist);
		ac = (ApplicationController)getApplication();
		this.showList = (ListView)findViewById(R.id.showList);
		getSupportActionBar().setTitle("BiSMo rocks!!!");
		nextShowTitle = (TextView)findViewById(R.id.nextShow);
		
		mAdapter = new ShowListAdapter(getApplicationContext(), R.layout.showlistadapter, R.id.title, new ArrayList<BiSMoShow>(), getLayoutInflater(), ac,this);
		this.showList.setAdapter(mAdapter);
		
		GetShowsTask task = new GetShowsTask(mAdapter, ac);
		task.execute();
		
		new GetNextShowTask().execute();
	}
	
	public void setNextShowTitle(String title){
		this.nextShowTitle.setText("Next show: "+title);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(android.support.v4.view.Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.show_menu, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
			case R.id.menu_refresh:
				
			return true;

		default:
			return true;
		}
		
	}
	
	
	private class GetNextShowTask extends AsyncTask<String, Void, BiSMoShow> {

		@Override
		protected BiSMoShow doInBackground(String... params) {
			// TODO Auto-generated method stub
			return BiSMoApi.getNextShow(ac);
		}
		
		@Override
		protected void onPostExecute(BiSMoShow result) {
			nextShowTitle.setText("Next Show: "+result.getShowTitle());
		}
	}
	
	
}
