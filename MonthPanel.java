import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Implements a monthPanel which display my calendar by month
 * @author My Vo
 *
 */

public class MonthPanel extends JPanel
{
	enum MONTHS
	{
		January, Febuary, March, April, May, June, July, August, Septemper, October, November, December;
	}
	enum DAYS
	{
		Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday ;
	}
	DatePanel datePanels[];
	JLabel dayLabels[];
	private boolean isSelected;
	private final int DATES = 31;
	private final int WIDTH = 40;
	private final int DAYS_IN_WEEK = 7;
	private final int PANEL_SIZE = 500;
	private JPanel monthPanel;
	private DayViewPanel dayViewPanel;
	private int currentDate;
	private int currentYear;
	private int currentMonth;
	private int selectedDate;
	private int maxDays;
	private JPanel current;
	private MyCalendar myCal;
	private ArrayList<ChangeListener> changeListeners;
	
	/**
	 * Constructs a month panel
	 * @param year the given year
	 * @param month the given month
	 * @param mycal Mycalendar
	 */
	public MonthPanel(int year, int month, MyCalendar mycal)
	{
		current = this;
		month--;
		int[] today = PrintHelper.getToday();
		monthPanel = new JPanel();
		monthPanel.setPreferredSize(new Dimension(PANEL_SIZE,PANEL_SIZE));
		monthPanel.setLayout(null);
		monthPanel.setVisible(true);
		dayViewPanel = new DayViewPanel(year,month,today[2],mycal);
		monthPanel.setPreferredSize(new Dimension(PANEL_SIZE,PANEL_SIZE));
		myCal = mycal;
		currentYear = year;
		currentMonth = month;
		selectedDate = -1;
		GregorianCalendar cal = new GregorianCalendar(year,month,1);
		datePanels = new DatePanel[DATES];
		for (int i = 0; i < datePanels.length; i++)
		{
			datePanels[i] = new DatePanel(WIDTH,WIDTH, i+1, false);
			final DatePanel temp = datePanels[i];
			if (i == today[2]-1)
			{
				datePanels[i].setSelect(true);
			}
		
			// These code serves as controller
			datePanels[i].addMouseListener(new MouseListener()
			{

				@Override
				public void mouseClicked(MouseEvent arg0) {
					selectedDate = getSelectedDate();
					if (selectedDate != -1)
					{
						datePanels[selectedDate].setSelect(false);
						datePanels[selectedDate].stateChanged(new ChangeEvent(this));
					}
					temp.setSelect(true);
					temp.stateChanged(new ChangeEvent(this));
					dayViewPanel.drawView(currentYear, currentMonth, getSelectedDate()+1);
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					
				}
				@Override
				public void mouseReleased(MouseEvent arg0) {
				}
				
			});
		}
		dayLabels = new JLabel[DAYS_IN_WEEK];
		DAYS[] arrayOfDays = DAYS.values();
		for (int i = 0; i < dayLabels.length; i++)
		{
			dayLabels[i] = new JLabel("  "+ arrayOfDays[i].toString().substring(0,3));
		}
		drawMonth(-1);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.add(monthPanel, BorderLayout.WEST);
		this.add(dayViewPanel, BorderLayout.EAST);
		
		
	}
	
	/**
	 * Attaches a new Panel when the button "CREATE" is clicked
	 * @param panel the panel
	 */
	public void addCreatePanel(CreateEventPanel panel)
	{
		monthPanel.setVisible(false);
		current.add(panel, BorderLayout.NORTH);
	}
	
	/**
	 * Sets the calendar visible or not
	 * @param var the visible boolean value
	 */
	public void setPanelVisible(boolean var)
	{
		monthPanel.setVisible(var);
	}
	
	/**
	 * Sets date for the calendar
	 * @param year the given year
	 * @param month the given month
	 * @param date the given date
	 */
	public void setDate(int year, int month, int date)
	{
		currentYear = year;
		currentMonth = month;
		currentDate = date;
	}
	
	/**
	 * Gets selected date
	 * @return the selected date
	 */
	public int getSelectedDate()
	{
		int date = -1;
		for (int i = 0; i < datePanels.length; i++)
		{
			if (datePanels[i].isSelect() == true)
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Gets the current date
	 * @return the current date
	 */
	public int getDate()
	{
		return currentDate;
	}
	
	/**
	 * Gets maximum days in a month
	 * @return the maximum of days in a month
	 */
	public int getMaxDay()
	{
		return maxDays;
	}
	
	/**
	 * Gets the current month
	 * @return the current month
	 */
	public int getMonth()
	{
		return currentMonth;
	}
	
	/**
	 * Gets the date after adding an offset
	 * @param offset the offset 
	 * @return the date after adding the offset
	 */
	public String getAddDate(int offset)
	{
		return helperDisplay.addDate(currentYear, currentMonth+1, getSelectedDate()+1, offset);
	}
	
	/**
	 * Gets the fulldate which is included year, month, and day
	 * @return the full date
	 */
	public String getFullDate()
	{
		String monthS = String.valueOf(currentMonth+1);
		if (monthS.length() < 2)
			monthS = "0" + monthS;
		String dayS = String.valueOf(getSelectedDate()+1);
		if (dayS.length()<2)
			dayS = "0" + dayS;
		return "" +currentYear+"/"+monthS+"/"+dayS;
	}
	
	/**
	 * Draws the calendar by month
	 * @param select if it is < 0 then no date will be selected, else the select value is the selected date
	 */
	public void drawMonth(int select)
	{
		for (int i = 0; i < datePanels.length; i++)
			datePanels[i].setHighlight(false);
		monthPanel.removeAll();
		monthPanel.repaint();
		if (select != -1)
		{
			datePanels[getSelectedDate()].setSelect(false);
			datePanels[select-1].setSelect(true);
			dayViewPanel.drawView(currentYear, currentMonth, select);
		}
		
		MONTHS[] arrayOfMonths = MONTHS.values();
	    int x = 0;
	    int y = 0;
		int start = 0;
		JLabel month = new JLabel(arrayOfMonths[currentMonth].toString());
	    JLabel year = new JLabel(String.valueOf(currentYear));
	    monthPanel.add(month);
	    monthPanel.add(year);
	    month.setFont(new Font("Serif", Font.PLAIN, 20));
	    year.setFont(new Font("Serif", Font.PLAIN, 20));
	    month.setBounds(x,y,WIDTH*2, WIDTH);
	    year.setBounds(x+WIDTH*2,y,WIDTH*2,WIDTH);
	    start += WIDTH;
	    y = start;
	    
		ArrayList<Integer> highlight = myCal.hightlightDays(currentYear, currentMonth);
		GregorianCalendar cal = new GregorianCalendar(currentYear,currentMonth,1);
		int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		maxDays = daysInMonth;
		for (int i = 0; i < dayLabels.length; i++)
		{
			monthPanel.add(dayLabels[i]);
			dayLabels[i].setBounds(x, y, WIDTH, WIDTH);
			x += WIDTH * 3/2;
		}
		start += WIDTH;
		y = start;
		x = 0;
		int firstDate = helperDisplay.getDayOfWeek(currentYear, currentMonth,1) - 1;
		int count = 1;
	    int indexArrayList = 0;
	    for (int i = 0; i < daysInMonth+firstDate; i++)
	    {
	    	if (i % 7 == 0 && i != 0)
	    	{
	    		x = 0;
	    		y += WIDTH*3/2;
	    	}
	    	if (i < firstDate)
	    	{
	    		x += WIDTH*3/2;
	    	}
	    	else
	    	{
	    		if (indexArrayList < highlight.size() && count == highlight.get(indexArrayList))
	    		{
	    			datePanels[count-1].setHighlight(true);
	    			indexArrayList++;
	    			
	    		}
	    		monthPanel.add(datePanels[count-1]);
	    		datePanels[count-1].setBounds(x, y, WIDTH, WIDTH);
	    		count++;
	    		x += WIDTH*3/2;
	    		
	    	}
	    }
		
		
	}
	
}
