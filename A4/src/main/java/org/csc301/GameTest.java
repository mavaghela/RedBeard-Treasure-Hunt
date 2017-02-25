package org.csc301;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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

		TreasureHunt game = new TreasureHunt();
		drawMap map = new drawMap(game);
        //game.play("game.txt");
//        System.out.println(game.getMap());
//        System.out.println(game.state);
//        System.out.println(game.pathLength());
	}
}
