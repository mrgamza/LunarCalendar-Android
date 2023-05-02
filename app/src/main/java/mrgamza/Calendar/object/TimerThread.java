package mrgamza.Calendar.object;

import mrgamza.Calendar.util.DateUtil;

public class TimerThread extends Thread
{
	private static final int SLEEP = 500;
	
	private TimerObserver xTimerObserver;
	
	public interface TimerObserver
	{
		public void result(String time);
	}
	
	public TimerThread(TimerObserver observer)
	{
		xTimerObserver = observer;
	}

	@Override
	public void run()
	{
		while(!interrupted())
		{
			xTimerObserver.result(DateUtil.getTimer(DateUtil.TIMER_FORMAT));
			
			try
			{
				Thread.sleep(SLEEP);
			}
			catch (InterruptedException e)
			{
				return;
			}
		}
	}
}
