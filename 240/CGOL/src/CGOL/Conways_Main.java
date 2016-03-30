package CGOL;

/**
 * Created by insidious on 3/23/16.
 */
public class Conways_Main {
    public static void main(String[] args) {
        Conways life = new Conways(1);

        int i = 1;
       while(i <= 3){
            System.out.println("Step: " + i);
            life.step();
            life.dumpWorld();
            i++;
        }


    }
}