package gameoflife;

import javax.swing.*;
import java.awt.*;

public class Pole extends JFrame {
    private int generation = 0;
    private int alive = 0;
    private stat st = new stat(generation, alive);
    private paintPole PP = new paintPole(800, 800);

    Pole() {
        super("WorldLife");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initial();
    }

    private void initial(){
        add(st, BorderLayout.NORTH);
        add(PP);
        setSize(800, 900);
        setResizable(false);

    }

    public paintPole getPP() {
        return PP;
    }

    void setWorld(char[][] world) {

        PP.setWorld(world);
    }

    void setColor(){
        PP.setColor(new Color(st.getColor()[0],st.getColor()[1],st.getColor()[2]));
    }


    public char[][] getWorld() {
        return PP.getWorld();
    }

    public stat getSt() {
        return st;
    }
}

class paintPole extends JComponent{
    private int x;
    private int y;
    private char[][] world;
    Color color = Color.BLACK;

    paintPole(int x, int y){
        this.x = x;
        this.y = y; }


    @Override
    public void paintComponent(Graphics g){
        double part = x / world.length;
        for (int i = 0; i < world.length; i++) {
           for (int k = 0; k < world[i].length; k++) {
               if (world[i][k] == 'O'){
                   g.setColor(color);
               }
               else {
                   g.setColor(Color.WHITE);
               }
               g.fillRect((int)(i * part),(int)(k * part), (int)part, (int)part);

           }
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    void setWorld(char[][] world) {
        this.world = world;
    }

    char[][] getWorld() {
        return world;
    }
}

class colorGrid extends JComponent{
    private JSlider red = new JSlider(1,255,1);
    private JSlider green = new JSlider(1,255,1);
    private JSlider blue = new JSlider(1,255,1);

    colorGrid(){
        setLayout(new GridLayout(1, 3, 1, 1));
        add(red);
        add(green);
        add(blue);
    }

    public int[] getRed() {
        return new int[]{red.getValue(), green.getValue(), blue.getValue()};
    }

}


class stat extends JComponent{
    private int generation;
    private int alive;
    private JLabel gener = new JLabel("Generation " + this.generation);
    private JLabel aliv = new JLabel("Alive: " + this.alive);
    private JSlider slider = new JSlider(50,1000,50);
    private colorGrid cGr = new colorGrid();


    stat(int generation, int alive){
        setLayout(new GridLayout (2, 2, 10, 2));

        this.generation = generation;
        this.alive = alive;


        add(slider);
        add(gener);
        add(cGr);
        add(aliv);


    }

    void setGeneration(int generation) {
        this.generation = generation;
        gener.setText("Generation " + this.generation);

    }

    void setAlive(int alive) {
        this.alive = alive;
        aliv.setText("Alive: " + this.alive);
    }

    public JSlider getSlider() {
        return slider;
    }
    public int[] getColor(){
        return cGr.getRed();
    }
}