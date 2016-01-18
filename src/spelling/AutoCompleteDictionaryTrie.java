package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete
 * ADT
 * 
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

	private TrieNode root;
	private int size;

	public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
	}

	/**
	 * Insert a word into the trie. For the basic part of the assignment (part
	 * 2), you should ignore the word's case. That is, you should convert the
	 * string to all lower case as you insert it.
	 */
	public boolean addWord(String word) {
		if (word == null || word.isEmpty()) {
			return false;
		}

		TrieNode tmp = root;
		word = word.toLowerCase();
		
		// return false if word already exists
		if (isFound(tmp, word)) {
			return false;
		}
		
		char[] characters = word.toCharArray();
		for (char c : characters) {
			TrieNode node = tmp.insert(c);
			if (node == null) {
				node = tmp.getChild(c);
			}

			// move the pointer down
			tmp = node;
			
			// return false if word already exists
			if (isFound(tmp, word)) {
				return false;
			}
		}		

		// set the word to true after looping through all the characters
		tmp.setEndsWord(true);
		
		size++;

		return true;
	}

	/**
	 * Return the number of words in the dictionary. This is NOT necessarily the
	 * same as the number of TrieNodes in the trie.
	 */
	public int size() {
		return size;
	}

	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) {
		if (s == null || s.isEmpty()) {
			return false;
		}
		
		s = s.toLowerCase();
		
		TrieNode tmp = root;		
		if (isFound(tmp, s)) {
			return true;
		}
		
		char[] characters = s.toCharArray();
		for (char ch : characters) {
			tmp = tmp.getChild(ch);
			if (tmp == null) {
				return false;
			} else if (isFound(tmp, s)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * * Returns up to the n "best" predictions, including the word itself, in
	 * terms of length If this string is not in the trie, it returns null.
	 * 
	 * @param text
	 *            The text to use at the word stem
	 * @param n
	 *            The maximum number of predictions desired.
	 * @return A list containing the up to n best predictions
	 */
	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {
		List<String> foundWords = new ArrayList<>();
		if (prefix == null) {
			return foundWords;
		}
				
		TrieNode stem = root;
		
		char[] characters = prefix.toCharArray();
		for (char ch : characters) {
			stem = stem.getChild(ch);
			if (stem == null) {
				return foundWords;
			}
		}
		
		LinkedList<TrieNode> bfs = new LinkedList<>();
		bfs.addLast(stem); // add the stem node to the list
		
		while (!bfs.isEmpty() && foundWords.size() < numCompletions) {
			TrieNode node = bfs.removeFirst();
			if (node.endsWord()) {
				foundWords.add(node.getText());
			}
			
			// Add the child nodes to the back of the queue 
			Set<Character> charKeySet = node.getValidNextCharacters();
			for (char key : charKeySet) {
				bfs.addLast(node.getChild(key));
			}
		}
		
		// TODO: Implement this method
		// This method should implement the following algorithm:
		// 1. Find the stem in the trie. If the stem does not appear in the
		// trie, return an
		// empty list
		// 2. Once the stem is found, perform a breadth first search to generate
		// completions
		// using the following algorithm:
		// Create a queue (LinkedList) and add the node that completes the stem
		// to the back
		// of the list.
		// Create a list of completions to return (initially empty)
		// While the queue is not empty and you don't have enough completions:
		// remove the first Node from the queue
		// If it is a word, add it to the completions list
		// Add all of its child nodes to the back of the queue
		// Return the list of completions

		return foundWords;
	}

	private boolean isFound(TrieNode node, String word) {
		return node.endsWord() && node.getText().equals(word);						
	}

	// For debugging
	public void printTree() {
		printNode(root);
	}

	/** Do a pre-order traversal from this node down */
	public void printNode(TrieNode curr) {
		if (curr == null)
			return;

		System.out.println(curr.getText());

		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			printNode(next);
		}
	}

}