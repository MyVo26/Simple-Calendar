import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Implements a panel which appears when the button "CREATE" is clicked
 * @author My Vo
 *
 */
public class CreateEventPanel extends JPanel{
	int width;
	int height;
	private MyCalendar mycal;
	private JTextField title;
	private JTextField date;
	private JTextField startTime;
	private JTextField endTime;
	private JButton save;
	private JLabel from;
	private JLabel to;
	private JLabel dateL;
	private JPanel current;
	private MonthPanel panel;
	private JButton cancel;
	
	/**
	 * Constructs a create event panel
	 * @param width the panel's width
	 * @param height the panel's height
	 * @param year the current display year
	 * @param month the current display month
	 * @param day the current display day
	 * @param mycal MyCalendar
	 * @param panel MonthPanel
	 */
	public CreateEventPanel(int width, int height,int year, int month, int day, MyCalendar mycal, MonthPanel panel)
	{
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(1000,1000));
		this.mycal = mycal;
		this.setLayout(null);
		addEvent(year, month, day);
		current = this;
		this.panel = panel;
		
	}
	
	/**
	 * Adds event into Mycalendar
	 * @param year the event's year
	 * @param month the event's month
	 * @param day the event's day
	 */
	public void addEvent(int year, int month, int day)
	{
		this.setVisible(true);
		cancel = new JButton("CANCEL");
		title = new JTextField("Untitled Event");
		date = new JTextField(""+month+"/"+day+"/"+year);
		startTime = new JTextField("07:00");
		endTime = new JTextField("11:00");
		save = new JButton("SAVE");
		from = new JLabel("From");
		to = new JLabel("To");
		dateL = new JLabel("Date");
		title.setBounds(0, 0, width*3/4, height/8);
		date.setBounds(height/4, height/8, width/4, height/8);
		startTime.setBounds(height/4, height/4, width/4, height/8);
		endTime.setBounds(width/4+height*3/8, height/4, width/4,height/8);
		save.setBounds(width/3, height/2, height/2, height/8);
		from.setBounds(0,height/4,height/8,height/8);
		to.setBounds(width/4+height/4,height/4,height/8,height/8);
		dateL.setBounds(0, height/8, height/8, height/8);
		cancel.setBounds(0,height/2,height/2,height/8);
		this.add(title);
		this.add(date);
		this.add(startTime);
		this.add(endTime);
		this.add(save);
		this.add(from);
		this.add(to);
		this.add(dateL);
		this.add(cancel);
		
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent action) {
				String duration = startTime.getText() + "-" + endTime.getText();
				String titles = title.getText();
				String new_date = helperDisplay.changeDateFormat(date.getText());
				boolean conflict = mycal.addEvent(new Event(titles, new_date, duration));
				if (conflict==true)
				{
					current.setVisible(false);
					panel.setPanelVisible(true);
					panel.drawMonth(panel.getSelectedDate()+1);
				}
				else
				{
					JOptionPane.showMessageDialog(current, "Confliction!!.");
				}
			}
			
		});
		
		cancel.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				current.setVisible(false);
				panel.setPanelVisible(true);
				panel.drawMonth(panel.getSelectedDate()+1);
			}
			
		});
		
	}
	
}
