package org.csc301;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
// import java.io.FileReader;
import java.io.*;

public class TreasureHunt {

	private final int DEFAULT_SONARS = 3; // default number of available sonars
	private final int DEFAULT_RANGE = 200; // default range of a sonar
	protected Grid islands; // the world where the action happens!
	protected int height, width, landPercent;
	protected int sonars, range; // user defined number of sonars and range
	protected String state; // state of the game (STARTED, OVER)
	protected ArrayList<Node> path; // the path to the treasure!

	public TreasureHunt() {
		// The default constructor
	}

	public TreasureHunt(int height, int width, int landPercent, int sonars,
			int range) {
		// The constructor that uses parameters
		this.height = height;
		this.width = width;
		this.landPercent = landPercent;
		this.sonars = sonars;
		this.range = range;
	}

	private void processCommand(String command) throws HeapFullException,
			HeapEmptyException {
		// The allowed commands are:
		// SONAR to drop the sonar in hope to detect treasure
		// GO direction to move the boat in some direction
		// For example, GO NW means move the boat one cell up left (if the cell is navigable; if not simply ignore the command)
		if(command.equals("SONAR")) {
			if (sonars == 0) {
				// you lose the game
				return;
			}
			sonars --;
			islands.getTreasure(range);
		}
		else {
			String[] dir = command.split(" ");
			if(dir.length == 2 && dir[0].equals("GO"))
			{
				String direction = dir[1];
				islands.move(direction);
			}
		}
	}

	public int pathLength() {
		if (path == null)
			return 0;
		else
			return path.size();
	}

	public String getMap() {
		return islands.drawMap();
	}

	public void play(String pathName) throws FileNotFoundException,
			HeapFullException, HeapEmptyException
	{
		// Read a batch of commands from a text file and process them.

		File file = new File(pathName);

    	try {
	        Scanner sc = new Scanner(file);

	        while (sc.hasNextLine()) {
				String line = sc.nextLine();
				processCommand(line);
	        }

	        sc.close();
    	}
		catch (Exception e) {
            e.printStackTrace();
        }
	}
}
