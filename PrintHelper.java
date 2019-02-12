import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
/**
 * enum class for DAYS
 * @author My Vo
 *
 */
enum DAYS
{
	Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday ;
}
/**
 * enum class for MONTHS
 * @author My Vo
 *
 */
enum MONTHS
{
	January, Febuary, March, April, May, June, July, August, Septemper, October, November, December;
}
/**
 * Model PrintHelper for printing job
 * @author My Vo
 *
 */
public class PrintHelper 
{
	/**
	 * Constructor of PrintHelper
	 */
	public PrintHelper()
	{
		
	}
	/**
	 * Gets date of today in int array type
	 * @return date in form of yyyy/mm/dd
	 */
	public static int[] getToday()
	{
		GregorianCalendar cal = new GregorianCalendar();
		int[] date = new int[3];
		date[0] = cal.get(Calendar.YEAR);
		date[1] = cal.get(Calendar.MONTH) + 1;
		date[2] = cal.get(Calendar.DAY_OF_MONTH);
		return date;
	}
	/**
	 * Converts date format to the form yyyy/mm/dd
	 * @param date the given date that need to be formated
	 * @return date the date with String type
	 */
	public static String convertDateFormat(String date)
	{
		if (date.indexOf("/") == 4)
		{
			return date;
		}
		String result = "";
		String year = date.substring(6);
		String month = date.substring(0,2);
		String day = date.substring(3,5);
		result = result + year + "/" + month + "/" + day;
		return result;
	}
	/**
	 * Converts date in the String type to Int array
	 * @param date
	 * @return
	 */
	public static int[] dateStringToInt(String date)
	{
		// push the date in the String type into String array
		// with the elements of the String array are yyyy, mm, dd
		String[] string = date.split("/");
		int[] theDate = new int[3];
		int i = 0;
		for (; i < string.length; i++)
		{
			theDate[i] = Integer.valueOf(string[i]);
		}
		return theDate;
	}
	/**
	 * Gets today date in String type
	 * @return today date in String type
	 */
	public static String getTodayString()
	{
		GregorianCalendar cal = new GregorianCalendar();
		String zero = "0";
		String result = "";
		
		int year = cal.get(Calendar.YEAR);
		// convert year in int type to string type
		String yearString = String.valueOf(year);
		
		int month = cal.get(Calendar.MONTH) + 1;
		// convert month in int type to string type
		String monthString = String.valueOf(month);
		if (monthString.length() == 1)
		{
			monthString = zero + monthString;
		}
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		//convert day in int type to string type
		String dayString = String.valueOf(day);
		if (dayString.length() == 1)
		{
			dayString = zero + dayString;
		}
		result = yearString + "/" + monthString + "/" + dayString;
		return result;
	}
	/**
	 * Uses to access the previous or next date
	 * @param date the current date
	 * @param amount 1 (next date) or -1 (previous date)
	 * @return date the date after accessing
	 */
	public static String accessDate(String date, int amount)
	{
		date = PrintHelper.convertDateFormat(date);
		int[] intDate = PrintHelper.dateStringToInt(date);
		GregorianCalendar cal = new GregorianCalendar(intDate[0], intDate[1] - 1, intDate[2]);
		cal.add(Calendar.DAY_OF_MONTH, amount);
		
		String zero = "0";
		String result = "";
		
		int year = cal.get(Calendar.YEAR);
		// convert year in int type to string type
		String yearString = String.valueOf(year);
		
		int month = cal.get(Calendar.MONTH) + 1;
		// convert month in int type to string type
		String monthString = String.valueOf(month);
		if (monthString.length() == 1)
		{
			monthString = zero + monthString;
		}
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		//convert day in int type to string type
		String dayString = String.valueOf(day);
		if (dayString.length() == 1)
		{
			dayString = zero + dayString;
		}
		result = yearString + "/" + monthString + "/" + dayString;
		return result;
	}
	/**
	 * Uses to access the previous or next month
	 * @param date the current date
	 * @param amount -1 (previous month) or 1 (next month)
	 * @return date the date we wanna access to
	 */
	public static String accessMonth(String date, int amount)
	{
		date = PrintHelper.convertDateFormat(date);
		int[] dateInt = PrintHelper.dateStringToInt(date);
		GregorianCalendar cal = new GregorianCalendar(dateInt[0], dateInt[1] - 1, dateInt[2]);
		cal.add(Calendar.MONTH, amount);
		String zero = "0";
		String result = "";
		
		int year = cal.get(Calendar.YEAR);
		// convert year in int type to string type
		String yearString = String.valueOf(year);
		
		int month = cal.get(Calendar.MONTH) + 1;
		// convert month in int type to string type
		String monthString = String.valueOf(month);
		if (monthString.length() == 1)
		{
			monthString = zero + monthString;
		}
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		//convert day in int type to string type
		String dayString = String.valueOf(day);
		if (dayString.length() == 1)
		{
			dayString = zero + dayString;
		}
		result = yearString + "/" + monthString + "/" + dayString;
		return result;
	}
	
	/**
	 * Gets day of week
	 * @param year the given year
	 * @param month the given month
	 * @param day the given day
	 * @return day the day of week
	 */
	public static int getDayOfWeek(int year, int month, int day)
	{
		GregorianCalendar cal = new GregorianCalendar(year, month, day);
		int k = 0;
		for (; k < 7; k++)
		{
			System.out.print("");
		}
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	/**
	 * Prints the calendar
	 * @param year the year
	 * @param month the month
	 * @param highlight highlighted date
	 * @param daysInMonth
	 */
	public static void printCalendar(ArrayList<Integer> highlight, int year, int month, int daysOfMonth)
	{
		int count = 1;
		int daysOfWeek = 7;
		DAYS[] dayArray = DAYS.values();
		MONTHS[] monthArray = MONTHS.values();
		String yearString = String.valueOf(year);
	
	    System.out.println("\t\t\t"+ monthArray[month].toString() + " " + yearString);
	    
	    int i = 0;
	    for (; i < dayArray.length; i++)
	    {
	    	String printDayArrayToString = dayArray[i].toString();
	    	System.out.printf("%7s ", printDayArrayToString.substring(0,2));
	    }
	    System.out.println();
	    
	    int firstDate = PrintHelper.getDayOfWeek(year, month, 1) - 1;
	    //using to access the array list
	    int index = 0;
	    int j = 0;
	    for (; j < daysOfMonth + firstDate; j++)
	    {
	    	if (j != 0 && j % 7 == 0)
	    	{
	    		System.out.println();
	    	}
	    	if (j < firstDate)
	    	{
	    		System.out.printf("%-8s"," ");
	    	}
	    	else
	    	{
	    		if (index < highlight.size() && count == highlight.get(index))
	    		{
	    			int k = 0;
	    			for (; k < daysOfWeek; k++)
	    			{
	    				System.out.print("");
	    			}
	    			String highlightedDay = "[" + String.valueOf(highlight.get(index)) + "]";
	    			System.out.printf("%8s", highlightedDay);
	    			index++;
	    			count++;
	    			
	    		}
	    		else
	    		{
	    			int k = 0;
	    			for (; k < daysOfWeek; k++)
	    			{
	    				System.out.print("");
	    			}
	    			System.out.printf(" %6d ", count++);
	    		}
	    	}
	    }
	    int k = 0;
		for (; k < daysOfWeek; k++)
		{
			System.out.print("");
		}
	}
	/**
	 * prints calendar by month
	 * @param cal new object of MyCalendar
	 * @param year the year
	 * @param month the month
	 */
	public static void printByMonth(MyCalendar cal, int year, int month)
	{
		ArrayList<Integer> highlight = cal.hightlightDays(year, month);
		GregorianCalendar theCal = new GregorianCalendar(year, month, 1);
		int daysOfMonth = theCal.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println();
		printCalendar(highlight, year, month, daysOfMonth);
	}
	/**
	 * Gets a date in words
	 * @param date the date
	 * @param hasYear check if the year included
	 * @return result the date in words
	 */
	public static String getDateInWords(String date, boolean hasYear)
	{
		DAYS[] dayArray = DAYS.values();
		MONTHS[] monthArray = MONTHS.values();
	    
	    int[] theDate = PrintHelper.dateStringToInt(date);
	    int dayOfWeek = PrintHelper.getDayOfWeek(theDate[0], theDate[1], theDate[2])- 1;
	    String theDay = dayArray[dayOfWeek].toString();
	    String theMonth = monthArray[theDate[1] - 1].toString();
	    String aDate = String.valueOf(theDate[2]);
	    String result = theDay + ", " + theMonth.substring(0, 3) + " " + aDate;
	    String theYear =  String.valueOf(theDate[0]);
	    if (hasYear == true)
	    {
	    	result = result + ", " + theYear;
	    }
	    return result;    
	}
	/**
	 * Print the event(s) of a date
	 * @param cal calendar
	 * @param date the date
	 * @param showNumber
	 */
	public static void printEventOfDate(MyCalendar cal, String date, boolean showNumber)
	{
		int ite = 1;
		int daysOfWeek = 7;
		ArrayList<Event> eventOfDate = cal.getEventByDate(date);
		if (eventOfDate.size() == 0)
		{
			System.out.println("There is NOT any events on " + getDateInWords(date, true));
			return;
		}
		System.out.println("Event(s) on " + getDateInWords(date, true) + ":");
		int k = 0;
		for (; k < daysOfWeek; k++)
		{
			System.out.print("");
		}
		for(Event e : eventOfDate)
		{  
			if (showNumber == true)
			{
				System.out.printf("\t%d. ", ite++ );
			}
			System.out.println(e.getDuration() + ", " + e.getTitle());
		}
	}
	/**
	 * prints calendar by day
	 * @param cal new object of MyCalendar
	 * @param date the date
	 */
	public static void printByDay(MyCalendar cal, String date)
	{
		int daysOfWeek = 7;
		date = PrintHelper.convertDateFormat(date);
		int k = 0;
		for (; k < daysOfWeek; k++)
		{
			System.out.print("");
		}
		printEventOfDate(cal, date, true);
	}
}
