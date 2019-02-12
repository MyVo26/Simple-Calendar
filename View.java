import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Implements a view class
 * @author My Vo
 *
 */
public class View extends JFrame
{
	static MonthPanel monthPanel;
	private MyCalendar mycal;
	
	/**
	 * Construcs a view class
	 * @param mycal MyCalendar
	 * @param panel the MonthPanel
	 * @throws IOException exception
	 */
	public View(final MyCalendar mycal, MonthPanel panel) throws IOException
	{
		JFrame frame = new JFrame();
		this.mycal = mycal;
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		mycal.load();
		monthPanel = panel;
		TopPanel topPanel = new TopPanel();
		frame.setLayout(new BorderLayout());
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(monthPanel, BorderLayout.WEST);
		frame.pack();
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.white);

		// Attaches ActionListeners
		// These codes serves as controller
		topPanel.attatchNextListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent action) {
				String date = monthPanel.getAddDate(1);
				int[] dates = PrintHelper.dateStringToInt(date);
				monthPanel.setDate(dates[0], dates[1]-1, dates[2]);
				monthPanel.drawMonth(dates[2]);
			}
			
		});
		
		topPanel.attatchPrevListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent action) {
				String date = monthPanel.getAddDate(-1);
				int[] dates = helperDisplay.convertStringToIntDate(date);
				monthPanel.setDate(dates[0], dates[1]-1, dates[2]);
				monthPanel.drawMonth(dates[2]);
			}
			
		});
		
		topPanel.attatchCreateListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent action) {
				int width = 500;
				int height = 300;
				String date = monthPanel.getFullDate();
				int dates[] = helperDisplay.convertStringToIntDate(date);
				CreateEventPanel temp = new CreateEventPanel(width, height,dates[0],dates[1],dates[2],mycal, monthPanel);
				monthPanel.addCreatePanel(temp);
				
			}
			
		});
		
		topPanel.attachQuitListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					mycal.quit();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
				
			}
			
		});
		
	}
}
