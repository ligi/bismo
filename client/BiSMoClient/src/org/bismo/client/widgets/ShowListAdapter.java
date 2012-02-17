package org.bismo.client.widgets;

import java.util.ArrayList;

import org.bismo.client.ApplicationController;
import org.bismo.client.R;
import org.bismo.client.api.BiSMoApi;
import org.bismo.client.models.BiSMoShow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ShowListAdapter extends ArrayAdapter<BiSMoShow> {
	
	
	static class ViewHolder {
		private Button voteButton;
		private TextView showTitle;
	}
	
	private LayoutInflater mInflater;
	private ApplicationController ac;

	public ShowListAdapter(Context context, int resource,
			int textViewResourceId, ArrayList<BiSMoShow> objects,LayoutInflater mInflater, ApplicationController ac) {
		super(context, resource, textViewResourceId, objects);
		this.mInflater = mInflater;
		this.ac = ac;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final BiSMoShow mShow = (BiSMoShow)getItem(position);
		
		View rowView = convertView;
		final ViewHolder holder;
		/*if (null == convertView)*/ 
		{
			rowView = mInflater.inflate(R.layout.showlistadapter, null);
			holder = new ViewHolder();
			holder.showTitle = (TextView)rowView.findViewById(R.id.showTitle);
			holder.voteButton = (Button)rowView.findViewById(R.id.vote);
		} 
		
		holder.showTitle.setText(mShow.getShowTitle());
		holder.voteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BiSMoApi.voteShow(ac, mShow.getShowId());
			}
		});
		return rowView;
	}

}
