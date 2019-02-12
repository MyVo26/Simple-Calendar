import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Implements a top panel which include "CREATE" "NEXT" "PREVIOUS" "QUIT" buttons
 * @author My Vo
 *
 */
public class TopPanel extends JPanel{
	private final int WIDTH = 900;
	private final int HEIGHT = 30;
	private JButton createBut;
	private JButton nextBut;
	private JButton prevBut;
	private JButton QuitAndSaveBut;
	/**
	 * Constructs a top panel
	 */
	public TopPanel()
	{
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setLayout(null);
		this.setVisible(true);
		createBut = new JButton("CREATE");
		createBut.setFont(new Font("Serif", Font.PLAIN, 20));
		nextBut = new JButton("Next Day");
		prevBut = new JButton("Previous Day");
		QuitAndSaveBut = new JButton("QUIT & SAVE");
		this.add(createBut);
		this.add(nextBut);
		this.add(prevBut);
		this.add(QuitAndSaveBut);
		createBut.setBounds(0,0,HEIGHT*6,HEIGHT);
		prevBut.setBounds(HEIGHT*6,0,HEIGHT*6,HEIGHT);
		nextBut.setBounds(HEIGHT*12,0,HEIGHT*6,HEIGHT);
		QuitAndSaveBut.setBounds(HEIGHT*18, 0, HEIGHT*6, HEIGHT);
	}
	
	/**
	 * Attaches a listener to create button
	 * @param action the action listener
	 */
	public void attatchCreateListener(ActionListener action)
	{
		createBut.addActionListener(action);
	}
	
	/**
	 * Attaches an actionListener to previous button
	 * @param action the action listener
	 */
	public void attatchPrevListener(ActionListener action)
	{
		prevBut.addActionListener(action);
	}
	
	/**
	 * Attaches an actionListener to next button
	 * @param action the action listener
	 */
	public void attatchNextListener(ActionListener action)
	{
		nextBut.addActionListener(action);
	}
	
	/**
	 * Attaches an actionListener to quit button
	 * @param action
	 */
	public void attachQuitListener(ActionListener action)
	{
		QuitAndSaveBut.addActionListener(action);
	}
}
