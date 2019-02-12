import java.io.IOException;

/**
 * Main class
 * @author My Vo
 *
 */
public class SimpleCalendar 
{
	public static void main(String[] args) throws IOException
	{
		MyCalendar mycal = new MyCalendar();
		mycal.load();
		String date = helperDisplay.getTodayString();
		int dates[] = helperDisplay.convertStringToIntDate(date);
		MonthPanel panel = new MonthPanel(dates[0],dates[1],mycal);
		View view = new View(mycal, panel);
	}
}
