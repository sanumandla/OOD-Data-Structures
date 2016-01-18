package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);		
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		validateElement(element);
		
		if (size == 0) {
			head = new LLNode<E>(element);
			tail = head;				
		} else {
			LLNode<E> tmp = tail;
			tail.next = new LLNode<E>(element);
			tail = tail.next;
			tail.prev = tmp;
		}
		
		size++;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		validateIndex(index);
		
		LLNode<E> tmp = head; 
		for(int i = 0; i < size; i++) {
			if (i == index) {
				return tmp.data;
			}
			
			tmp = tmp.next;
		}
		
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) 
	{
		validateElement(element);
		if (index != 0) {
			validateIndex(index);			
		}		
		
		LLNode<E> tmp = head;
		if (size == 0) {
			head = new LLNode<E>(element);
			tail = head;
		} else {
			for (int i = 0; i < size; i++) {
				if (i == index) {
					LLNode<E> newNode = new LLNode<E>(element);
					newNode.next = tmp;
					newNode.prev = tmp.prev;					
					
					if (index == 0) {
						head = newNode;
					} else {
						tmp.prev.next = newNode;
						tmp.prev = newNode;
					}
					
					break;
				}								
				tmp = tmp.next;
			}
		}		
		
		size++;
		
		LLNode<E> tmp1 = head;
		for (int i = 0; i < size; i ++) {
			System.out.print(" " + tmp1.data);
			tmp1 = tmp1.next;
		}
		System.out.println("----");
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		validateIndex(index);
		
		LLNode<E> tmp = head;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				size--;
				
				if (index == 0) {
					head = head.next;
				} else {
					tmp.next.prev = tmp.prev;
					tmp.prev.next = tmp.next;
				}
								
				return tmp.data;				
			}
			tmp = tmp.next;			
		}
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		validateIndex(index);
		validateElement(element);
		
		LLNode<E> tmp = head;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				E value = tmp.data;
				tmp.data = element;
				return value;
			}
			tmp = tmp.next;
		}
		
		return null;		
	}   
	
	public void validateIndex(int index) {
		if (index == -1 || index >= size) {
			throw new IndexOutOfBoundsException("Element not found at index " + index);
		}
	}
	
	public void validateElement(E element) {
		if (element == null) {
			throw new NullPointerException("Element cannot be null");
		}
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
