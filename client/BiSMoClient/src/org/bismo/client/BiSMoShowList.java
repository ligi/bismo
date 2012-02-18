package org.bismo.client;

import java.util.ArrayList;

import org.bismo.client.models.BiSMoShow;
import org.bismo.client.tasks.GetNextShowTask;
import org.bismo.client.tasks.GetShowsTask;
import org.bismo.client.widgets.ShowListAdapter;
import org.bismo.client.widgets.ShowListFragment;

import android.content.pm.ActivityInfo;
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
	public ArrayList<BiSMoShow> mShows;
	public TextView nextShowTitle;
	public Exception mException=null;;
	private ShowListFragment listFragment;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		setContentView(R.layout.showlist);
		ac = (ApplicationController)getApplication();
		getSupportActionBar().setTitle("BiSMo rocks!!!");
		nextShowTitle = (TextView)findViewById(R.id.nextShow);
		
		GetNextShowTask nextShowTask = new GetNextShowTask(ac, this);
		nextShowTask.execute();
		
		listFragment = new ShowListFragment(ac);
		this.getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,listFragment).commit();
		
		GetShowsTask task = new GetShowsTask(listFragment, ac);
		task.execute();
	}
	
	public void setNextShowTitle(BiSMoShow show){
			if (show!=null) {
				this.nextShowTitle.setText("Actual show:"+show.getShowTitle()+" with "+show.getTotalVotes()+" total Votes");
			}else{
				this.nextShowTitle.setText("Vote your fovorite show!");
			}
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
				GetShowsTask getShowsTask = new GetShowsTask(listFragment, ac);
				getShowsTask.execute();
				GetNextShowTask nextShowTask = new GetNextShowTask(ac, this);
				nextShowTask.execute();
			return true;

		default:
			return true;
		}
	}
	
	public void startSpinner(){
		
	}
	
	public void stopSpinner(){
		
	}
	
}
