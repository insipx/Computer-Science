/**
 * Sean Batzel 3/18/2016.
 */
public class GameOfLife {
    private boolean[][] one; //The initial world.
    private boolean[][] two; //A whole new world.

    public GameOfLife(boolean[][] world) { //Let's get this show on the road.
        this.one = world; //Make these guys
        this.two = world; //The same thing for right now.
    }

    public void theGameOfLife() {
        for (int i = 1; i < 19; i++) {
            for (int j = 1; j < 19; j++) {
                this.two[i][j] = life(i, j);
            }
        }
        this.one = this.two;
    }

    private boolean life(int i, int j) { //And then, life happens.
        boolean live;
        int neighbors = this.neighborHoodOf(i, j);
        if (this.one[i][j]) { //If we're currently alive.
            if (neighbors <= 3 && neighbors >= 2) {
                live = true; //We have friends, we have food, what more could we want?
            } else {
                live = false; //You make me so lonely I could die.
            }
        } else {
            if (neighbors == 3) {
                live = true; //A healthy new baby cell has been born!
            } else {
                live = false; //Nope still dead.
            }
        }
        return live;
    }

    private int neighborHoodOf(int i, int j) {
        int neighbors = 0;
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (this.one[i + x][j + y]) {
                    if (x == 0 && y == 0) {
                    } else {
                        neighbors++;
                    }
                }
            }
        }
        //System.out.println(neighbors);
        return neighbors;
    }

    public void printTheWorld() {
        for (int i = 1; i < 19; i++) {
            for (int j = 1; j < 19; j++) {
                if (two[i][j]) {
                    System.out.print("X ");
                } else {
                    System.out.print("o ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
