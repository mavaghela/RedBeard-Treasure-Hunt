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
	}

	public void processCommand(String command) throws HeapFullException,
			HeapEmptyException {
		// The allowed commands are:
		// SONAR to drop the sonar in hope to detect treasure
		// GO direction to move the boat in some direction
		// For example, GO NW means move the boat one cell up left (if the cell is navigable; if not simply ignore the command)
		if(command.equals("SONAR")) {
			if (sonars == 0) {
				// TODO you lose the game
				state = "OVER";
				return;
			}

			sonars --;
			Node treasure = islands.getTreasure(range);
			if(treasure != null){
				// TODO you win
				state = "OVER";
				islands.findPath(islands.boat, treasure);
				path = islands.retracePath(islands.boat, treasure);
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
		state = "STARTED";
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

	public void buildGUI() throws IOException{
		JFrame frame = buildFrame();

		JPanel pane = new JPanel() {
			final BufferedImage water = ImageIO.read(new File("/home/mvaghela/csc301/assignment4-torontomaplelaughs/A4/src/main/java/org/csc301/water.gif"));

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				for (int i = 0; i < height; i++) {
					for (int j = 0; j < islands.width; j++) {
						if (i == islands.boat.gridY && j == islands.boat.gridX){
							// boat
						}

						else if (i == islands.treasure.gridY && j == islands.treasure.gridX){
							// treasure
						}

						else if (islands.map[j][i].inPath){
							// path
						}

						else if (islands.map[j][i].walkable) {
							System.out.println("But like i'm trying to draw");
							// water
							g.drawImage(water, j, i, null);

						}
						else { // land

						}
					}
				}

			}
		};
		frame.add(pane);
	}
}
