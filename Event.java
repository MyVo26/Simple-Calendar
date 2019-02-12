/**
 * Models Event class
 * @author My Vo
 *
 */
public class Event 
{
	private String title;
	private String date;
	private String duration;
	/**
	 * Initializes variables for an event
	 * @param theTitle title of the event
	 * @param theDate date of the event
	 * @param theDuration duration of the event
	 */
	public void initialize(String theTitle, String theDate, String theDuration)
	{
		title = theTitle;
		date = PrintHelper.convertDateFormat(theDate);
		duration = theDuration;	
	}
	/**
	 * Constructor of Event
	 * @param theTitle the title of the event
	 * @param theDate the date of the event
	 * @param theDuration the duration of the event
	 */
	public Event(String theTitle, String theDate, String theDuration)
	{
		initialize(theTitle, theDate, theDuration);
	}
	/**
	 * Gets title of the evetn
	 * @return title title of the event
	 */
	public String getTitle()
	{
		return title;
	}
	/**
	 * Gets date of the event
	 * @return date of the event
	 */
	public String getDate()
	{
		return date;
	}
	/**
	 * Gets duration of the event
	 * @return duration duration of the event
	 */
	public String getDuration()
	{
		return duration;
	}
	@Override
	public String toString()
	{
		return PrintHelper.getDateInWords(date, true) + ", " + duration + " " + title;
	}
}
