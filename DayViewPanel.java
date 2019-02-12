import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Implements one day view panel
 * @author My Vo
 *
 */
public class DayViewPanel extends JPanel{

	private final int WIDTH = 400;
	private final int HEIGHT = 300;
	private MyCalendar myCal;
	
	/**
	 * Constructs a day view panel
	 * @param year the given year
	 * @param month the given month
	 * @param day the given day
	 * @param myCal MyCalendar
	 */
	public DayViewPanel(int year, int month, int day, MyCalendar myCal)
	{
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.myCal = myCal;
		this.setVisible(true);
		this.setLayout(null);
		drawView(year, month, day);
	}
	
	/**
	 * Draws the view of the day
	 * @param year the given year
	 * @param month the given month
	 * @param day the given day
	 */
	public void drawView(int year, int month, int day)
	{
		this.removeAll();
		this.repaint();
		month++;
		String monthS = String.valueOf(month);
		String dayS = String.valueOf(day);
		if (monthS.length() == 1)
		{
			monthS = "0" + monthS;
		}
		if (dayS.length() == 1)
		{
			dayS = "0" + dayS;
		}
		String date = "" + year + "/" + monthS + "/" + dayS;
		ArrayList<Event> events = myCal.getEventByDate(date);
		JLabel title = new JLabel(helperDisplay.getEnglishDate(date, true));
		this.add(title);
		int x = 0;
		int y = WIDTH/5;
		int height = 40;
		title.setBounds(WIDTH/4,WIDTH/8,WIDTH/2,height);
		if (events.size() == 0)
		{
			JLabel label = new JLabel("There is no event in this day.");
			this.add(label);
			label.setBounds(x, y, WIDTH, height);
		}
		else
		{
			for (Event e : events)
			{
				JLabel label = new JLabel(e.getDuration() + ": " + e.getTitle());
				this.add(label);
				label.setBounds(x,y, WIDTH,height);
				y += height+1;
			}
		}
	}
	
	
}
