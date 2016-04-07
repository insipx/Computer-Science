package conway.highest.value;
import org.apache.commons.lang3.time.StopWatch;
import CGOL.Conways;

/**
 * Created by insidious on 4/5/16.
 */
public class OptimizerMain{
    public static void main(String[] args) {
        Conways[] lifeForms = new Conways[3];

        lifeForms[0] = new Conways(Conways.EXPLODER);
        lifeForms[1] = new Conways(Conways.TEN_CELL_LINE);
        lifeForms[2] = new Conways(Conways.PUFFER_2);
        ConwaysOptimizer algorithm = new ConwaysOptimizer(lifeForms, 1000);
        StopWatch watch = new StopWatch();
        watch.start();
        for(int i = 0; i < 1000; i++) {
            algorithm.run();
        }
        algorithm.dumpSuperiorLife(true);
        watch.stop();
        System.out.println(" time is: " + watch.getTime() ) ;
        System.out.println("The Fitness Is: " + algorithm.getFitness());

    }
}