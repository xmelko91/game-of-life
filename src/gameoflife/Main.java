package gameoflife;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        WorldLife newWorld = new WorldLife(0);
        newWorld.start();
    }
}






