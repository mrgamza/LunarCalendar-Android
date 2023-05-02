package mrgamza.Calendar;

import android.app.Application;
import android.content.pm.ApplicationInfo;

public class LunarCalendar extends Application
{
	public static final String TAG = "LunarCalendar";
	public static boolean isLog;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		isLog = ( 0 != ( getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE ) );
	}
}
