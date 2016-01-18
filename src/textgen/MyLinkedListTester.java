/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {
			list1.remove(2);
			fail("Supposed to fail");
		} catch(IndexOutOfBoundsException e) {			
		}
		
		try {
			list1.remove(-1);
			fail("Supposed to fail");
		} catch(IndexOutOfBoundsException e) {			
		}
		
		int b = list1.remove(1);
		assertEquals("Remove: check b is correct ", 42, b);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 1, list1.size());		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		try {
			emptyList.add(null);
			fail("Supposed to fail");
		} catch(NullPointerException e) {			
		}
		
		boolean value = emptyList.add(10);
		assertTrue(value);
		assertEquals("Add: check if element is added correctly", (Integer)10, emptyList.get(0));
		assertEquals("Add: check size is correct ", 1, emptyList.size());	
		
		boolean value1 = emptyList.add(20);
		assertTrue(value1);
		assertEquals("Add: check if element is added correctly", (Integer)20, emptyList.get(1));
		assertEquals("Add: check size is correct ", 2, emptyList.size());
		
		emptyList.remove(0);
		assertEquals("Add: check if element is added correctly", (Integer)20, emptyList.get(0));
		assertEquals("Add: check size is correct ", 1, emptyList.size());
		
		boolean value2 = emptyList.add(10);
		assertTrue(value2);
		assertEquals("Add: check if element is added correctly", (Integer)10, emptyList.get(1));
		assertEquals("Add: check size is correct ", 2, emptyList.size());
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		MyLinkedList<Integer> mylist = new MyLinkedList<Integer>();
		assertEquals("Size: check size is correct ", 0, mylist.size());
		
		mylist.add(10);
		mylist.add(20);
		assertEquals("Size: check size is correct ", 2, mylist.size());
		
		mylist.remove(0);
		mylist.remove(0);
		assertEquals("Size: check size is correct ", 0, mylist.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			emptyList.add(0, null);
			fail("Supposed to fail");
		} catch(NullPointerException e) {			
		}
		
		try {
			emptyList.add(-1, 10);
			fail("Supposed to fail");
		} catch(IndexOutOfBoundsException e) {			
		}
		
		try {
			emptyList.add(2, 10);
			fail("Supposed to fail");
		} catch(IndexOutOfBoundsException e) {			
		}
		
		emptyList.add(0, 10);		
		assertEquals("Add: check if element is added correctly", (Integer)10, emptyList.get(0));
		assertEquals("Add: check size is correct ", 1, emptyList.size());	
		
		emptyList.add(0, 20);
		assertEquals("Add: check if element is added correctly", (Integer)10, emptyList.get(1));
		assertEquals("Add: check size is correct ", 2, emptyList.size());
		
		emptyList.remove(0);
		assertEquals("Add: check if element is added correctly", (Integer)10, emptyList.get(0));
		assertEquals("Add: check size is correct ", 1, emptyList.size());
		
		boolean value2 = emptyList.add(30);
		assertTrue(value2);
		assertEquals("Add: check if element is added correctly", (Integer)30, emptyList.get(1));
		assertEquals("Add: check size is correct ", 2, emptyList.size());	
		
		emptyList.add(1, 40);		
		assertEquals("Add: check if element is added correctly", (Integer)40, emptyList.get(1));
		assertEquals("Add: check size is correct ", 3, emptyList.size());		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try {
			emptyList.set(0, 10);
			fail("Supposed to fail");
		} catch(IndexOutOfBoundsException e) {			
		}
		
		try {
			emptyList.set(-1, 10);
			fail("Supposed to fail");
		} catch(IndexOutOfBoundsException e) {			
		}
		
		try {
			emptyList.set(2, 10);
			fail("Supposed to fail");
		} catch(IndexOutOfBoundsException e) {			
		}		
		
		try {
			shortList.set(0, null);
			fail("Supposed to fail");
		} catch(NullPointerException e) {			
		}		
	    		
		String data = shortList.set(0, "A1");
		assertEquals("Add: check if element is added correctly", (String) "A", data);
		assertEquals("Add: check if element is added correctly", (String) "A1", shortList.get(0));
		assertEquals("Add: check size is correct ", 2, shortList.size());
		
		String data1 = shortList.set(1, "B1");
		assertEquals("Add: check if element is added correctly", (String) "B", data1);
		assertEquals("Add: check if element is added correctly", (String) "B1", shortList.get(1));
		assertEquals("Add: check size is correct ", 2, shortList.size());
	}
	
	
	// TODO: Optionally add more test methods.
	
}
