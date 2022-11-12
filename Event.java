// TO DO: add your implementation and JavaDocs.

/**
 *  This is the Event class.
 *  
 *  @author Luan Nguyen
 */
public class Event implements Comparable<Event> 
{

	//starting and ending time of the event
	/**
	 *  This variable holds the starting time of the event.
	 */
	private MyTime startTime;
	/**
	 *  This variable holds the starting time of the event.
	 */
	private MyTime endTime;

	//description of the event
	/**
	 *  This variable holds the description of the event.
	 */
	private String description;
	
	/**
	 *  This is the constructor with an initial startTime and endTime.
	 *  @param startTime is the desired starting time.
	 *  @param endTime is the desired ending time.
	 */
	public Event(MyTime startTime, MyTime endTime)
	{
		// constructor with start and end times
		// set description to be empty string ""

		// Throw IllegalArgumentException if endTime comes before startTime
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//        "End Time cannot come before Start Time!"
		// - Assume that the start time can be the same as the end time 
		//   (0-duration event allowed)
		this.description = "";
		if(startTime.compareTo(endTime) > 0)
			throw new IllegalArgumentException("End Time cannot come before Start Time!");
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 *  This is the constructor with an initial startTime, endTime and description.
	 *  @param startTime is the desired starting time.
	 *  @param endTime is the desired ending time.
	 *  @param description is the desired description.
	 */
	public Event(MyTime startTime, MyTime endTime, String description)
	{
		// constructor with start time, end time, and description

		// perform the same checking of start/end times and 
		// throw the same exception as the constructor above

		// if description argument is null, 
		// set description of the event to be empty string ""
		if(startTime.compareTo(endTime) > 0)
			throw new IllegalArgumentException("End Time cannot come before Start Time!");
		this.startTime = startTime;
		this.endTime = endTime;

		if(description == null)
			this.description = "";
		else
			this.description = description;

	}
	
	/**
	 *  This method reports the starting time of the event.
	 *  @return the starting time.
	 */
	public MyTime getStart()
	{
		// report starting time

		return this.startTime; //default return, remove/change as needed
	}
	
	/**
	 *  This method reports the ending time of the event.
	 *  @return the ending time.
	 */
	public MyTime getEnd()
	{
		// report starting time

		return this.endTime; //default return, remove/change as needed
	}
	
	/**
	 *  This method reports the description time of the event.
	 *  @return the description.
	 */
	public String getDescription()
	{
		// report description

		return this.description; //default return, remove/change as needed
	}



	/**
	 *  This method helps comparing two events for ordering.
	 *  @param otherEvent is the event we comparing with.
	 *  @return a value that either >,< or = 0.
	 */
	@Override 
	public int compareTo(Event otherEvent)
	{
		// compare two times for ordering

		// The ordering of two events is the same as the ordering of their start times


		return this.getStart().compareTo(otherEvent.getStart()); //default return, remove/change as needed

	}

	/**
	 *  This method move the start time of this Event to be newStart but keep the same duration.
	 *  @param newStart is the new starting time of the event.
	 *  @return true if the start time can be moved to newStart successfully, otherwise false .
	 */
	public boolean moveStart(MyTime newStart)
	{
		// Move the start time of this Event to be newStart but keep the same duration. 
		// - Remember to update the end time to ensure duration unchanged.

		// The start time can be moved forward or backward but the end time cannot 
		// go beyond 23:59 of the same day.  Do not update the event if this condition
		// cannot be satisfied and return false.  
		// Return true if the start time can be moved to newStart successfully.

		// Note: a false return value means the specified newStart can not be used 
		//       for the current event.  Hence if newSart is the same as the current 
		//       start, we will still return true.
		if(this.getStart().compareTo(newStart) == 0)
			return true;
		int dur = this.getStart().getDuration(this.getEnd());
		if(dur != -1)
		{
			this.startTime = newStart;
		}
		else
			return false;


		if(this.startTime.getEndTime(dur) != null)
		{
			this.endTime = this.startTime.getEndTime(dur);
			return true;
		}

		return false; //default return, remove/change as needed
	}
	
	/**
	 *  This method change the duration of event to be the given number of minutes.
	 *  @param minute is the new duration of the event.
	 *  @return true if the duration can be changed, otherwise false .
	 */
	public boolean changeDuration(int minute)
	{
		// Change the duration of event to be the given number of minutes.
		// Update the end time of event based on the updated duration.	

		// The given minute cannot be negative; and the updated end time cannot go 
		// beyond 23:59 of the same day.  Do not update the event if these conditions
		// cannot be satisfied and return false.  
		// Return true if the duration can be changed.

		// Note: a false return value means the specified duration is invalid for some 
		// 		 reason.  Hence if minute argument is the same as the current duration, 
		//       we will still return true.
		if(minute < 0)
			return false;
		else
		{
			if(this.getStart().getEndTime(minute) != null)
			{
				this.endTime = this.getStart().getEndTime(minute);
				return true;
			}
		}
		return false; //default return, remove/change as needed

	}

	/**
	 *  This method set the description of this event.
	 *  @param newDescription is the new description of the event.
	 */
	public void setDescription(String newDescription)
	{
		// set the description of this event

		// if newDescription argument is null, 
		// set description of the event to be empty string ""
		if(newDescription == null)
			this.description = "";
		else
			this.description = newDescription;

	}

	/**
	 *  This method return a string representation of the event in the form of startTime-endTime/description.
	 *  @return the string.
	 */
	public String toString()
	{
		// return a string representation of the event in the form of
		// startTime-endTime/description
		// example: "06:30-07:00/breakfast"

		// Hint: String.format() can be helpful here...

		// The format of start/end times is the same as .toString() of MyTime
		String str ="";
		str = this.getStart().toString()+"-"+this.getEnd().toString()+"/"+this.getDescription();

		return str; //default return, remove/change as needed

	}
	
	/**
	 *  Main method to run the tests.
	 *  @param args command line argument.
	 */
	public static void main(String[] args)
	{
		// creating an event
		Event breakfast = new Event(new MyTime(7), new MyTime(7,30), "breakfast");





		if (breakfast.getStart()!=null && breakfast.getEnd()!=null &&
				breakfast.getStart().getHour() == 7 && breakfast.getEnd().getHour() == 7 && 
				breakfast.getStart().getMin() == 0 && breakfast.getEnd().getMin() == 30){
			System.out.println("Yay 1");			
		}	


		System.out.println(breakfast);
		//expected output (excluding quote):
		//"07:00-07:30/breakfast"

		// moveStart

		if (breakfast.moveStart(new MyTime(6,30)) && breakfast.getStart().getHour() == 6
				&& breakfast.getStart().getMin() == 30 && breakfast.getEnd().getMin() == 0){
			System.out.println("Yay 2");					
		}
		System.out.println(breakfast);
		//		
		//longer duration
		if (breakfast.changeDuration(45) && breakfast.getStart().getHour() == 6
				&& breakfast.getStart().getMin() == 30 && breakfast.getEnd().getMin() == 15
				&& breakfast.getEnd().getHour() == 7){

			System.out.println("Yay 3");					
		}
		System.out.println(breakfast);
		//		
		//		//shorter duration
		if (!breakfast.changeDuration(-10) && breakfast.changeDuration(15) 
				&& breakfast.getStart().getHour() == 6 && breakfast.getStart().getMin() == 30 
				&& breakfast.getEnd().getMin() == 45 && breakfast.getEnd().getHour() == 6){
			System.out.println("Yay 4");					
		}
		System.out.println(breakfast);
		//		
		//		// compareTo
		Event jogging = new Event(new MyTime(5), new MyTime(6), "jogging");
		Event morningNews = new Event(new MyTime(6, 30), new MyTime(7), "morning news");
		if (breakfast.compareTo(jogging)>0 && jogging.compareTo(breakfast)<0
				&& breakfast.compareTo(morningNews) == 0){
			System.out.println("Yay 5");								
		}
	}

}