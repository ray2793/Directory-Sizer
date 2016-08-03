package raymond_li;

/**
 * This class implements a singly linked sorted list that can store data of any type
 * that implements the Comparable interface (restricted generics).
 * @author Raymond Li
 *
 * @param <T>
 * 		any valid reference type, but used to store FileOnDisk objects for this program
 */
public class GenericSortedLinkedList <T extends Comparable<T>> implements Comparable<T> {

	//reference to the first node
	private GenericNode<T> head;
		
	//number of items in the list
	private int numOfElements;
		
		/**
		 * Creates an empty list object. 
		 */
	public GenericSortedLinkedList( ) {
		head = null;
		numOfElements = 0;
	}
	/**
	 * Inserts nodes in order
	 * @param item
	 * 		Item represents given reference type 
	 */
	@SuppressWarnings("unchecked")
	public void orderedInsert( T item ) {
		//add node only if item is not null
		if (item != null ) {
			//create new node
			GenericNode<T> newNode = new GenericNode <T> ( item , null );
			// special case for an empty list
			if (head == null )
				head	=	newNode ;
			//special case for inserting in front of the first node
			else if (0 < item.compareTo(head.getData())) {
				newNode.setNext( head );
				head = newNode ;
			}
		else {
			//create the current reference and advance it to the node that
			// is onebefore the position that we want to insert
			GenericNode<T> current = head;
			while ( current . getNext () != null &&
					0 > ((Comparable<T>) item).compareTo( current.getNext().getData() ) )
				current	=	current . getNext ();
			
			//special case for inserting at the end
			if	( current.getNext ()	==	null)	{
				current.setNext ( newNode ) ;
			}
			else { //insert the new node after current
				newNode.setNext(current.getNext()) ;
				current.setNext(newNode);
			}
		}
			numOfElements++;
		}
	}
	/**
	 * Returns size of Generic Sorted Linked List
	 * @return
	 * 		Returns size of Generic Sorted Linked List
	 */
	public int size() {
		return numOfElements;
	}
	
	
	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		GenericNode<T>  current = head;
		while (current != null ) {
			s.append( current.getData().toString());
			current = current.getNext();
		}
		return "" + s;
	}
	/**
	 * Prints out string containing the n largest files within the given directory
	 * @param n
	 * 		Represents number of files shown to user
	 * @return
	 * 		Returns the string
	 */
	public String toStringMaxNum(int n) {
		int counter = 0;
		StringBuffer s = new StringBuffer();
		GenericNode<T>  current = head;
		while (current != null & counter < n) {
			s.append( current.getData().toString());
			current = current.getNext();
			counter++;
		}
		return "" + s;
	}

	@Override
	public int compareTo(T o) {
		return 0;
	}
	
	public boolean contains(T item) {
		//if item is equal to null, there is nothing to do
		//if the list is empty, there is nothing to do.
		// otherwise look for the item
		if (item != null && head != null ) {

			//if there is only one node in the list, we need to handle it
			//separately
			if (numOfElements == 1 ) {
				if ( item.equals( head.getData() ) ) {
					return true;
				}
				else return false;
			}
			else { 
				//create a current reference and advance to the second node
				GenericNode<T>  current = head.getNext();
				//keep checking the data in nodes until either
				//a matching node is found or we reached the end of the list
				while ( current != null 
						&& !item.equals((current.getData() ) ) )
				    current = current.getNext();
				
				//if matching node found return true
				if (current != null )  {
					return true;
				}
			}
		}		
		return false;
	}

}
