package conway.highest.value;

/**
 * Created by insidious on 4/5/16.
 */
public interface OptimizerInterface {
    public void test(int iterations, int pattern);
    public void dumpSuperiorLife(boolean printable);
    public void run();
}
