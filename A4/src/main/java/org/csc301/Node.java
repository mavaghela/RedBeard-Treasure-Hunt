package org.csc301;

public class Node implements HeapItem {

	protected boolean walkable; // true if this node is available for passage, false if it belongs to an obstacle (i.e. an island)
	protected int gridX, gridY; // gridX runs left to right starting from 0, gridY runs top to bottom starting from 0
	protected int gCost, hCost; // gCost & hCost as explained in the A-star algorithm (check the given web links)
	protected int heapIndex;    // this is used to implement the methods imposed by HeapItem interface
	protected Node parent;      // points to previous node in the calculated path
	protected boolean inPath;   // true if the node belongs to the calculated path, false otherwise

	public Node(boolean walkable, int gridX, int gridY) {
		this.walkable = walkable;
		this.gridX = gridX;
		this.gridY = gridY;
		this.inPath = false;
		this.parent = null;
		this.gCost = 0;
		this.hCost = 0;
	}

	public int getFCost() {
		return gCost + hCost;
	}

	public int getGCost() {
		return gCost;
	}

	public int getHCost() {
		return hCost;
	}

	public void setGCost(int gCost){
		this.gCost = gCost;
	}

	public void setHCost(int hCost){
		this.hCost = hCost;
	}

	@Override
	public int compareTo(HeapItem other) {
		int thisCost = this.getFCost();
		int otherCost = ((Node)other).getFCost();

		if(thisCost == otherCost)
			return 0;
		return thisCost > otherCost ? 1 : -1;
	}

	@Override
	public void setHeapIndex(int index) {
		heapIndex = index;
	}

	@Override
	public int getHeapIndex() {
		return heapIndex;
	}

	@Override
	public boolean equals(Object other) {
		// Your implementation goes here. Two nodes are equal if they occupy same position in the map.

		Node nodeOther = (Node) other;
		if(nodeOther != null)
			return (this.gridX == nodeOther.gridX && this.gridY == nodeOther.gridY);

		return false;
	}
}
