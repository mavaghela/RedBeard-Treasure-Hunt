package org.csc301;

import java.io.FileNotFoundException;
import java.io.IOException;

// This class is not part of your evaluation. You may use it for testing if you want.
public class GameTest {

	/**
	 * @param args
	 * @throws HeapEmptyException
	 * @throws HeapFullException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, HeapFullException, HeapEmptyException, IOException {
		// TODO Auto-generated method stub

		TreasureHunt game = new TreasureHunt();
		drawMap thing = new drawMap(game);
//		game.buildGUI();
		// TreasureHunt game = new TreasureHunt(4, 4, 0, 3, 100);
        //game.play("/home/mvaghela/csc301/assignment4-torontomaplelaughs/A4/game.txt");

        System.out.println(game.getMap());
        System.out.println(game.state);
        System.out.println(game.pathLength());
	}


}
