import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

/**
 * Implements a single date panel in the calendar
 * @author My Vo
 */
public class DatePanel extends JPanel implements ChangeListener
{
	private int date;
	private boolean isSelected;
	private boolean isHighLight;
	private JLabel dateLabel;
	private Rectangle borders;
	private int width;
	private int height;
	
	/**
	 * Constructs a date panel with given width, height, day and boolean hightlight
	 * @param w the width
	 * @param h the height
	 * @param date the date
	 * @param highLight if it is true, then the day will be highlighted
	 */
	public DatePanel(int w, int h, int date, boolean highLight)
	{
		width = w;
		height = h;
		this.setPreferredSize(new Dimension(w,h));
		this.date = date;
		isSelected = false;
		isHighLight = highLight;
		dateLabel = new JLabel(String.valueOf(date));
		this.add(dateLabel);
	}
	
	/**
	 * @Overriden
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		borders = new Rectangle(0,0,width-1,height-1);
		if (isHighLight == true)
		{
			g2.setColor(Color.red);
			g2.fill(borders);
		}
		if (isSelected == true)
		{
			g2.setColor(Color.GREEN);
			g2.setStroke(new BasicStroke(5));
			g2.draw(borders);
		}
		else
		{
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(5));
			g2.draw(borders);
		}
		
		
	}
	
	/**
	 * Checks if the date is select
	 * @return true if it is selected, otherwise return false
	 */
	public boolean isSelect()
	{
		return isSelected;
	}

	/**
	 * Sets select for the date
	 * @param select the boolean
	 */
	public void setSelect(boolean select)
	{
		isSelected = select;
	}
	
	/**
	 * Gets the date
	 * @return the date
	 */
	public int getDate()
	{
		return date;
	}
	
	/**
	 * Checks if the date is hightlighted or not
	 * @return true if it is highlighted and false if not
	 */
	public boolean getHighlight()
	{
		return isHighLight;
	}
	
	/**
	 * Sets highlight for the current date
	 * @param highlight the boolean value
	 */
	public void setHighlight(boolean highlight)
	{
		isHighLight = highlight;
	}
	
	/**
	 * Implements statechanged method
	 * @param arg0 the Change Event
	 */
	public void stateChanged(ChangeEvent arg0) {
		this.repaint();
	}
}
