package org.csc301;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
    protected TreasureHunt game;
    private boolean flag = false;

    public drawMap(TreasureHunt game){
        this.game = game;
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        islands = game.islands;
        createFrame();
        try {
            createPanel();
        }
        catch (Exception E){
            System.out.println("uhohhhhh");
        }

    }

    public drawMap(int width, int height, TreasureHunt game){

        this.game = game;
        this.width = width;
        this.height = height;
        islands = game.islands;
        createFrame();

    }

    private void createFrame(){
        frame = new JFrame("Grid Layout");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
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
        BufferedImage path = ImageIO.read(new File("/home/mvaghela/csc301/assignment4-torontomaplelaughs/A4/src/main/java/org/csc301/images/path.png"));

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

                    if(game.state.equals("OVER")) {
                        picLabel = new JLabel(new ImageIcon(treasure));
                    }
                    else{
                        picLabel = new JLabel(new ImageIcon(water));
                    }
                    c.gridx = j;
                    c.gridy = i;
                    panel.add(picLabel, c);
                }

                else if (islands.map[j][i].inPath){
                    picLabel = new JLabel(new ImageIcon(path));
                    c.gridx = j;
                    c.gridy = i;
                    panel.add(picLabel, c);
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
        c.gridy = height + 3;
        JButton north = new JButton("N");
        onClick(north);

        JButton south = new JButton("S");
        onClick(south);

        JButton east = new JButton("E");
        onClick(east);

        JButton west = new JButton("W");
        onClick(west);

        JButton ne = new JButton("NE");
        onClick(ne);

        JButton nw = new JButton("NW");
        onClick(nw);

        JButton se = new JButton("SE");
        onClick(se);

        JButton sw = new JButton("SW");
        onClick(sw);

        JButton sonar = new JButton("Drop Sonar");
        onClick(sonar);

        JButton playAgain = new JButton("Play Again");
        onClick(playAgain);

        //c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 20;
        c.insets = new Insets(10,0,0,0);
        c.weightx = 0.0;
        c.gridwidth = 5;
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
        c.gridx+= 4;
        c.gridwidth = 15;
        panel.add(sonar,c);

        if(game.state.equals("OVER")){
            c.gridx+= 4;
            c.gridwidth = 15;
            panel.add(playAgain,c);
        }

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void onClick(final JButton button) throws IOException {

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String name = button.getText();
                System.out.println(String.format("We clicked %s", name));
                switch (name){
                    case "Drop Sonar":
                        System.out.println("Dropped Sonar");
                            try {
                                game.processCommand("SONAR");
                            } catch (HeapFullException e) {
                                e.printStackTrace();
                            } catch (HeapEmptyException e) {
                                e.printStackTrace();
                            }
                        System.out.println(game.state);
                        break;

                    default:
                        try {
                            game.processCommand(String.format("GO %s", name));
                        } catch (HeapFullException e) {
                            e.printStackTrace();
                        } catch (HeapEmptyException e) {
                            e.printStackTrace();
                        }


                }
                try {
                    createPanel();
                    if(game.state.equals("OVER")){
                        System.out.println("All sonars are used");
                        if(game.pathLength() > 0){
                            if(!flag) {
                                flag = true;
                                JOptionPane.showMessageDialog(null, "YOU WIN!");
                            }
                        }
                        else{
                            if(!flag) {
                                flag = true;
                                JOptionPane.showMessageDialog(null, "YOU LOSE!");
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(name.equals("Play Again")){
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    try {
                        GameTest.main(null);
                    } catch (HeapFullException e) {
                        e.printStackTrace();
                    } catch (HeapEmptyException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

}
