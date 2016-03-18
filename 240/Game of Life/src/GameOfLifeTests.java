/**
 * Sean Batzel 3/18/2016.
 */
public class GameOfLifeTests {
    public void continuousTest(boolean[][] world){
        GameOfLife game = new GameOfLife(world);
        while(true){
            game.theGameOfLife();
            game.printTheWorld();
        }
    }
}
