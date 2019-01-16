package gameoflife;

import java.util.Scanner;

class KeysEvent extends Thread {
    private String state = "run";


    @Override
    public void run(){
        Scanner sc = new Scanner(System.in);
        while (!state.equals("quit")){
            String c = sc.nextLine();
            if (c.equals("q")){
                setst("quit");
            }
            else if (c.equals("")) {
                setst(state.equals("pause")? "run" : "pause");
            }
            else if (c.equals("n")){
                setst("new");
                while (getst().equals("new")) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public String getst() {
        return state;
    }

    public void setst(String s){
        this.state = s;
    }
}
