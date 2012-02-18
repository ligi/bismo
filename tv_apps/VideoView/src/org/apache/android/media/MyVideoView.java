package org.apache.android.media;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.widget.VideoView;

public class MyVideoView extends VideoView {
	public Display mDisplay;
	
	public MyVideoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		  int width = getDefaultSize(0, widthMeasureSpec);
//		  int height = getDefaultSize(0, heightMeasureSpec);
//
//		  setMeasuredDimension(width, height);
//	}

}
