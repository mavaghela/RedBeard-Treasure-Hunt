package org.csc301;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import static org.csc301.GameTest.buildFrame;

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
		this.islands = new Grid();
		this.sonars = DEFAULT_SONARS;
		this.range = DEFAULT_RANGE;
		this.state = "STARTED";
	}

	public TreasureHunt(int height, int width, int landPercent, int sonars,
			int range) {
		// The constructor that uses parameters
		this.height = height;
		this.width = width;
		this.landPercent = landPercent;
		this.sonars = sonars;
		this.range = range;
		this.islands = new Grid(width, height, landPercent);
		this.state = "STARTED";
	}

	public void processCommand(String command) throws HeapFullException,
			HeapEmptyException {
		// The allowed commands are:
		// SONAR to drop the sonar in hope to detect treasure
		// GO direction to move the boat in some direction
		// For example, GO NW means move the boat one cell up left (if the cell is navigable; if not simply ignore the command)

		if(state.equals("OVER")){
			return;
		}

		if(command.equals("SONAR")) {
			sonars --;


			Node treasure = islands.getTreasure(range);
			if(treasure != null){
				// TODO you win
				state = "OVER";
				islands.findPath(islands.boat, treasure);
				path = islands.retracePath(islands.boat, treasure);
			}

			if (sonars == 0) {
				// TODO you lose the game
				state = "OVER";
				return;
			}
		}
		else {
			String[] dir = command.split(" ");
			if(dir.length == 2 && dir[0].equals("GO")) {
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

	        while (sc.hasNextLine() && state.equals("STARTED")) {
				String line = sc.nextLine();
				processCommand(line);
	        }

	        state = "OVER";

	        sc.close();
    	}
		catch (Exception e) {
            e.printStackTrace();
        }
	}
}
