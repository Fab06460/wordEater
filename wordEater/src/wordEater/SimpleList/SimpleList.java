package wordEater.SimpleList;


/**
 * A very basic list class.
 * @author fabricebelloncle
 *
 * @param <E> type of the list elements. The type must implement ISimpleListElement interface.
 */
public class SimpleList<E extends ISimpleListElement<E>> {

	/**
	 * Represents a node in the list
	 * @author fabricebelloncle
	 *
	 * @param <T> type of the data contained in the node
	 */
	private class Node<T> {
		private T element;
		private Node<T> next;
		
		public Node(T element) {
			this.element = element;
			this.next = null;
		}
		
		public T getElement() {
			return element;
		}
		
		public Node<T> getNext() {
			return next;
		}
		public void setNext(Node<T> next) {
			this.next = next;
		}
	}
	
	// root of the list
	private Node<E> root = null;
	
	// current size of the list
	private int size = 0;
	
	/**
	 * Initializes an empty list
	 */
	public SimpleList() {
	}
	
	/**
	 * Initializes a list with a first element
	 * @param element
	 */
	public SimpleList(E element) {
		root = new Node<E>(element);
	}
	
	/**
	 * Clears the list
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Inserts at index or at end of list if index is greater than list size.
	 * @param index position in the list where the element should be inserted. 
	 * Must be a valid index (between 1 and list size).
	 * If index is out of bounds, an ArrayIndexOutOfBoundsException is thrown.
	 * @param element element to insert in the list
	 */
	public void insertAtIndex(int index, E element) {
		if ( (index < 1) || (index > size + 1)) {
			throw new ArrayIndexOutOfBoundsException();
		}
			
		Node<E> node = new Node<E>(element);
		if ( size == 0) {
			// The list is empty, node is the root
			root = node;
		}
		else {
			if ( index == 1 ) {
				// Insert node at head of list
				node.setNext(root);
				root = node;
			}
			else {
				// Chain element in the list
				Node<E> current = root;
				for (int i=1; (i<index-1) && (current.getNext()!=null); i++) {
					current = current.getNext();
				}
				
				node.setNext(current.getNext());
				current.setNext(node);		
			}
		}
		size++;
	}
	
	/**
	 * Gets the list element specified by the index
	 * @param index position of the element. Must be a valid index (between 1 and list size).
	 * If index is out of bounds, an ArrayIndexOutOfBoundsException is thrown.
	 * @return element with the given index
	 */
	public E getAtIndex(int index) {
		if ( (index < 1) || (index > size) ) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		Node<E> current = root;
		for (int i=1; (i<index) && (current.getNext() != null); i++) {
			current = current.getNext();
		}
		return current.getElement();
	}
	
	/**
	 * Gets the index of the specified element. The compareTo() method from the list parameterized type is used
	 * to compare list elements.
	 * @param element the element to search
	 * @return index of element in the list or -1 if not found
	 */
	public int indexOf(E element) {
		int i = 1;
		Node<E> current = root;
		while ((i<=size) && (current.element.compareTo(element) != 0)) {
			current = current.getNext();
			i++;
		}
		return (i<=size?i:-1);
	}
	
	/**
	 * Tests if list contains an element.  The compareTo() method from the list parameterized type is used
	 * to compare list elements.
	 * @param element the element to search
	 * @return true if element has been found, otherwise false
	 */
	public boolean contains(E element) {
		int index = indexOf(element);
		return (index != -1);
	}
	
	/**
	 * Returns the current list size
	 * @return list size
	 */
	public int length() {
		return size;
	}
	
	@Override
	public String toString() {
		String str = "";
		Node<E> node = root;
		while ( node.getNext() != null ) {
			str += node.getElement().toString() + ", ";
			node = node.getNext();
		}
		return str;
	}
}
