// TO DO: add your implementation and JavaDocs.
/**
 *  This is the MyTime class.
 *  
 *  @author Luan Nguyen
 */
public class MyTime implements Comparable<MyTime> 
{

	// Hour and minute of a time.
	/**
	 *  This variable holds the number of hour.
	 */
	private int hour;
	/**
	 *  This variable holds the number of minute.
	 */
	private int min;

	/**
	 *  This is the default constructor, we set hour and minute to 0.
	 */
	public MyTime()
	{
		// Constructor
		// initialize time to be 00:00
		this.hour = 0;
		this.min = 0;

	}
	
	/**
	 *  This is the constructor with initial hour, we set the hour to initial hour and minute to 0.
	 *  @param hour is the desired hour.
	 */
	public MyTime(int hour)
	{
		// Constructor with hour specified
		// initialize time to be hour:00

		// A valid hour can only be within [0, 23].
		// For an invalid hour, throw IllegalArgumentException.
		// Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		// "Hour must be within [0, 23]!"
		if (hour>23 || hour<0)
			throw new IllegalArgumentException("Hour must be within [0, 23]!");
		else
		{
			this.hour = hour;
			this.min = 0;
		}

	}

	/**
	 *  This is the constructor with initial hour and initial minute, 
	 *  we set the hour and minute to initial hour and initial minute.
	 *  @param hour is the desired hour.
	 *  @param min is the desired minute.
	 */
	public MyTime(int hour, int min)
	{
		// Constructor with hour and minutes specified
		// initialize time to be hour:minute

		// A valid hour can only be within [0, 23].
		// A valid minute can only be within [0, 59].

		// For an invalid hour / minute, throw IllegalArgumentException.
		// Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		// "Hour must be within [0, 23]; Minute must be within [0, 59]!");
		if (hour>23 || hour<0)
			throw new IllegalArgumentException("Hour must be within [0, 23]!");
		if (min>59 || min<0)
			throw new IllegalArgumentException("Minute must be within [0, 59]!");
		this.hour = hour;
		this.min = min;

	}

	/**
	 *  This method reports the current number of hours.
	 *  @return the number of hours.
	 */
	public int getHour()
	{
		return this.hour;
	}
	
	/**
	 *  This method reports the current number of minutes.
	 *  @return the number of minutes.
	 */
	public int getMin()
	{
		// report minute

		return this.min;
	}
	
	/**
	 *  This method helps comparing two times for ordering.
	 *  @param otherTime is the time we comparing with.
	 *  @return a value that either >,< or = 0.
	 */
	@Override 
	public int compareTo(MyTime otherTime)
	{
		// compare two times for ordering
		// return the value 0 if the argument Time has the same hour and minute of this Time;
		// return a value less than 0 if this Time is before the Time argument; 
		// return a value greater than 0 if this Time is after the Time argument.
		int i = this.hour - otherTime.hour;
		if(i != 0)
			return i;

		return Integer.compare(this.min, otherTime.min); //default return, remove/change as needed
	}
	
	/**
	 *  This method return the number of minutes starting from this Time and ending at endTime.
	 *  @param endTime is the ending time.
	 *  @return the duration, -1 if it's invalid.
	 */
	public int getDuration(MyTime endTime)
	{
		// return the number of minutes starting from this Time and ending at endTime
		// return -1 if endTime is before this Time
		int dur = 0;
		int startM = this.getHour()*60 + this.getMin();
		int endM = endTime.getHour()*60 + endTime.getMin();
		dur = endM - startM;
		if(dur < 0)
			return -1;
		return dur;
		//default return, remove/change as needed		
	}
	
	/**
	 *  This method return return a Time object that is duration minute from this Time.
	 *  @param duration is the new duration.
	 *  @return the new ending time, return null otherwise.
	 */
	public MyTime getEndTime(int duration)
	{
		// return a Time object that is duration minute from this Time

		// Throw IllegalArgumentException if duration is negative. 
		// Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		// "Duration must be non-negative!"			

		// return null if endTime passes 23:59 given this Time and duration argument
		if(duration < 0)
			throw new IllegalArgumentException("Duration must be non-negative!");
		int durHours = 0;
		int durMinutes = 0;
		MyTime result;

		if(duration >= 60)
		{

			durHours = duration/60;
			durMinutes = duration - durHours*60;
			if(this.getMin() + durMinutes >= 60)
			{
				int newHour = this.getHour() + durHours +1;
				int newMin = this.getMin() + durMinutes -60;
				if(newHour <= 23 && newMin <= 59)
				{
					result = new MyTime(newHour, newMin);
					return result;
				}
				return null;

			}
			result = new MyTime(this.getHour() + durHours, this.getMin() + durMinutes);
			return result;
		}
		else
		{
			durHours = 0;
			durMinutes = duration;
			if(this.getMin() + durMinutes >= 60)
			{
				int newHour = this.getHour() + durHours +1;
				int newMin = this.getMin() + durMinutes -60;

				if(newHour <= 23 && newMin <= 59)
				{
					result = new MyTime(newHour, newMin);
					return result;
				}
				return null;

			}
			result = new MyTime(this.getHour() + durHours, this.getMin() + durMinutes);
			return result;
		}
	}
	
	/**
	 *  This method return a String representation of this object in the form of hh:mm.
	 *  @return the string.
	 */
	public String toString() 
	{
		// return a String representation of this object in the form of hh:mm
		// hh is the hour of the time (00 through 23), as two decimal digits
		// mm is the minute of the time (00 through 59), as two decimal digits

		// Hint: String.format() can be helpful here...
		String str ="";
		str = String.format("%02d:%02d", this.getHour(), this.getMin());

		return str; //default return, remove/change as needed		
	}

	/**
	 *  Main method to run the tests.
	 *  @param args command line argument.
	 */
	public static void main(String[] args){
		//This method is provided for testing 
		//(use/modify as much as you'd like)

		//time objects
		MyTime time1 = new MyTime(7);
		MyTime time2 = new MyTime(9,30);
		MyTime time4 = new MyTime(11,34);

		//checking hour/minute
		if (time1.getHour() == 7 && time1.getMin() == 0 && time2.getHour() == 9
				&& time2.getMin() == 30){
			System.out.println("Yay 1");			
		}		

		//compareTo, duration



		if (time1.compareTo(time2) < 0 && time1.compareTo(new MyTime(7,0)) == 0
				&& time2.compareTo(time1) > 0 && time1.getDuration(time2) == 150){
			System.out.println("Yay 2");						
		}
		//		
		//getEndTime



		MyTime time3 = time1.getEndTime(500);
		if (time3!=null && time3.getHour() == 15 && time3.getMin() == 20 
				&& time2.getEndTime(870) == null){
			System.out.println("Yay 3");								
		}



	}
}