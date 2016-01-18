/**
 * 
 */
package spelling;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Sridhar
 *
 */
public class NearbyWordsTester {
	
	private NearbyWords instance;
	private List<String> currentList; 
	
	@Before
	public void setup() {
		currentList = new ArrayList<>();
		
		Dictionary d = new DictionaryHashSet(); 
		DictionaryLoader.loadDictionary(d, "data/dict.txt"); 
		instance = new NearbyWords(d);
	}
	
	@After
	public void teardown() {		
	}
	
	@Test
	public void testSubstitution() {
		instance.subsitution("A", currentList, true);		
		assertTrue(currentList.size() == 26);
		assertTrue(currentList.contains("g"));
		assertTrue(currentList.contains("x"));
		assertTrue(currentList.contains("p"));
	}
	
	@Test
	public void testInsertions() {
		instance.insertions("A", currentList, true);		
		assertTrue(currentList.size() == 26);		
		assertFalse(currentList.contains("aA"));
		assertTrue(currentList.contains("Am"));
		assertTrue(currentList.contains("An"));
	}

	@Test
	public void testDeletions() {
		instance.deletions("Ae", currentList, true);
		assertTrue(currentList.size() == 2);
		assertTrue(currentList.contains("A"));
		assertTrue(currentList.contains("e"));
	}
	
	@Test
	public void testDistanceOne() {
		List<String> words = instance.distanceOne("A", true);
		assertTrue(words.size() == 52);
	}
	
}
