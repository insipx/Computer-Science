/**
 * Sean Batzel 3/18/2016.
 */

public class GameOfLifeTests {
    public void continuousTest(boolean[][] world){
        GameOfLife game = new GameOfLife(world);
	    int x = 0;
        while(x < 100){
            game.theGameOfLife();
            game.printTheWorld();
	        x++;
        }
    }

    public void oneTest(boolean[][] world){
        GameOfLife game = new GameOfLife(world);
        game.printTheWorld();
        game.theGameOfLife();
        game.printTheWorld();
    }

    public void fiveTests(boolean[][] world){
        GameOfLife game = new GameOfLife(world);
        game.printTheWorld();
        for (int i = 0; i < 5; i++){
            game.theGameOfLife();
            game.printTheWorld();
        }
    }

    public void neighborhoodTest(boolean[][] world){
        GameOfLife game = new GameOfLife(world);
        int i = 0;
        while(i < 100){
            game.theGameOfLife();
            i++;
        }
    }
}
