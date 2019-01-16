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
        initial();
    }

    private void initial(){
        add(st, BorderLayout.NORTH);
        add(PP);
        setSize(800, 870);
        setResizable(false);

    }

    public paintPole getPP() {
        return PP;
    }

    void setWorld(char[][] world) {

        PP.setWorld(world);
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

    paintPole(int x, int y){
        this.x = x;
        this.y = y; }


    @Override
    public void paintComponent(Graphics g){
        double part = x / world.length;
        for (int i = 0; i < world.length; i++) {
           for (int k = 0; k < world[i].length; k++) {
               if (world[i][k] == 'O'){
                   g.setColor(Color.BLACK);
               }
               else {
                   g.setColor(Color.WHITE);
               }
               g.fillRect((int)(k * part),(int)(i * part), (int)part, (int)part);

           }
        }
    }

    void setWorld(char[][] world) {
        this.world = world;
    }

    char[][] getWorld() {
        return world;
    }
}

class stat extends JComponent{
    private int generation;
    private int alive;
    private JLabel gener = new JLabel("Generation " + this.generation);
    private JLabel aliv = new JLabel("Alive: " + this.alive);

    stat(int generation, int alive){
        setLayout(new GridLayout (2, 1, 10, 2));

        this.generation = generation;
        this.alive = alive;



        add(gener);
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
}