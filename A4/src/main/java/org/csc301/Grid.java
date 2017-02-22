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

	private boolean DEBUG = false;

	public Grid() {
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		percent = DEFAULT_PERCENT;
		map = new Node[width][height];
		buildMap();
	}

	public Grid(int width, int height, int percent) {
		DEBUG = false;
		this.width = width;
		this.height = height;
		 if (percent <= 0 || percent >= 100)
		 	this.percent = DEFAULT_PERCENT;
		 else
			this.percent = percent;
		map = new Node[width][height];
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
		for(int l = 0; l < width; l++){
			for(int m = 0; m < height; m++){
				if(null == map[l][m]){
					map[l][m] = new Node(true, l, m);
				}
			}
		}

		if(DEBUG)
		{
			boat = new Node(false, 0, 0);
			map[0][0] = boat;

			treasure = new Node(true, width - 1, height - 1);
			map[width-1][height-1] = treasure;
		}
		else
		{
			coord = getRandom(true);
			boat = new Node(false, coord[0], coord[1]);
			map[coord[0]][coord[1]] = boat;


			coord = getRandom(true);
			treasure = new Node(true, coord[0], coord[1]);
			map[coord[0]][coord[1]] = treasure;

		}
		System.out.println(String.format("We just created a boat at %d %d",boat.gridX, boat.gridY));
		System.out.println(String.format("We just created a treasure at %d %d",treasure.gridX, treasure.gridY));
	}

	/** Generates random index values in map that haven't
	 * been used before **/
	private int[] getRandom(boolean object){
		Random r = new Random();
		int[] coord = new int[2];
		int x = r.nextInt(width);
		int y = r.nextInt(height);

		// If we're getting coords for boat or treasure it has
		// to be navigable ie. the water
		if(object){
			while(!(map[x][y].walkable)){
				x = r.nextInt(width-1);
				y = r.nextInt(height-1);
			}
		}
		else {
			while (null != map[x][y]) {
				x = r.nextInt(width-1);
				y = r.nextInt(height-1);
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
				else if (map[j][i].inPath)
					hline += "*";
				else if (map[j][i].walkable)
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

		ArrayList<Node> neighbours = new ArrayList<Node>();
		int startX  = Math.max(node.gridX - 1, 0);
		int endX = Math.min(node.gridX + 1, width - 1);
		int startY  = Math.max(node.gridY - 1, 0);
		int endY = Math.min(node.gridY + 1, height - 1);

		for (int curX = startX; curX <= endX; curX++) {
			for (int curY = startY; curY <= endY; curY++) {
				if(node.gridX == curX && node.gridY == curY){
					continue;
				}
				Node n = map[curX][curY];
				if(n != null){
					neighbours.add(n);
				}
			}
		}

		return neighbours;
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
			throws HeapFullException, HeapEmptyException
	{
		if(startNode == targetNode)
			return;

		Heap<Node> openSet = new Heap<>(width * height); // this where we make use of our heaps
		ArrayList<Node> closedSet = new ArrayList<Node>();

		// Add the starting square (or node) to the open list.
		openSet.add(startNode);

		while(!openSet.isEmpty())
		{
			// Look for the lowest F cost square on the open list (current Square)
			Node currentNode = openSet.removeFirst();

			if(currentNode == null || currentNode == targetNode){
				return;
			}

			// Switch it to the closed list.
			closedSet.add(currentNode);

			// For each of the 8 squares adjacent to this current square …
			ArrayList<Node> neighbours = getNeighbours(currentNode);

			for (Node n : neighbours) {
				// If not walkable or on the closed list, ignore it
				if(n == null || !n.walkable || closedSet.contains(n)){
					continue;
				}

				if(!openSet.contains(n)){
					/*
					If it isn’t on the open list, add it to the open list.
					Make the current square the parent of this square.
					Record the F, G, and H costs of the square.
					*/
					n.parent = currentNode;
					int gCost = getDistance(n, currentNode);
					int hCost = getDistance(n, targetNode);
					n.setGCost(gCost);
					n.setHCost(hCost);
					openSet.add(n);
				}

				/*
				If it is on the open list already,
				check to see if this path to that square is better,
				using G cost as the measure.
				A lower G cost means that this is a better path.
				If so, change the parent of the square to the current square,
				and recalculate the G and F scores of the square.
				*/
				else {
					int currentGCost = n.getGCost();
					int newGCost = getDistance(n, currentNode);
					if (currentGCost > newGCost) {
						n.parent = currentNode;
						n.setGCost(newGCost);
                    }
				}
			}
		}
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
		int curX = boat.gridX;
		int curY = boat.gridY;

		switch (direction)
		{
			case "N":
				if(isWalkable(curX, curY + 1)){
					moveBoat(curX, curY + 1);
				}
				break;
			case "S":
				if(isWalkable(curX, curY - 1)){
					moveBoat(curX, curY - 1);
				}
				break;
			case "W":
				if(isWalkable(curX - 1, curY)){
					moveBoat(curX - 1, curY);
				}
				break;
			case "E":
				if(isWalkable(curX + 1, curY)){
					moveBoat(curX + 1, curY);
				}
				break;
			case "NE":
				if(isWalkable(curX + 1, curY - 1)){
					moveBoat(curX + 1, curY - 1);
				}
				break;
			case "NW":
				if(isWalkable(curX - 1, curY - 1)){
					moveBoat(curX - 1, curY - 1);
				}
				break;
			case "SE":
				if(isWalkable(curX + 1, curY + 1)){
					moveBoat(curX + 1, curY + 1);
				}
				break;
			case "SW":
				if(isWalkable(curX - 1, curY + 1)){
					moveBoat(curX - 1, curY + 1);
				}
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

		int distance = getDistance(boat, treasure);
		if(distance <= range){
			return treasure;
		}

		return null;
	}

	private boolean isWalkable(int x, int y){
		if(x < 0 || x >= width || y < 0 || y >= height){
			return false;
		}
		return map[x][y].walkable;
	}

	private void moveBoat(int newX, int newY){
		// set old location to water
		map[boat.gridX][boat.gridY] = new Node(true, boat.gridX, boat.gridY);

		boat.gridX = newX;
		boat.gridY = newY;
		map[newX][newY] = boat;
	}
}
