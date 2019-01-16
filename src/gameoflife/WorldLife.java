package gameoflife;

import java.io.IOException;
import java.util.Random;

class WorldLife extends Thread{
    private int alive;
    private Pole world = new Pole();
    private Menu menu = new Menu();

    WorldLife(int i) throws InterruptedException {
        this.alive = i;
    }

    @Override
    public void run(){
        try {
            getParams();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getParams() throws IOException, InterruptedException {
        int nb = alive;
        while (nb == 0){
            nb = menu.getOP().getSizeField();
            Thread.sleep(100);
        }
        menu.getOP().setSizeField(0);
        int seed = (int) Math.abs(Math.random() * 100);
        Random generator = new Random(seed);
        char[][] world = new char[nb][nb];

        for (int i = 0; i < nb; i++){
            for (int k = 0; k < nb; k++){
                world[i][k] = generator.nextBoolean()? 'O' : ' ';
            }
        }
        this.world.setWorld(world);
        this.world.setVisible(true);
        generation(this.world.getWorld(), this.world, this.menu);

    }



    private  void generation(char[][] world, Pole allWorld, Menu menu) throws InterruptedException, IOException {
        int ms = 700;
        char[][] buff = new char[world.length][world.length];
        KeysEvent key = new KeysEvent();
        allWorld.addKeyListener(key);
        key.start();

        for (int i = 1; i > 0;){
            if (menu.isVisible())
                menu.setVisible(false);

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
            outWorld(allWorld);
            allWorld.getSt().setAlive(live);
            allWorld.getSt().setGeneration(i);
            if (key.getst().equals("pause"))
                if (!menu.isVisible()) menu.setVisible(true);
            while (key.getst().equals("pause") && !key.getst().equals("quit") && key.getState() != Thread.State.TERMINATED){

                if (menu.getOP().getSizeField() > 0){
                    key.setst("new");
                }
                if (!menu.isVisible()){
                    key.setst("run");
                }
                Thread.sleep(ms/2);
                if (menu.getOP().getSizeField() < 0){
                    key.setst("quit");
                    menu.setVisible(false);
                }
            }

            if (key.getst().equals("quit")) {
                key.join();
                i = -1;
                System.exit(1);
            }


            if (key.getst().equals("new")){
                key.setst("quit");
                key.join();
                this.alive = menu.getOP().getSizeField();
                this.getParams();
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

    private static void outWorld(Pole world) throws IOException {
        world.getPP().repaint();
    }
}
