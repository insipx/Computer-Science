package CGOL;

/**
 * Created by insidious on 3/23/16.
 */
public class Conways_Main {
    public static void main(String[] args) {
        Conways life = new Conways(1);

        int i = 0;
       while(i < 2){
            System.out.println("Iteration: " + i);
            life.step();
            life.dumpGraph();
            i++;
        }


    }
}