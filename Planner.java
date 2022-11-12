// TO DO: add your implementation and JavaDocs.

/**
 *  This is the Event class.
 *  
 *  @author Luan Nguyen
 */
public class Planner
{

	// DO NOT MODIFY INSTANCE VARIABLES PROVIDED BELOW

	//underlying array of events  -- you MUST use this for credit!
	//Do NOT change the name or type
	
	/**
	 *  This variable holds an array of events.
	 */
	private MySortedArray<Event> events;

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	
	/**
	 *  This is the default constructor where we initialized our event array.
	 */
	public Planner()
	{
		// Constructor with no arguments.

		// A list of events should be created.  The initial capacity should be 
		// DEFAULT_CAPACITY defined in our MySortedArray class. 
		// The list should be empty (with no events).
		this.events = new MySortedArray<>();
	}
	
	/**
	 *  This method return the number of events in the list.
	 *  @return the number of events.
	 */
	public int size()
	{
		// return the number of events in the list.
		// O(1)

		return this.events.size(); //default return, remove/change as needed
	}
	
	/**
	 *  This method return the string representation of the planner.
	 *  @return the string representation.
	 */
	public String toString()
	{
		// return the string representation of the planner with this format:
		// - include all events in the list in ascending order of the indexes;
		// - each event goes into a separate line;
		// - each line except for the last uses this format (quotes excluded): "[index]event\n"
		// - the last line does not end with a new line and uses this format: "[index]event"

		// The format of an event is the same as .toString() of Event class

		// Hint: String.format() can be helpful here...

		// Note: use StringBuilder instead of String concatenation for a better efficiency 
		StringBuilder str = new StringBuilder();
		int n = this.size();
		for(int i = 0; i < n; i++)
		{
			if(i == n-1)
			{
				str.append("["+i+"]");
				str.append(events.get(i).toString());
			}
			else
			{
				str.append("["+i+"]");
				str.append(events.get(i).toString());
				str.append("\n");
			}
		}

		return str.toString(); //default return, remove/change as needed
	}
	
	/**
	 *  This method add a new event into the list.
	 *  @param event is the event we adding.
	 */
	public void addEvent(Event event)
	{
		this.events.add(event);
		// Add a new event into the list
		//	- make sure events are sorted after addition

		// O(N) where N is the number of events in the list

	}
	
	/**
	 *  This method move the event at index to be start at newStart.
	 *  @param index is the place we change the start time.
	 *  @param newStart is the new starting time.
	 *  @return true if event can be updated; return false otherwise.
	 */
	public boolean moveEvent(int index, MyTime newStart)
	{
		// Move the event at index to be start at newStart.
		// Hint: we will keep the same duration but the end time may need to be changed.

		// Return true if event can be updated; return false otherwise.
		// - return false for an invalid index
		// - return false if event cannot be moved to newStart

		// If with the updated starting time, the events are still sorted in ascending 
		// order of their starting times, do not change the index of the event.
		// Otherwise, fix the ordering by first removing the updated event, 
		// then adding it back.

		// O(N) where N is the number of events currently in list

		if(index < 0 || index > events.size()-1)
			return false;
		Event temp = this.events.get(index);

		if(temp.moveStart(newStart) == true)
		{

			if(this.events.replace(index, temp) == true)
				return true;
			else
				return false;
		}
		return false; //default return, remove/change as needed
	}

	
	/**
	 *  This method change the duration of event at index to be the given number of minutes.
	 *  @param index is the place we change the duration time.
	 *  @param minute is the new duration time.
	 *  @return true if duration can be changed; return false otherwise.
	 */
	public boolean changeDuration (int index, int minute)
	{
		// Change the duration of event at index to be the given number of minutes.

		// Return true if the duration can be changed.
		// Return false if:
		// - index is invalid; or
		// - minute is negative; or
		// - the duration of event at index can not be updated with the specified minute

		// O(1)
		if(index < 0 || index > events.size()-1)
			return false;
		Event temp = this.events.get(index);
		if(temp.changeDuration(minute) == true)
			return true;
		return false; //default return, remove/change as needed

	}

	/**
	 *  This method change the description of event at index.
	 *  @param index is the place we change the description.
	 *  @param description is the new description.
	 *  @return true if description can be changed; return false otherwise.
	 */
	public boolean changeDescription(int index, String description)
	{
		// Change the description of event at index.

		// Return true if the event can be changed.
		// Return false for an invalid index.

		// O(1)
		if(index < 0 || index > events.size()-1)
			return false;
		Event temp = this.events.get(index);
		temp.setDescription(description);
		if(this.events.replace(index, temp) == true)
			return true;
		return false; //default return, remove/change as needed
	}
	
	/**
	 *  This method remove the event at index.
	 *  @param index is the place we perform removing.
	 *  @return true if the event can be removed; return false otherwise.
	 */
	public boolean removeEvent(int index)
	{
		// Remove the event at index.

		// Return true if the event can be removed
		// Return false for an invalid index.

		// O(N) where N is the number of elements currently in the storage
		if(index < 0 || index > events.size()-1)
			return false;
		if(events.delete(index) != null)
			return true;
		return false; //default return, remove/change as needed
	}

	
	/**
	 *  This method return the event at index.
	 *  @param index is the place we perform getting event.
	 *  @return the event if the event can be returned; return null otherwise.
	 */
	public Event getEvent(int index)
	{
		// Return the event at index

		// Return null for an invalid index.

		//O(1)
		if(index < 0 || index > events.size()-1)
			return null;
		return this.events.get(index); //default return, remove/change as needed
	}
	
	/**
	 *  Main method to run the tests.
	 *  @param args command line argument.
	 */
	public static void main(String[] args){

		// creating a planner
		Planner day1 = new Planner();

		// adding two events		
		Event breakfast = new Event(new MyTime(7), new MyTime(7,30), "breakfast");
		Event jogging = new Event(new MyTime(5), new MyTime(6), "jogging");
		day1.addEvent(breakfast);
		day1.addEvent(jogging);




		if (day1.size()==2 && day1.getEvent(0)==jogging && day1.getEvent(1)==breakfast ){
			System.out.println("Yay 1");					
		}

		//toString
		
		if (day1.toString().equals("[0]05:00-06:00/jogging\n[1]07:00-07:30/breakfast")){
			System.out.println("Yay 2");							
		}
		System.out.println(day1);

		// move start of breakfast		
		MyTime newBFTime = new MyTime(6,30);

		if (day1.moveEvent(1, newBFTime) && day1.getEvent(1).getStart().getHour() == 6
				&& day1.getEvent(1).getStart().getMin() == 30){
			System.out.println("Yay 3");								
		}
		//System.out.println(day1);

		// change duration
		if (day1.changeDuration(0, 45) && day1.getEvent(0).getEnd().getHour() == 5 
				&& day1.getEvent(0).getEnd().getMin() == 45 && day1.changeDuration(1, 60)
				&& day1.getEvent(1).getEnd().getHour() == 7 
				&& day1.getEvent(1).getEnd().getMin() == 30){
			System.out.println("Yay 4");											
		}
		//System.out.println(day1);

		// change description, remove
		if (day1.changeDescription(1, "sleeping") && !day1.removeEvent(3) 
				&& !day1.removeEvent(-2) && day1.removeEvent(0)){
			System.out.println("Yay 5");							
		}
		//System.out.println(day1);

	}
}