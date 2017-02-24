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
        panel.setLayout(new GridBagLayout());
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        GridBagConstraints c = new GridBagConstraints();

        BufferedImage water = ImageIO.read(new File("/home/mvaghela/csc301/assignment4-torontomaplelaughs/A4/src/main/java/org/csc301/images/water.png"));
        BufferedImage land = ImageIO.read(new File("/home/mvaghela/csc301/assignment4-torontomaplelaughs/A4/src/main/java/org/csc301/images/island.png"));
        BufferedImage boat = ImageIO.read(new File("/home/mvaghela/csc301/assignment4-torontomaplelaughs/A4/src/main/java/org/csc301/images/boat.png"));
        BufferedImage treasure = ImageIO.read(new File("/home/mvaghela/csc301/assignment4-torontomaplelaughs/A4/src/main/java/org/csc301/images/treasure.png"));
        JLabel picLabel;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == islands.boat.gridY && j == islands.boat.gridX){
                    // boat
                    picLabel = new JLabel(new ImageIcon(boat));
                    c.gridx = j;
                    c.gridy = i;
                    panel.add(picLabel, c);
                }

                else if (i == islands.treasure.gridY && j == islands.treasure.gridX){
                    // treasure
                    picLabel = new JLabel(new ImageIcon(treasure));
                    c.gridx = j;
                    c.gridy = i;
                    panel.add(picLabel, c);
                }

                else if (islands.map[j][i].inPath){

                }

                else if (islands.map[j][i].walkable) {
                    // water
                    picLabel = new JLabel(new ImageIcon(water));
                    c.gridx = j;
                    c.gridy = i;
                    panel.add(picLabel, c);

                }
                else { // land
                    picLabel = new JLabel(new ImageIcon(land));
                    c.gridx = j;
                    c.gridy = i;
                    panel.add(picLabel, c);


                }
            }
        }
        frame.add(panel);
        c.gridx = 0;
        JButton north = new JButton("N");
        JButton south = new JButton("S");
        JButton east = new JButton("E");
        JButton west = new JButton("W");
        JButton ne = new JButton("NE");
        JButton nw = new JButton("NW");
        JButton se = new JButton("SE");
        JButton sw = new JButton("SW");

        //c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 2;
        panel.add(north, c);
        c.gridx += 4;
        panel.add(south, c);
        c.gridx += 4;
        panel.add(east,c);
        c.gridx+= 4;
        panel.add(west,c);
        c.gridx+= 4;
        panel.add(ne,c);
        c.gridx+= 4;
        panel.add(nw,c);
        c.gridx+= 4;
        panel.add(se,c);
        c.gridx+= 4;
        panel.add(sw,c);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

}
