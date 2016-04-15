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
        System.out.println("superior life");
        algorithm.dumpSuperiorLife(false);
        for(int i = 0; i < 100; i++){
            algorithm.run();
            //print out everytime a better solution is discovered
            if(algorithm.getFitness() > startFitness){
                startFitness = algorithm.getFitness();
                System.out.println("Fitness is..." + startFitness + " on iteration " + i);
            }
        }

        System.out.println("Start Grid:");
        algorithm.dumpSuperiorLife(true);
        System.out.println();
        System.out.println("End Grid:");
        algorithm.dumpEndGrid(true);




       /* ConwaysOptimizer algorithm = new ConwaysOptimizer();
        algorithm.test(Conways.TEST,1000);*/



    }
}