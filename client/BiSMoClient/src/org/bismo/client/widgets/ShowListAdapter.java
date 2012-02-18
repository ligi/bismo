package org.bismo.client.widgets;

import java.util.ArrayList;

import org.bismo.client.ApplicationController;
import org.bismo.client.BiSMoShowList;
import org.bismo.client.R;
import org.bismo.client.models.BiSMoShow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ShowListAdapter extends ArrayAdapter<BiSMoShow> {
	
	
	static class ViewHolder {
		private TextView showTitle;
		private TextView showTotalVotes;
		private TextView showDetails;
	}
	
	private LayoutInflater mInflater;

	public ShowListAdapter(Context context, int resource,
			int textViewResourceId, ArrayList<BiSMoShow> objects,LayoutInflater mInflater, ApplicationController ac,BiSMoShowList activity) {
		super(context, resource, textViewResourceId, objects);
		this.mInflater = mInflater;
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
			holder.showTotalVotes = (TextView)rowView.findViewById(R.id.totalVotes);
			holder.showDetails = (TextView)rowView.findViewById(R.id.showDetail);
		} 
		
		holder.showTitle.setText(mShow.getShowTitle());
		holder.showDetails.setText(mShow.getShowParam());
		holder.showTotalVotes.setText(""+mShow.getTotalVotes());
		
		return rowView;
	}

}
