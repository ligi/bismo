package org.bismo.client;

import org.bismo.client.api.BiSMoApi;
import org.bismo.client.widgets.ShowListAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBar.Tab;
import android.support.v4.app.ActionBar.TabListener;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItem;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;

public class BiSMoShowList extends  FragmentActivity{
	ApplicationController ac;
	ShowListAdapter mAdapter;
	ListView showList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showlist);
		ac = (ApplicationController)getApplication();
		this.showList = (ListView)findViewById(R.id.showList);
		mAdapter = new ShowListAdapter(getApplicationContext(), R.layout.showlistadapter, R.id.title, BiSMoApi.getShows(ac), (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), ac);
		this.showList.setAdapter(mAdapter);
		
		getSupportActionBar().setTitle("MyTitle");
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
	
	
		
	
	
}
