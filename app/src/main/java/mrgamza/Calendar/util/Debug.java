package mrgamza.Calendar.util;

import mrgamza.Calendar.LunarCalendar;

import android.util.Log;

public class Debug
{
	public static void log(String msg)
	{
		if(LunarCalendar.isLog)
		{
			Log.d(LunarCalendar.TAG, msg);
		}
	}
	
	public static void log(int msg)
	{
		if(LunarCalendar.isLog)
		{
			log(String.valueOf(msg));
		}
	}
	
	public static void log(float msg)
	{
		if(LunarCalendar.isLog)
		{
			log(String.valueOf(msg));			
		}
	}
	
	public static void log(long msg)
	{
		if(LunarCalendar.isLog)
		{
			log(String.valueOf(msg));			
		}
	}
	
	public static void log(boolean msg)
	{
		if(LunarCalendar.isLog)
		{
			log(String.valueOf(msg));			
		}
	}
}
