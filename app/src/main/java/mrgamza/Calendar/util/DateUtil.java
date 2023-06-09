package mrgamza.Calendar.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil
{
	public static final String TIMER_FORMAT = "a hh:mm:ss";
	public static final String LUNAR_YEAR_MONTH_FORMAT = "%d.%d";
	
	public static String getTimer(String format)
	{
		return new SimpleDateFormat(format).format(System.currentTimeMillis());
	}
	
	public static String getTimer(String format, int year, int month)
	{
		return new SimpleDateFormat(format).format(new Date(year - 1900, month, 1));
	}
	
	public static int getDayOfWeek(int year, int month)
	{
		return new GregorianCalendar(year, month, 1).get(Calendar.DAY_OF_WEEK) - 1;
	}
	
	public static int getLastDay(int year, int month)
	{
		return new GregorianCalendar(year, month, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static String getLunal(int year, int month, int day)
	{
		String[] MonthData = {"1212122322121", "1212121221220", "1121121222120", "2112132122122", "2112112121220", "2121211212120", "2212321121212", "2122121121210", "2122121212120", "1232122121212", "1212121221220", "1121123221222", "1121121212220", "1212112121220", "2121231212121", "2221211212120", "1221212121210", "2123221212121", "2121212212120", "1211212232212", "1211212122210", "2121121212220", "1212132112212", "2212112112210", "2212211212120", "1221412121212", "1212122121210", "2112212122120", "1231212122212", "1211212122210", "2121123122122", "2121121122120", "2212112112120", "2212231212112", "2122121212120", "1212122121210", "2132122122121", "2112121222120", "1211212322122", "1211211221220", "2121121121220", "2122132112122", "1221212121120", "2121221212110", "2122321221212", "1121212212210", "2112121221220", "1231211221222", "1211211212220", "1221123121221", "2221121121210", "2221212112120", "1221241212112", "1212212212120", "1121212212210", "2114121212221", "2112112122210", "2211211412212", "2211211212120", "2212121121210", "2212214112121", "2122122121120", "1212122122120", "1121412122122", "1121121222120", "2112112122120", "2231211212122", "2121211212120", "2212121321212", "2122121121210", "2122121212120", "1212142121212", "1211221221220", "1121121221220", "2114112121222", "1212112121220", "2121211232122", "1221211212120", "1221212121210", "2121223212121", "2121212212120", "1211212212210", "2121321212221", "2121121212220", "1212112112210", "2223211211221", "2212211212120", "1221212321212", "1212122121210", "2112212122120", "1211232122212", "1211212122210", "2121121122210", "2212312112212", "2212112112120", "2212121232112", "2122121212110", "2212122121210", "2112124122121", "2112121221220", "1211211221220", "2121321122122", "2121121121220", "2122112112322", "1221212112120", "1221221212110", "2122123221212", "1121212212210", "2112121221220", "1211231212222", "1211211212220", "1221121121220", "1223212112121", "2221212112120", "1221221232112", "1212212122120", "1121212212210", "2112132212221", "2112112122210", "2211211212210", "2221321121212", "2212121121210", "2212212112120", "1232212122112", "1212122122120", "1121212322122", "1121121222120", "2112112122120", "2211231212122", "2121211212120", "2122121121210", "2124212112121", "2122121212120", "1212121223212", "1211212221220", "1121121221220", "2112132121222", "1212112121220", "2121211212120", "2122321121212", "1221212121210", "2121221212120", "1232121221212", "1211212212210", "2121123212221", "2121121212220", "1212112112220", "1221231211221", "2212211211220", "1212212121210", "2123212212121", "2112122122120", "1211212322212", "1211212122210", "2121121122120", "2212114112122", "2212112112120", "2212121211210", "2212232121211", "2122122121210", "2112122122120", "1231212122212", "1211211221220", "2121121321222", "2121121121220", "2122112112120", "2122141211212", "1221221212110", "2121221221210", "2114121221221"};
		
		int[] currentDate = new int[162];
		int[] MonthAll = {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		int dateAll = 0;
		int currentDateAll = 0;
		int currentYear = 0;
		
		int tempDate = 0;
		int tempDate0 = 0;
		int m1 = 0;
		int m2 = 0;
		int JCOUNT = 0;
		
		for(int i = 0; i < 162; i++)
		{
			currentDate[i] = 0;
			for(int j = 0; j <= 12; j++)
			{
				if(MonthData[i].substring(j, j + 1).equals("1") || MonthData[i].substring(j, j + 1).equals("3"))
				{
					currentDate[i] = currentDate[i] + 29;
				}
				else if(MonthData[i].substring(j, j + 1).equals("2") || MonthData[i].substring(j, j + 1).equals("4"))
				{
					currentDate[i] = currentDate[i] + 30;
				}
			}
		}
		dateAll = 1880 * 365 + 1880 / 4 - 1880 / 100 + 1880 / 400 + 30;
		currentYear = year - 1;
		currentDateAll = currentYear * 365 + currentYear / 4 - currentYear / 100 + currentYear / 400;
		if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
		{
			MonthAll[1] = 29;
		}
		else
		{
			MonthAll[1] = 28;
		}
		
		if(day <= MonthAll[month - 1])
		{
			for(int i = 0; i < month - 1; i++)
			{
				currentDateAll = currentDateAll + MonthAll[i];
			}
		}
		
		currentDateAll = currentDateAll + day;
		
		tempDate = currentDateAll - dateAll + 1;
		
		tempDate0 = currentDate[0];
		int thisdate = 0;
		while (tempDate > tempDate0)
		{
			tempDate0 = tempDate0 + currentDate[thisdate + 1];
			thisdate++;
		}
		year = thisdate + 1881;
		
		tempDate0 = tempDate0 - currentDate[thisdate];
		tempDate = tempDate - tempDate0;
		
		if(MonthData[thisdate].substring(12).equals("0"))
		{
			JCOUNT = 11;
		}
		else
		{
			JCOUNT = 12;
		}
		
		m2 = 0;
		
		for(int j = 0; j <= JCOUNT; j++)
		{
			if(Integer.parseInt(MonthData[thisdate].substring(j, j + 1)) <= 2)
			{
				m2 = m2 + 1;
				m1 = Integer.parseInt(MonthData[thisdate].substring(j, j + 1)) + 28;
			}
			else
			{
				m1 = Integer.parseInt(MonthData[thisdate].substring(j, j + 1)) + 26;
			}
			
			if(tempDate <= m1)
			{
				break;
			}
			tempDate = tempDate - m1;
		}
		
		month = m2;
		day = tempDate;
		
		if(day < 1)
		{
			return "";
		}
		else
		{
			return String.format(LUNAR_YEAR_MONTH_FORMAT, month, day);
		}
	}
}
