package org.csc301;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by mvaghela on 2/24/17.
 */
public class drawMap {

    private JFrame frame;
    private JPanel panel;
    private JButton button;
    private  JLabel label;
    private final int DEFAULT_WIDTH = 60;
    private final int DEFAULT_HEIGHT = 15;
    protected int width, height;
    protected Grid islands;

    public drawMap(Grid world){
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        islands = world;
        createFrame();
        try {
            createPanel();
        }
        catch (Exception E){
            System.out.println("uhohhhhh");
        }

    }

    public drawMap(int width, int height, Grid world){

        this.width = width;
        this.height = height;
        islands = world;
        createFrame();

    }

    private void createFrame(){
        frame = new JFrame("Grid Layout");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
      //  frame.setResizable(false);
        return;
    }

    private void createPanel() throws IOException{
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        panel.setLayout(new GridLayout(height, width, 0 , 0));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        BufferedImage water = ImageIO.read(new File("/home/mvaghela/csc301/assignment4-torontomaplelaughs/A4/src/main/java/org/csc301/water.gif"));
        BufferedImage land = ImageIO.read(new File("/home/mvaghela/csc301/assignment4-torontomaplelaughs/A4/src/main/java/org/csc301/land.gif"));
        BufferedImage boat = ImageIO.read(new File("/home/mvaghela/csc301/assignment4-torontomaplelaughs/A4/src/main/java/org/csc301/boat.png"));
        BufferedImage treasure = ImageIO.read(new File("/home/mvaghela/csc301/assignment4-torontomaplelaughs/A4/src/main/java/org/csc301/tres.gif"));
        JLabel picLabel;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == islands.boat.gridY && j == islands.boat.gridX){
                    // boat
                    picLabel = new JLabel(new ImageIcon(boat));
                    panel.add(picLabel);
                }

                else if (i == islands.treasure.gridY && j == islands.treasure.gridX){
                    // treasure
                    picLabel = new JLabel(new ImageIcon(treasure));
                    panel.add(picLabel);
                }

                else if (islands.map[j][i].inPath){

                }

                else if (islands.map[j][i].walkable) {
                    // water
                    picLabel = new JLabel(new ImageIcon(water));
                    panel.add(picLabel);

                }
                else { // land
                    picLabel = new JLabel(new ImageIcon(land));
                    panel.add(picLabel);


                }
            }
        }
        frame.add(panel);
    }

}
