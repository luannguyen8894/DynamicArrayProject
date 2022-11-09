// TO DO: add your implementation and JavaDocs.
/**
 *  This is the MySortedArray class.
 *  @param <T> generic type.
 *  @author Luan Nguyen
 */
public class MySortedArray<T extends Comparable<T>> 
{

	//default initial capacity / minimum capacity
	/**
	 *  This is the default capacity of the array.
	 */
	private static final int DEFAULT_CAPACITY = 2;

	//underlying array for storage -- you MUST use this for credit!
	//Do NOT change the name or type
	/**
	 *  This is the array that stores generic object types.
	 */
	private T[] data;

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	/**
	 *  This size keeps track of how many elements in the array.
	 */
	private int size;

	/**
	 *  This is the default constructor, we use DEFAULT_CAPACITY at set size equals 0.
	 */
	@SuppressWarnings("unchecked")
	public MySortedArray() 
	{
		// Constructor

		// Initial capacity of the storage should be DEFAULT_CAPACITY
		// Hint: Can't remember how to make an array of generic Ts? It's in the textbook...

		this.data = (T[])new Comparable[DEFAULT_CAPACITY];

		size = 0;
	}
	
	/**
	 *  This is the constructor with an initial capacity, we use initial capacity at set size equals 0.
	 *  @param initialCapacity is the desired capacity.
	 */
	@SuppressWarnings("unchecked")
	public MySortedArray(int initialCapacity) 
	{
		// Constructor

		// Initial capacity of the storage should be initialCapacity

		// Throw IllegalArgumentException if initialCapacity is smaller than 
		// 2 (which is the DEFAULT_CAPACITY). 
		// Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Capacity must be at least 2!"
		if(initialCapacity < 2)
			throw new IllegalArgumentException("Capacity must be at least 2!");
		else
		{
			this.data = (T[])new Comparable[initialCapacity];
			size = 0;
		}
	}

	/**
	 *  This method reports the current number of elements.
	 *  @return the size of the array.
	 */
	public int size() 
	{	
		// Report the current number of elements
		// O(1)

		return size; //default return, remove/change as needed

	}  
	/**
	 *  This method report max number of elements before the next expansion.
	 *  @return the length of the array.
	 */
	public int capacity() 
	{ 
		// Report max number of elements before the next expansion
		// O(1)

		return data.length; //default return, remove/change as needed
	}
	/**
	 *  This method help to sort the array in ascending order.
	 */
	private void sort()
	{
		for (int i = 0; i < size - 1; i++) 
		{
			if (data[i].compareTo(data[i+1]) > 0) 
			{
				T temp = data[i];
				data[i] = data[i + 1];
				data[i+1] = temp;
				i = -1;
			}
		}
	}
	/**
	 *  This method insert the given value into the array and keep the array _SORTED_.
	 *  @param value is what need to be added to the array.
	 */
	public void add(T value)
	{
		if(value == null)
			throw new IllegalArgumentException("Cannot add: null value!");
		else
		{
			if(size == data.length)
			{
				if(doubleCapacity() == true)
				{
					data[size] = value;
					size++;
				}
				else
				{
					throw new IllegalStateException("Cannot add: capacity upper-bound reached!");
				}
			}
			else
			{
				data[size] = value;
				size++;
			}

		}

		sort();
		// Insert the given value into the array and keep the array _SORTED_ 
		// in ascending order. 

		// If the array already contains value(s) that are equal to the new value,
		// make sure that the new value is added at the end of the group. Check examples
		// in project spec and main() below.

		// Remember to use .compareTo() to perform order/equivalence checking.

		// Note: You _can_ append an item (and increase size) with this method.
		// - You must call doubleCapacity() if no space is available. 
		// - Check below for details of doubleCapacity().
		// - For the rare case that doubleCapacity() fails to increase the max 
		//   number of items allowed, throw IllegalStateException.
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Cannot add: capacity upper-bound reached!"


		// Note: The value to be added cannot be null.
		// - Throw IllegalArgumentException if value is null. 
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Cannot add: null value!"

		// O(N) where N is the number of elements in the storage

	}
	
	/**
	 *  This method helps shifting the element when adding.
	 *  @param index is where need to perform add.
	 *  @param value is what need to be added to the array.
	 *  @return new array after shifting.
	 */
	private  T[] shifting(int index, T value)
	{
		T[] result = (T[]) new Comparable[data.length];
		if(index == 0)
		{
			result[0] = value;
			for(int i = 1; i<= size; i++)
				result[i] = data[i-1];
		}
		else 
		{
			for (int i = 0; i < index; i++) 
				result[i] = data[i];

			result[index] = value;

			for(int i = index + 1; i<= size; i++)
				result[i] = data[i-1];
		}
		return result;
	}
	/**
	 *  This method insert the given value into the array at the given index and keep the array _SORTED_.
	 *  @param index is where need to perform add.
	 *  @param value is what need to be added to the array.
	 *  @return true if adding success, false if it fails.
	 */
	public boolean add(int index, T value) 
	{
		// Insert the given value at the given index. Shift elements if needed.
		// Double capacity if no space available. 

		// For an invalid index, throw an IndexOutOfBoundsException. 
		// Use the same error message as get(index).
		// Note: You _can_ append items with this method, which is different from replace().
		if (index>size || index<0)
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		else
		{
			if(value == null)
				throw new IllegalArgumentException("Cannot add: null value!");
			else
			{
				//if array empty
				if(size == 0)
				{
					add(value);
				}
				//array not empty, index at size
				else if(index == size && data[index - 1].compareTo(value) <= 0)
				{
					//if array is full
					if(size == data.length)
					{
						if(doubleCapacity() == true)
						{
							data[size] = value;
							size++;
							return true;
						}
						else
						{
							throw new IllegalStateException("Cannot add: capacity upper-bound reached!");
						}
					}
					else
					{
						data[size] = value;
						size++;
						return true;
					}
				}
				//index >= 0 && index < size
				//need to have 2 separe cases, when index = 0 and when index > 0 && index < size
				else if(index == 0 && data[index].compareTo(value) >= 0 
						|| (index > 0) && (index < size) && (data[index].compareTo(value) >= 0)
						&&  data[index-1].compareTo(value) <= 0)
				{

					if(size == data.length)
					{
						if(doubleCapacity() == true)
						{
							data = shifting(index, value);
							size++;
							return true;
						}
						else
						{
							throw new IllegalStateException("Cannot add: capacity upper-bound reached!");

						}
					}
					else
					{
						data = shifting(index, value);
						size++;
						return true;
					}

				}
				else
					return false;

			}
		}
		// For a valid index, if value is null, throw IllegalArgumentException.
		// Use the exact same error message as add(value). See add(value) above.

		// The array must keep to be sorted in ascending order after updating. 
		// For a valid index, return false if inserting the value at index violates 
		// the required order hence can not be performed; no change should be made 
		// to the array.  Otherwise, insert the value and return true. 

		// You must call doubleCapacity() if no space is available. 
		// Throw IllegalStateException if doubleCapacity() fails.
		// Use the exact same error message as add(value). See add(value) above.

		// O(N) where N is the number of elements in the storage

		return false; //default return, remove/change as needed
	}
	
	/**
	 *  This method return the item at the given index.
	 *  @param index is where we need to get the item.
	 *  @return the element at index.
	 */
	public T get(int index) 
	{
		// Return the item at the given index

		// O(1)

		// For an invalid index, throw an IndexOutOfBoundsException.
		// Use this code to produce the correct error message for
		// the exception (do not use a different message):
		//	  "Index " + index + " out of bounds!"
		if (index>size-1 || index<0)
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		else
		{
			return data[index];
		}
		//default return, remove/change as needed

	}
	
	/**
	 *  This method change the item at the given index to be the given value.
	 *  @param index is where need to perform replace.
	 *  @param value is the new element.
	 *  @return true if adding success, false if it fails.
	 */
	public boolean replace(int index, T value) {
		// Change the item at the given index to be the given value.

		// For an invalid index, throw an IndexOutOfBoundsException. 
		// Use the same error message as get(index).
		// Note: You _cannot_ add new items with this method.
		if (index>size-1 || index<0)
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		else
		{
			if(value == null)
				throw new IllegalArgumentException("Cannot add: null value!");
			else
			{
				if(index == 0 && data[index+1].compareTo(value) >= 0 )
				{
					data[index] = value;
					return true;
				}
				else if(index == size-1 && value.compareTo(data[index-1]) > 0)
				{
					data[index] = value;
					return true;
				}
				else if((index > 0) && (index < size-1) && (data[index+1].compareTo(value) >= 0 )
						&&  data[index-1].compareTo(value) <= 0)
				{
					data[index] = value;
					return true;
				}
				else
					return false;
			}
		}
		// For a valid index, if value is null, throw IllegalArgumentException.
		// Use the exact same error message as add(value).

		// The array must keep to be sorted in ascending order after updating. 
		// For a valid index, return false if setting the value at index violates 
		// the required order hence can not be performed; no change should be made 
		// to the array.  Otherwise, change the item and return true. 

		// O(1)

		//default return, remove/change as needed

	}







	/**
	 *  This method double the max number of items allowed in data storage.
	 *  @return true if doubling success, false if it fails.
	 */
	@SuppressWarnings("unchecked")
	public boolean doubleCapacity()
	{
		int upperBound = Integer.MAX_VALUE - 50;
		T[] data2;
		// Double the max number of items allowed in data storage.
		// Remember to copy existing items to the new storage after expansion.

		// - Out of abundance of caution, we will use (Integer.MAX_VALUE - 50)
		//   as the upper-bound of our capacity.
		// - If double the current capacity would go beyond this upper-bound,
		//   use this upper-bound value as the new capacity.
		// - If the current capacity is already this upper-bound (Integer.MAX_VALUE - 50), 
		//   do not expand and return false.
		if(data.length == upperBound)
			return false;
		else
		{
			if((data.length)*2 > upperBound)
			{
				data2 = (T[]) new Comparable[upperBound];	
			}
			else
			{
				data2 = (T[]) new Comparable[size*2];	

			}
			//copy over
			for (int i=0; i<size; i++)
				data2[i] = data[i];

			this.data = data2;
			return true;
		}
		// Return true for a successful expansion.

		// O(N) where N is the number of elements in the array

		//default return, remove/change as needed

	}

	/**
	 *  This method remove and return the element at the given index.
	 *  @param index is where need to perform deletion.
	 *  @return the old element.
	 */
	public T delete(int index) 
	{
		// Remove and return the element at the given index. Shift elements
		// to remove the gap. Throw an exception when there is an invalid
		// index (see replace(), get(), etc. above).

		// After deletion, if the number of elements falls below 1/3 
		// of the capacity, you must call halveCapacity() to shrink the storage.
		// - Check halveCapacity() below for details.
		// - If the capacity would fall below the DEFAULT_CAPACITY, 
		//   shrink to the DEFAULT_CAPACITY. This should be implemented by
		//   halveCapacity().

		// O(N) where N is the number of elements currently in the storage
		if (index>size-1 || index<0)
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		else
		{
			//need to shrink
			if((size-1) < (double)(capacity())/3)
			{
				//shrink success
				if(halveCapacity() == true)
				{
					T temp = get(index);
					data = shiftingForDdeletion(index);
					size--;
					return temp;
				}
				//shrink fail
				else
					return null;

			}
			//no need to shrink
			else
			{
				T temp = get(index);
				data = shiftingForDdeletion(index);
				size--;
				return temp;

			}

		}
		//default return, remove/change as needed
	}  

	/**
	 *  This method helps shifting the element when deleting.
	 *  @param index is where need to perform deletion.
	 *  @return new array after shifting.
	 */
	private T[] shiftingForDdeletion(int index)
	{

		T[] result = (T[]) new Comparable[data.length];
		//delete front
		if(index == 0)
		{
			for(int i = 0; i<size-1; i++)
				result[i] = data[i+1];
		}
		//delete end
		if(index == size - 1)
		{
			for(int i = 0; i<size-1; i++)
				result[i] = data[i];
		}
		//delete where else
		if((index > 0) && (index < size - 1))
		{
			for (int i = 0; i < index; i++) 
				result[i] = data[i];

			for(int i = index; i<size - 1; i++)
				result[i] = data[i+1];
		}
		return result;

	}
	
	/**
	 *  This method reduce the max number of items allowed in data storage by half.
	 *  @return true if halving success, false if it fails.
	 */
	@SuppressWarnings("unchecked")
	public boolean halveCapacity()
	{
		// Reduce the max number of items allowed in data storage by half.
		// - If the current capacity is an odd number, _round down_ to get the 
		//   new capacity;
		// - If the new capacity would fall below the DEFAULT_CAPACITY, 
		//   shrink to the DEFAULT_CAPACITY;
		// - If the new capacity (after necessary adjustment to DEFAULT_CAPACITY) 
		//   cannot hold all existing items, do not shrink and return false;
		// - Return true for a successful shrinking.

		// Remember to copy existing items to the new storage after shrinking.

		// O(N) where N is the number of elements in the array


		int newCapacity = data.length/2;

		if(newCapacity < DEFAULT_CAPACITY)
			newCapacity = DEFAULT_CAPACITY;
		if(newCapacity< size)
			return false;
		T[] data2 = (T[]) new Comparable[newCapacity];
		//copy over
		for (int i=0; i<size; i++)
			data2[i] = data[i];
		this.data = data2;
		return true;
		//default return, remove/change as needed


	}
	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//*******		Remember to add JavaDoc			 *******
	//******************************************************
	
	/**
	 *  This method prints out the array.
	 *  @return the string with size, capacity and elements.
	 */
	public String toString() 
	{
		//This method is provided for debugging purposes
		//(use/modify as much as you'd like), it just prints
		//out the MySortedArray for easy viewing.
		StringBuilder s = new StringBuilder("MySortedArray with " + size() + " items and a capacity of " + capacity() + ":");
		for (int i = 0; i < size(); i++) {
			s.append("\n  ["+i+"]: " + get(i));
		}
		return s.toString();

	}

	/**
	 *  Main method to run the tests.
	 *  @param args command line argument.
	 */
	public static void main(String[] args)
	{
		//These are _sample_ tests. If you're seeing all the "yays" that's
		//an excellend first step! But it might not mean your code is 100%
		//working... You may edit this as much as you want, so you can add
		//own tests here, modify these tests, or whatever you need!

		//create a MySortedArray of integers
		MySortedArray<Integer> nums = new MySortedArray<>();
		if((nums.size() == 0) && (nums.capacity() == 2))
		{
			System.out.println("Yay 1");
		}


		//append some numbers 
		for(int i = 0; i < 3; i++) 
		{
			nums.add(i,i*2);
		}
		//uncomment to check the array details
		System.out.println(nums.toString());
		////		
		////	

		//num size = 3, index = 1


		if(!nums.add(nums.size(),1) && nums.size() == 3 && nums.get(2) == 4 && nums.capacity() == 4)
		{
			System.out.println("Yay 2");
		}
		System.out.println(nums.toString());
		//		
		//		//add more numbers, your insertion need to keep the array sorted
		nums.add(1);
		nums.add(-1);
		nums.add(5);
		if (nums.size() == 6 && nums.get(0)==-1 && nums.get(2) == 1 && nums.get(5) == 5)
		{
			System.out.println("Yay 3");
		}
		System.out.println(nums.toString());

		//add with index



		if (nums.add(4,2) && nums.add(3,2) && nums.get(3) == nums.get(4) 
				&& nums.get(4) == nums.get(5) && nums.get(5)== 2)
		{ 	

			System.out.println("Yay 4");		
		}
		System.out.println(nums.toString());

		//		//replace with index



		if (nums.replace(5,3) && nums.get(5)==3 && nums.replace(6,5) && nums.get(6)==5
				&& !nums.replace(1,2) && nums.get(1) == 0)
		{
			System.out.println("Yay 5");				
		}
		System.out.println(nums.toString());

		MySortedArray<String> names = new MySortedArray<>();
		//		
		//insert some strings
		names.add("alice");
		names.add("charlie");
		names.add("bob");
		names.add("adam");

		System.out.println(names.toString());

		//
		//delete
		if (names.add(4,"emma") && names.delete(3).equals("charlie"))
		{
			System.out.println("Yay 6");
		}

		names.delete(0);
		names.delete(0);

		//shrinking
		if (names.size()==2 && names.capacity() == 4)
		{
			System.out.println("Yay 7");
		}
		System.out.println(names.toString());

		//insert equal values: sorted by insertion order
		String dylan1 = new String("dylan");
		String dylan2 = new String("dylan");
		names.add(dylan1);
		names.add(dylan2);
		if (names.size()==4 && names.get(1) == dylan1 && names.get(2) == dylan2
				&& names.get(1)!=names.get(2))
		{
			System.out.println("Yay 8");		
		}
		//System.out.println(names.toString());

		// exception checking example
		// make sure you write more testings by yourself
		try
		{
			names.get(-1);
		}
		catch(IndexOutOfBoundsException ex)
		{
			if (ex.getMessage().equals("Index -1 out of bounds!"))
				System.out.println("Yay 9");
		}
		//		
		// call doubleCapacity() and halveCapacity() directly
		if (names.doubleCapacity() && names.capacity() == 8 
				&& names.halveCapacity() && names.capacity() == 4
				&& !names.halveCapacity() && names.capacity() == 4)
		{
			System.out.println("Yay 10");

		}
		System.out.println(names.toString());

	}


}