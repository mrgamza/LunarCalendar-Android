/*
 * Copyright (C) 2011. Lee Dong-Hyuk. mrgamza@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mrgamza.Calendar.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import mrgamza.Calendar.R;

public class Indicator implements OnTouchListener
{
	private final int ACTIVITY = 1;
	private final int LAYOUT = 2;
	private final int HIDE = 3;
	
	private Activity xActivity = null;
	private ViewGroup xLayout = null;
	private Context xContext = null;
	private View xView = null;
	
	private boolean isShow = false;
	
	private int iMode = 0;
	
	public Indicator(Activity activity)
	{
		iMode = ACTIVITY;
		xActivity = activity;
	}
	
	public Indicator(Context context, View layout)
	{
		iMode = LAYOUT;
		xContext = context;
		xLayout = (ViewGroup)layout;
	}

	public void show()
	{
		isShow = true;
		xHandler.sendEmptyMessage(iMode);
	}
	
	public void hide()
	{
		xHandler.sendEmptyMessage(HIDE);
	}
	
	public boolean isShowing()
	{
		return isShow;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		return true;
	}
	
	private Handler xHandler = new Handler()
	{
		private void hide()
		{
			if(xView != null)
			{
				xView.setVisibility(View.GONE);
				xView.setFocusable(false);
				xView.setBackgroundDrawable(null);
				xView = null;
			}
			
			isShow = false;
		}
		
		private void showActivity()
		{
			if(xView == null)
			{
				xView = View.inflate(xActivity, R.layout.indicator, null);
				xView.setOnTouchListener(Indicator.this);
				xActivity.addContentView(xView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			}
			
			xView.setVisibility(View.VISIBLE);
			xView.setFocusable(true);
		}
		
		private void showLayout()
		{
			if(xView == null)
			{
				xView = View.inflate(xContext, R.layout.indicator, null);
				xView.setOnTouchListener(Indicator.this);
				xLayout.addView(xView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			}
			
			xView.setVisibility(View.VISIBLE);
			xView.setFocusable(true);
		}
		
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
				case ACTIVITY :
				{
					showActivity();
					
					break;
				}
				case LAYOUT :
				{
					showLayout();
					
					break;
				}
				case HIDE :
				{
					hide();
					
					break;
				}
			}
		}
	};
}
