package mrgamza.Calendar.util;

import android.app.Activity;
import android.widget.Toast;

public class ViewUtil
{
	public static void showToast(final Activity activity, final int text, final int duration)
	{
		activity.runOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				Toast.makeText(activity, text, duration).show();
			}
		});
	}
}
