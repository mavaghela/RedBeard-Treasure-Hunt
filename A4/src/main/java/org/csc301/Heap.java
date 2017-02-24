package org.csc301;

public class Heap<T extends HeapItem> {

	// Note the T is a parameter representing a type that extends the HeapItem interface
	// This a new way to use inheritance!

	protected T[] items; // Array that is used to store heap items. items[0] is the highest priority element.
	protected int maxHeapSize; // The capacity of the heap
	protected int currentItemCount; // How many elements we have currently on the heap

	public Heap(int maxHeapSize) {
		this.maxHeapSize = maxHeapSize;
		items = (T[]) new HeapItem[maxHeapSize];
		currentItemCount = 0; // heap is empty!
	}

	public boolean isEmpty() {
		return currentItemCount == 0;
	}

	public boolean isFull() {
		return currentItemCount == maxHeapSize;
	}

	public void add(T item) throws HeapFullException
	{
	// Adds item T to its correct position on the heap
		if (isFull())
			throw new HeapFullException();
		else {
			item.setHeapIndex(currentItemCount);
			items[currentItemCount] = item;  // the element is added to the bottom
			sortUp(item); // Move the element up to its legitimate place. Check the diagram on the handout!
			currentItemCount++;
		}
	}

	public boolean contains(T item)
	// Returns true if item is on the heap
	// Otherwise returns false
	{
		return items[item.getHeapIndex()].equals(item);
	}

	public int count() {
		return currentItemCount;
	}

	public void updateItem(T item) {
		sortUp(item);
	}

	public T removeFirst() throws HeapEmptyException
	{
	// Removes and returns the element sitting on top of the heap
		if (isEmpty())
			throw new HeapEmptyException();
		else {
			T firstItem = items[0]; // element of top of the heap is stored in firstItem variable
			currentItemCount--;
			items[0] = items[currentItemCount]; //last element moves on top
			items[0].setHeapIndex(0);
			sortDown(items[0]); // move the element to its legitimate position. Please check the diagram on the handout.
			return firstItem;
		}
	}

	private void sortUp(T item) {
		// Implement this method according to the diagram on the handout.
		// Also: the indices of children and parent elements satisfy some relationships.
		// The formulas are on the handout.

		int index = item.getHeapIndex();
		T parent = getParent(item);

		while(parent != null && parent.compareTo(item) == 1){
			swap(item, parent);
			parent = getParent(item);
		}
	}

	private void sortDown(T item) {
		// Implement this method according to the diagram on the handout.
		// Also: the indices of children and parent elements satisfy some relationships.
		// The formulas are on the handout.

		T smallest = item;

		// check left child
		T leftChild = getChild(item, false);
		if(leftChild != null && smallest.compareTo(leftChild) == 1){
			smallest = leftChild;
		}

		// check right child
		T rightChild = getChild(item, true);
		if(rightChild != null && smallest.compareTo(rightChild) == 1){
			smallest = rightChild;
		}

		if(smallest != item){
			swap(item, smallest);
			sortDown(item);
		}
	}

	// You may implement additional helper methods if desired. Make sure to make them private!
	private void swap(T item1, T item2){
		int item1Index = item1.getHeapIndex();
		int item2Index = item2.getHeapIndex();
		item1.setHeapIndex(item2Index);
		item2.setHeapIndex(item1Index);
		items[item1Index] = item2;
		items[item2Index] = item1;
	}

	private T getParent(T child){
		int parentIndex = (child.getHeapIndex() - 1) / 2;
		if (parentIndex < 0) {
			return null;
		}
		return items[parentIndex];
	}

	private T getChild(T parent, boolean rightChild){
		int parentIndex = parent.getHeapIndex();
		int childIndex = (parentIndex * 2) + 1; // leftChild

		if(rightChild){
			childIndex++;
		}

		if(childIndex >= currentItemCount){
			return null;
		}
		return items[childIndex];
	}
}
