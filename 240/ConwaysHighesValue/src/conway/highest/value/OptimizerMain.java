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

        //get the best fitness out of the custom seeds
        double startFitness = algorithm.getFitness();
        System.out.println("Fitness is.." + startFitness + " before iterations");
        for(int i = 0; i < 1000; i++){
            algorithm.run();
            //print out everytime a better solution is discovered
            if(algorithm.getFitness() > startFitness){
                startFitness = algorithm.getFitness();
                System.out.println("Fitness is..." + startFitness + " on iteration " + i);
            }
        }

        System.out.println("Start Grid:");
        algorithm.dumpSuperiorLife(false);
        System.out.println();
        System.out.println("End Grid:");
        algorithm.dumpEndGrid();

        /*ConwaysOptimizer algorithm = new ConwaysOptimizer(1000);
        algorithm.test(1000, Conways.TEST);*/

    }
}