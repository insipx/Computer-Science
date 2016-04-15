package conway.highest.value;
import conways.Conways;

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

        //the lifeForm seeds and the iterations we want to check
        //the lifeforms at for Conways
        //defaults  to EMPTY lifeforms and 1000 iterations if empty constructor
        ConwaysOptimizer algorithm = new ConwaysOptimizer(lifeForms, 1000);

        //get the best fitness out of the custom seeds
        double startFitness = algorithm.getFitness();
        System.out.println("The Best Fitness is.." + startFitness + " before iterations");

        for(int i = 0; i < 10000; i++){
            algorithm.run();
            //print out everytime a better solution is discovered
            if(algorithm.getFitness() > startFitness){
                startFitness = algorithm.getFitness();
                System.out.println("The Best Fitness is..." + startFitness + " on iteration " + i);
            }
        }

        System.out.println("Start Grid:");
        algorithm.dumpSuperiorLife(false);
        System.out.println();
        System.out.println("End Grid:");
        algorithm.dumpEndGrid(false);

        System.out.println("The Best End Fitness is: " + algorithm.getFitness());




       /* ConwaysOptimizer algorithm = new ConwaysOptimizer();
        algorithm.test(conways.TEST,1000);*/



    }
}