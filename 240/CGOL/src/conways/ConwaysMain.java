package conways;

/**
 * Created by insidious on 3/23/16.
 */
public class ConwaysMain {
    public static void main(String[] args) {
        conways.Conways life = new conways.Conways(Conways.GLIDER);
        life.dumpWorld(false,false);
        for(int i = 0; i < 10; i++){
            life.evolve();
        }
        life.dumpWorld(false, false);
    }
}