package org.csc301;

import java.util.*;

public class Grid {

	private final int DEFAULT_WIDTH = 60; // default width of the world map - gridX runs from 0 to 59
	private final int DEFAULT_HEIGHT = 15; // default height of the map - gridY runs from 0 to 14
	private final int DEFAULT_PERCENT = 20; // this is the percentageof the map occupied by islands
	protected int width, height; // user defined width and height, if one is not using defaults
	protected int percent; // user defined percentage of islands on the map
	protected Node treasure; // points to the map node where the Redbeard treasure is sunken
	protected Node boat; // points to the current location of our boat on the map

	protected Node[][] map; // the map

	public Grid() {
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		percent = DEFAULT_PERCENT;
		map = new Node[height][width];
		buildMap();
	}

	public Grid(int width, int height, int percent) {
		this.width = width;
		this.height = height;
		if (percent <= 0 || percent >= 100)
			this.percent = DEFAULT_PERCENT;
		else
			this.percent = percent;
		map = new Node[height][width];
		buildMap();
	}

	/**  Your implementation goes here
	 For each map position (i,j) you need to generate a Node with can be navigable
	 or it may belong to an island
	 You may use ideas from Lab3 here.
	 Don't forget to generate the location of the boat and of the treasure;
	 they must be on navigable waters, not on the land! **/

	private void buildMap() {
		int coord[];
		double squares = width * height;
		int numIslands = (int)(squares * ((double)percent/100.0));

		//creating islands
		for(int k = 0; k < numIslands; k++){
			coord = getRandom(false);
			map[coord[0]][coord[1]] = new Node(false, coord[0], coord[1]);
		}

		//creating water
		for(int l = 0; l < height; l++){
			for(int m = 0; m < width; m++){
				if(null == map[l][m]){
					map[l][m] = new Node(true, l, m);
				}
			}
		}

		coord = getRandom(true);
		boat = new Node(false, coord[0], coord[1]);
		System.out.println(String.format("We just created a boat at %d %d",coord[0], coord[1]));

		coord = getRandom(true);
		treasure = new Node(false, coord[0], coord[1]);
		System.out.println(String.format("We just created an treasure at %d %d",coord[0], coord[1]));

	}

	/** Generates random index values in map that haven't
	 * been used before **/
	private int[] getRandom(boolean object){
		Random r = new Random();
		int[] coord = new int[2];
		int x = r.nextInt(height -1);
		int y = r.nextInt(width -1);

		// If we're getting coords for boat or treasure it has
		// to be navigable ie. the water
		if(object){
			while(!(map[x][y].walkable)){
				x = r.nextInt(height - 1);
				y = r.nextInt(width - 1);
			}
		}
		else {
			while (null != map[x][y]) {
				x = r.nextInt(height - 1);
				y = r.nextInt(width - 1);
			}
		}
		coord[0] = x;
		coord[1] = y;
		return coord;
	}

	public String drawMap() {
		// provided for your convenience
		String result = "";
		String hline = "       ";
		String extraSpace;
		for (int i = 0; i < width / 10; i++)
			hline += "         " + (i + 1);
		result += hline + "\n";
		hline = "       ";
		for (int i = 0; i < width; i++)
			hline += (i % 10);
		result += hline + "\n";
		for (int i = 0; i < height; i++) {
			if (i < 10)
				extraSpace = "      ";
			else
				extraSpace = "     ";
			hline = extraSpace + i;
			for (int j = 0; j < width; j++) {
				if (i == boat.gridY && j == boat.gridX)
					hline += "B";
				else if (i == treasure.gridY && j == treasure.gridX)
					hline += "T";
				else if (map[i][j].inPath)
					hline += "*";
				else if (map[i][j].walkable)
					hline += ".";
				else
					hline += "+";
			}
			result += hline + i + "\n";
		}
		hline = "       ";
		for (int i = 0; i < width; i++)
			hline += (i % 10);
		result += hline + "\n";
		return result;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPercent() {
		return percent;
	}

	public Node getBoat() {
		return boat;
	}

	private ArrayList<Node> getNeighbours(Node node) {
		// each node has at most 8 neighbours
		// Lab3 may be useful here as well
		return null;
	}

	private int getDistance(Node nodeA, Node nodeB) {
		// helper method. Provided for your convenience.
		int dstX = Math.abs(nodeA.gridX - nodeB.gridX);
		int dstY = Math.abs(nodeA.gridY - nodeB.gridY);
		if (dstX > dstY)
			return 14 * dstY + 10 * (dstX - dstY);
		return 14 * dstX + 10 * (dstY - dstX);
	}

	public void findPath(Node startNode, Node targetNode)
			throws HeapFullException, HeapEmptyException {
		Heap<Node> openSet = new Heap<>(width * height); // this where we make use of our heaps
		// The rest of your implementation goes here.
		// This method implements A-star path search algorithm.
		// The pseudocode is provided in the appropriate web links.
		// Make sure to use the helper method getNeighbours
	}

	public ArrayList<Node> retracePath(Node startNode, Node endNode) {
		Node currentNode = endNode;
	    ArrayList<Node> path = new ArrayList<Node>();
		while (currentNode != startNode && currentNode != null) {
			currentNode.inPath = true;
			path.add(currentNode);
			currentNode = currentNode.parent;
		}
		return path;
	}

	public void move(String direction) {
		// Direction may be: N,S,W,E,NE,NW,SE,SW
		// move the boat 1 cell in the required direction
		switch (direction)
		{
			case "N":
				break;
			case "S":
				break;
			case "W":
				break;
			case "E":
				break;
			case "NE":
				break;
			case "NW":
				break;
			case "SE":
				break;
			case "SW":
				break;
	         default:
			 	// illegal direction
				break;
     	}
	}

	public Node getTreasure(int range) {
		// range is the range of the sonar
		// if the distance of the treasure from the boat is less or equal that the sonar range,
		// return the treasure node. Otherwise return null.
		return null;
	}

}
