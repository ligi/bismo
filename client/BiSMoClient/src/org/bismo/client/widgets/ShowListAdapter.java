package org.bismo.client.widgets;

import java.util.ArrayList;

import org.bismo.client.ApplicationController;
import org.bismo.client.BiSMoShowList;
import org.bismo.client.R;
import org.bismo.client.models.BiSMoShow;
import org.bismo.client.util.BismoHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ShowListAdapter extends ArrayAdapter<BiSMoShow> {
	
	
	static class ViewHolder {
		private TextView showTitle;
		private TextView showTotalVotes;
		private TextView showDetails;
	}
	
	private LayoutInflater mInflater;
	private Context mContext;

	public ShowListAdapter(Context context, int resource,
			int textViewResourceId, ArrayList<BiSMoShow> objects,LayoutInflater mInflater, ApplicationController ac,BiSMoShowList activity) {
		super(context, resource, textViewResourceId, objects);
		this.mInflater = mInflater;
		this.mContext = context;
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
		
		if (position == BismoHelper.LAST_SELECTED_SHOW_INDEX) {
			Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.hyperspace_jump);
			rowView.startAnimation(animation);
			BismoHelper.LAST_SELECTED_SHOW_INDEX = -1;
//			RelativeLayout layout = (RelativeLayout) rowView.findViewById(R.id.adapter_layout);
//			layout.setBackgroundResource(R.drawable.translate);
//			
//			TransitionDrawable transition = (TransitionDrawable) layout.getBackground();
//			transition.startTransition(500);
//			transition.reverseTransition(500);

		}
		
		return rowView;
	}

}
