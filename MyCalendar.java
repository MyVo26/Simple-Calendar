import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
/**
 * Models MyCalendar
 * @author My Vo
 *
 */
public class MyCalendar 
{
	private ArrayList<Event> eventList;
	/**
	 * Initializes Even tList
	 */
	public void initializeEventList()
	{
		eventList = new ArrayList<Event>();
	}
	/**
	 * Constructor of MayCalendar
	 */
	public MyCalendar()
	{
		initializeEventList();
	}
	/**
	 * Sorts the event list
	 * @param list
	 * @return
	 */
	public ArrayList<Event> sort(ArrayList<Event> list)
	{
		Comparator<Event> compareByDateTime = new Comparator<Event>()
				{
					public int compare(Event e1, Event e2)
					{
						int compareDate = e1.getDate().compareTo(e2.getDate());
						if (compareDate == 0)
						{
							return e1.getDuration().compareTo(e2.getDuration());
						}
						return compareDate;
					}
				};
		Collections.sort(list, compareByDateTime);
		return list;
	}
	/**
	 * Loads the events file to get the stored events	
	 * @throws IOException
	 */
	public void load() throws IOException
	{
		int n = 0;
		initializeEventList();
		ArrayList<String> eventsFile = new ArrayList<String>();
		if (new File("events.txt").exists() == false)
		{
			System.out.println("This is the first run");
			return;
		}
		BufferedReader in = new BufferedReader(new FileReader("events.txt"));
		String line;
		while ((line = in.readLine()) != null)
		{	
			eventsFile.add(line);
		}
		in.close();
		while (eventsFile.size() > n)
		{
			String title = eventsFile.get(n);
			String date = eventsFile.get(n + 1);
			String duration = eventsFile.get(n + 2);
			n = n + 3;
			eventList.add(new Event(title, date, duration));
		}
	}
	/**
	 * Checks if the time of a new event is in between the time of other events
	 * @param duration1 the first duration
	 * @param duration2 the second duration
	 * @return true if the time of a new event is in between the time of other events
	 * Otherwise, return false
	 */
	public boolean isTimeLineBetween(String[] duration1, String[] duration2)
	{
		if (duration1[0].compareTo(duration2[0]) <= 0 && duration1[1].compareTo(duration2[0]) > 0)
		{
			return true;
		}
		if (duration1[0].compareTo(duration2[1]) < 0 && duration1[1].compareTo(duration2[1]) >= 0)
		{
			return true;
		}
		return false;
	}
	/**
	 * Checks if a new event conflicts with the event in the Event List
	 * @param e a new event that needs to be checked
	 * @return true if the new event is conflicted with the events in the list
	 */
	public boolean isConflictedEvent(Event e)
	{
		for (Event theE : eventList)
		{
			String[] duration1 = theE.getDuration().split("-");
			String[] duration2 = e.getDuration().split("-");
			if (theE.getDate().compareTo(e.getDate()) == 0)
			{		
				if(duration1.length == 1 && duration1[0].compareTo(duration2[0]) <= 0 )
				{
					return true;
				}

				if (isTimeLineBetween(duration1,duration2) == true || isTimeLineBetween(duration2, duration1) == true)
				{
					return true;
				}
				return false;
			}			
		}
		return false;
	}
	
	/**
	 * Checks if the event can be added
	 * @param e the event needs to be added
	 * @return true if the event can be added
	 */
	public boolean addEvent(Event e)
	{
		if (isConflictedEvent(e))
		{
			return false;
		}
		eventList.add(e);
		this.eventList = sort(this.eventList);
		return true;
	}	
	/**
	 * Gets the event by date
	 * @param date the date that occurs the event
	 * @return eventDate the dates of the events
	 */
	public ArrayList<Event> getEventByDate(String date)
	{
		date = PrintHelper.convertDateFormat(date);
		ArrayList<Event> eventDate = new ArrayList<Event>();	
		for (Event e : eventList)
		{
			if (date.equals(e.getDate()))
			{
				eventDate.add(e);
			}
		}
		return eventDate;
	}
	/**
	 * Gets an event by its index
	 * (helper function using for delete a selected event by choosing event's index
	 * @param list
	 * @param date
	 * @param index
	 * @return
	 */
	public int getEventIndex(ArrayList<Event> list, String date, int index)
	{	
		int ite = 0;
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).getDate().equals(date))
			{
				ite = i;
				break;
			}
		}
		for (int i = 0; i < index; i++)
		{
			ite++;
		}
		return ite;
	}
	
	/**
	 * Deletes a selected events
	 * @param date the date whose event needs to be deleted
	 * @param index the index of the event needs to be deleted
	 */
	public void deleteSelectedEvent(String date, int index,MyCalendar cal)
	{
		eventList.remove(getEventIndex(cal.eventList, date, index));		
		this.eventList = sort(this.eventList);
	}
	/**
	 * Deletes all the events on specific date
	 * @param date the date needs to be delete all events
	 */
	public void deleteAllEvent(String date)
	{
		ArrayList<Event> aList = getEventByDate(date);
		for (Event e : aList)
		{
			eventList.remove(e);
		}
	}	
	/**
	 * Terminates the program and writes the events to file events.txt
	 * @throws IOException
	 */
	public void quit() throws IOException
	{
		File file = new File("events.txt");
		FileWriter writer = new FileWriter(file);
		if (eventList.size() != 0)
		{
			for(Event e : eventList)
			{
				writer.write(e.getTitle());
				writer.write("\n");
				writer.write(e.getDate());
				writer.write("\n");
				writer.write(e.getDuration());
				writer.write("\n");				
				// force all data to be written before close the stream
				writer.flush();	
			}			
		}		
		writer.close();
	}
	/**
	 * Prints the event list in English
	 */
	public void printEventList()
	{
		if (eventList.size() == 0)
		{
			System.out.println("There is NO event on the input date");
			return;
		}
		int[] dateArray = PrintHelper.dateStringToInt((eventList.get(0).getDate()));
		int currentYear = dateArray[0];
		System.out.println(currentYear);
		for(Event e : eventList)
		{  
			String date = e.getDate();
			dateArray = PrintHelper.dateStringToInt(date);
			if (dateArray[0] != currentYear)
			{
				System.out.println(dateArray[0]);
				currentYear = dateArray[0];
			}			
			System.out.println("\t" + PrintHelper.getDateInWords(date, false) + ", "
											+ e.getDuration() + " " + e.getTitle());	
		}  
	}
	/**
	 * Helper function for view by month
	 * Highlight the days which occur event(s)
	 * @param year the year occurring the event
	 * @param month the year occurring the event
	 * @return days the days highlighted by brackets
	 */
	public ArrayList<Integer> hightlightDays(int year, int month)
	{
		int[] today = PrintHelper.getToday();
		ArrayList<Integer> result;
		TreeSet<Integer> highlightD = new TreeSet<Integer>();		
		int i = 0;
		for (Event e : eventList)
		{
			int[] dateInt = PrintHelper.dateStringToInt(e.getDate());
			if (dateInt[0] == year && dateInt[1]-1 == month)
			{
				highlightD.add(dateInt[2]);
			}
		}
		result = new ArrayList<Integer>(highlightD);
		return result;
	}
	
}
