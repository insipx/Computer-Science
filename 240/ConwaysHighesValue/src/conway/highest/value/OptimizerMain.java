package conway.highest.value;
import CGOL.Conways;

/**
 * Created by insidious on 4/5/16.
 */
public class OptimizerMain{
    public static void main(String[] args) {
        //seeds
        Conways[] lifeForms = new Conways[3];
        lifeForms[0] = new Conways(Conways.INF_5X5);
        lifeForms[1] = new Conways(Conways.TEN_CELL_LINE);
        lifeForms[2] = new Conways(Conways.INF_2X12);


        ConwaysOptimizer algorithm = new ConwaysOptimizer(lifeForms, 1000);
        for(int i = 0; i < 1000; i++){
            algorithm.run();
        }

        algorithm.dumpSuperiorLife(false);
        System.out.println("The Fitness Is: " + algorithm.getFitness());

        /*ConwaysOptimizer algorithm = new ConwaysOptimizer(1000);
        algorithm.test(1000, Conways.TEST);*/

    }
}