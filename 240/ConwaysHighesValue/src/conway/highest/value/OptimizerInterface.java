package conway.highest.value;

import CGOL.Conways;

/**
 * Created by insidious on 4/5/16.
 */
public interface OptimizerInterface {
    public void dumpSuperiorLife(boolean printable);
    public void run();
    public double getFitness();
    public double fitness(Conways life, int iterations);
}
