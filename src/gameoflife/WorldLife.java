package gameoflife;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class WorldLife extends Thread{
    private int alive;


    public void setAlive(int alive) {
        this.alive = alive;
    }

    @Override
    public void run(){
        try {
            getParams();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    void getParams() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of the universe: ");
        int nb = sc.nextInt();
        int seed = (int) Math.abs(Math.random() * 100);
        Random generator = new Random(seed);
        char[][] world = new char[nb][nb];

        for (int i = 0; i < nb; i++){
            for (int k = 0; k < nb; k++){
                world[i][k] = generator.nextBoolean()? 'O' : ' ';
            }
        }
        generation(world);

    }



    private  void generation(char[][] world) throws InterruptedException, IOException {
        int ms = 700;
        char[][] buff = new char[world.length][world.length];
        KeysEvent key = new KeysEvent();
        key.start();
        for (int i = 1; i > 0;){
            int live = 0;
            for (int y = 0; y < world.length; y++){
                for (int x = 0; x < world[y].length; x++){
                    int isalive = isAlive(world, y, x);
                    buff[y][x] = (world[y][x] == 'O' && isalive == 1)? 'O' : ' ';
                    if (world[y][x] == ' ' && isalive == -1)
                        buff[y][x] = 'O';
                    live += (buff[y][x] == 'O')? 1 : 0;
                }
            }

            for (int z = 0; z < world.length; z++){
                for (int k = 0; k < world.length; k++){
                    world[z][k] = buff[z][k];
                }
            }
            setAlive(live);
            outWorld(world, i, alive);
            while (key.getst().equals("pause") && !key.getst().equals("quit") && key.getState() != Thread.State.TERMINATED){
                Thread.sleep(ms);
            }

            if (key.getst().equals("quit")) {
                key.join();
                i = -1;
                System.exit(1);
            }


            if (key.getst().equals("new")){
                key.setst("quit");
                key.join();
                clearConsole();
                WorldLife newWorld = new WorldLife();
                newWorld.start();
                i = -1;
            }



            Thread.sleep(ms);
            i++;
        }
        key.setst("quit");
    }

    private  int isAlive(char[][] world, int i, int k){
        int neib = 0;
        int reborn = 0;

        for (int i1 = -1; i1 < 2; i1++){
            for (int k1 = -1; k1 < 2; k1++){
                int x = k + k1;
                int y = i + i1;
                if (i1 == 0 && k1 == 0) continue;
                if (x == -1) x = world.length - 1;
                if (y == -1) y = world.length - 1;
                if (x == world.length) x = 0;
                if (y == world.length) y = 0;
                if (world[y][x] == 'O') {
                    if (world[i][k] == 'O') {
                        neib++;
                    } else {
                        reborn++;
                    }
                }
            }
        }
        if (reborn == 3)
            return -1;
        return (neib == 3 || neib == 2) ? 1 : 0;
    }

    private static void outWorld(char[][] world, int generation, int alive) throws IOException {
        clearConsole();
        System.out.printf("Generation %d\n", generation);
        System.out.printf("Alive: %d\n", alive);
        for (char[] c : world)
            System.out.println(c);
        System.out.println("\nPress ENTER for pause\nInput n and press ENTER to start new\nInput q and press ENTER to quit");
    }

    private static void clearConsole() throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
            Runtime.getRuntime().exec("clear");
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
}
