package mrgamza.Calendar.activity;

import java.util.Calendar;

import mrgamza.Calendar.R;
import mrgamza.Calendar.base.BaseActivity;
import mrgamza.Calendar.object.TimerThread;
import mrgamza.Calendar.object.TimerThread.TimerObserver;
import mrgamza.Calendar.util.DateUtil;
import mrgamza.Calendar.util.ViewUtil;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener {
	
	/** Day Line */
	private static final int[] ID_COLUMN = {
		R.id.column1, R.id.column2, R.id.column3, R.id.column4, R.id.column5, R.id.column6, R.id.column7
	};

	/** Under Line */
	private static final int[] ID_COLUMN_UNDER = {
		R.id.column1under, R.id.column2under, R.id.column3under, R.id.column4under, R.id.column5under, R.id.column6under, R.id.column7under
	};
	
	private TextView subTitleTextView;
	private TextView timerTextView;
	private LinearLayout mainContainer;
	private TextView[] columnTextViews = new TextView[ID_COLUMN.length];
	private TextView[] columnUnderTextViews = new TextView[ID_COLUMN_UNDER.length];
	private View calendarRow;
	private EditText yearInput;
	private EditText monthInput;
	
	private TimerThread timerThread;
	private TimerObserver timerObserver = new TimerObserver() {

		@Override
		public void result(final String time) {
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					timerTextView.setText(time);
				}
			});
		}
	};
	
	private Calendar calendar = Calendar.getInstance();
	
	private int year;
	private int month;
	
	@Override
	public void onCreate(final Bundle savedInstanceState) {

		setContentView(R.layout.main, Window.FEATURE_CUSTOM_TITLE, R.layout.main_title);
		
		super.onCreate(savedInstanceState);
		
		subTitleTextView = findViewById(R.id.sub_title);
		timerTextView = findViewById(R.id.main_timer);
		mainContainer = findViewById(R.id.main_calendar);

		yearInput = findViewById(R.id.year_input);
		monthInput = findViewById(R.id.month_input);
		
		timerThread = new TimerThread(timerObserver);

		// Sub Title의 오늘 날짜 표기
		subTitleTextView.setText(DateUtil.getTimer(getString(R.string.YEAR_MONTH_FORMAT)));
		timerThread.start();

		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);

		yearInput.setText(String.valueOf(year));
		monthInput.setText(String.valueOf(month + 1));

		makeCalendarRows();
	}
	
	private void makeCalendarRows() {
		runThread(new Runnable() {

			@Override
			public void run() {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						if (mainContainer != null) {
							mainContainer.removeAllViews();
						}

						int count = 0;
						
						final int lastDay = DateUtil.getLastDay(year, month);
						int firstDayOfWeek = DateUtil.getDayOfWeek(year, month);
						while (count < lastDay) {
							calendarRow = View.inflate(getApplicationContext(), R.layout.day_line, null);
							for(int i = firstDayOfWeek; i < ID_COLUMN.length; i++) {
								count++;
								if(count > lastDay) {
									break;
								}
								
								columnTextViews[i] = calendarRow.findViewById(ID_COLUMN[i]);
								columnTextViews[i].setText(String.valueOf(count));
								
								columnUnderTextViews[i] = calendarRow.findViewById(ID_COLUMN_UNDER[i]);
								columnUnderTextViews[i].setText(DateUtil.getLunal(year, month + 1, count));
							}
							
							mainContainer.addView(calendarRow);
							firstDayOfWeek = 0;
						}
					}
				});
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.before_year : {
				if (validation(year - 1)) {
					year--;
				}

				break;
			}
			
			case R.id.next_year : {
				if (validation(year + 1)) {
					year++;
				}
				
				break;
			}
			
			case R.id.before : {
				if (month == Calendar.JANUARY) {
					if(validation(year - 1)) {
						year--;
						month = Calendar.DECEMBER;
					}
				} else {
					month--;
				}
				
				break;
			}
			
			case R.id.next : {
				if (month == Calendar.DECEMBER) {
					if (validation(year + 1)) {
						year++;
						month = Calendar.JANUARY;
					}
				} else {
					month++;
				}
				
				break;
			}

			case R.id.move_button: {
				moveInput();
				return;
			}
			
			default : {
				return;
			}
		}

		yearInput.setText(String.valueOf(year));
		monthInput.setText(String.valueOf(month + 1));

		makeCalendarRows();
	}
	
	private boolean validation(int year) {
		if (year < 1 || year > 9999) {
			ViewUtil.showToast(getBaseActivity(), R.string.EXCEPTION_YEAR, Toast.LENGTH_SHORT);
			return false;
		}

		return true;
	}

	private void moveInput() {
		int targetYear = Integer.parseInt(yearInput.getText().toString());
		int targetMonth = Integer.parseInt(monthInput.getText().toString()) - 1;

		if (!validation(targetYear)) return;
		if (targetMonth < 0 || targetMonth > 11) {
			ViewUtil.showToast(getBaseActivity(), R.string.EXCEPTION_DAY, Toast.LENGTH_SHORT);
			return;
		}

		year = targetYear;
		month = targetMonth;

		makeCalendarRows();
	}
}
