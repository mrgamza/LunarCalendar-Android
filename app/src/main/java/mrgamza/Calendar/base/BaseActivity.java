package mrgamza.Calendar.base;

import android.app.Activity;

public class BaseActivity extends Activity {

	protected void runThread(Runnable runnable) {
		new Thread(runnable).start();
	}
	
	protected void setContentView(int layoutId, int featureId, int featureValue) {
		requestWindowFeature(featureId);
		setContentView(layoutId);
		getWindow().setFeatureInt(featureId, featureValue);
	}
	
	protected Activity getBaseActivity()
	{
		return this;
	}
}
