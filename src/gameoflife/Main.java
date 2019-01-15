package gameoflife;
import java.io.IOException;
import java.util.*;

public class Main {
        public static void main(String[] args) throws InterruptedException, IOException {
            Scanner sc = new Scanner(System.in);
            System.out.print("> ");
            int nb = sc.nextInt();
            int seed = sc.nextInt();
            int generation = sc.nextInt();
            Random generator = new Random(seed);
            char[][] world = new char[nb][nb];

            for (int i = 0; i < nb; i++){
                for (int k = 0; k < nb; k++){
                    world[i][k] = generator.nextBoolean()? 'O' : ' ';
                }
            }
            if (generation > 0)
                generation(world, generation);

        }



        private static void generation(char[][] world, int nb) throws InterruptedException, IOException {
            int ms = 1000;
            char[][] buff = new char[world.length][world.length];

            for (int i = 0; i < nb; i++){
                for (int y = 0; y < world.length; y++){
                    for (int x = 0; x < world[y].length; x++){
                        int isalive = isAlive(world, y, x);
                        buff[y][x] = (world[y][x] == 'O' && isalive == 1)? 'O' : ' ';
                        if (world[y][x] == ' ' && isalive == -1)
                            buff[y][x] = 'O';
                    }
                }

                for (int z = 0; z < world.length; z++){
                    for (int k = 0; k < world.length; k++){
                        world[z][k] = buff[z][k];
                    }
                }

                clearConsole();
                outWorld(world);
                Thread.sleep(ms);

            }
        }

        private static int isAlive(char[][] world, int i, int k){
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

        private static void outWorld(char[][] world){
            for (char[] c : world)
                System.out.println(c);
        }

    public static void clearConsole()
    {
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