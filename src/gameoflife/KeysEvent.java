package gameoflife;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

class KeysEvent extends Thread implements KeyListener {
    private String state = "run";


    @Override
    public void run(){
        Scanner sc = new Scanner(System.in);
        while (!state.equals("quit")){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public String getst() {
        return state;
    }

    public void setst(String s){
        this.state = s;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        setst(state.equals("pause")? "run" : "pause");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
