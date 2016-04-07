package conway.highest.value;

import org.apache.commons.lang3.time.StopWatch;
import CGOL.Conways;

import java.util.Random;

import static CGOL.Conways.*;

/**
 * Created by insidious on 4/5/16.
 * <p>
 * Optimizer uses the Gene Algorithm to compute best fitness(EndLiveCells/2*StartLiveCells)
 * of Conways Game of life
 */

public class ConwaysOptimizer implements OptimizerInterface {

    //constructors
    final static boolean ALIVE = true;
    final static boolean DEAD = false;
    int iterations;
    int bestFitnessIdx = 0;
    int bestFitness = 0;
    private Conways[] lifeForms;

    public ConwaysOptimizer() {
        lifeForms = new Conways[3];
        lifeForms[0] = new Conways(EXPLODER);
        lifeForms[1] = new Conways(TEN_CELL_LINE);
        lifeForms[2] = new Conways(GLIDER);
        iterations = 1000;
    }
    //default seeds
    public ConwaysOptimizer(int iterations) {
        lifeForms = new Conways[3];
        lifeForms[0] = new Conways(EXPLODER);
        lifeForms[1] = new Conways(TEN_CELL_LINE);
        lifeForms[2] = new Conways(GLIDER);
        this.iterations = iterations;
    }

    //custom seeded constructor
    public ConwaysOptimizer(Conways[] lifeForms) {
        this.lifeForms = lifeForms;
        iterations = 1000;
    }
    public ConwaysOptimizer(Conways[] lifeForms, int iterations) {
        this.lifeForms = lifeForms;
        this.iterations = iterations;
    }


    public void run(){
        for(int i = 0; i < lifeForms.length; i ++){
            lifeForms[i] = mutate(lifeForms[i]);
        }
        nextGeneration();
    }

    private void nextGeneration() {
        int worstFitness = 999999999;
        int worstFitnessIdx = 0;
        int secondBestFitness = 0;
        int secondBestFitnessIdx = 0;
        int fitness;
        //hope this works
        for(int i = 0; i < lifeForms.length; i++){
            fitness = fitness(lifeForms[i], iterations);
            if(worstFitness > fitness){ worstFitnessIdx = i; worstFitness = fitness;}
            if (bestFitnessIdx < fitness){ bestFitnessIdx = i; bestFitness = fitness;}
            if(fitness <= bestFitnessIdx && fitness >= worstFitness) secondBestFitnessIdx = i; secondBestFitness = fitness;
        }

        Conways[] children = new Conways[2];
        children = doublePointCrossover(lifeForms[bestFitnessIdx],lifeForms[secondBestFitnessIdx]);

        lifeForms[worstFitnessIdx] = children[0];
        lifeForms[secondBestFitnessIdx] = children[1];
    }

    //randomly change one thing
    //this can be called multiple times to call multiple things
    private Conways mutate(Conways lifeForm) {
        //performs one mutation
        Random rand = new Random();

        StringBuilder bitString = new StringBuilder(lifeForm.toBitString());

        for (int i = 0; i < bitString.length(); i++) {
            if (rand.nextInt(20) == 0) {
                if (bitString.charAt(i) == '1') {
                    bitString.setCharAt(i, '0');
                } else {
                    bitString.setCharAt(i, '1');
                }
            } else {

            }
        }
        String result = bitString.toString();

        return new Conways(lifeForm.toBoolArr(result));
    }



    private Conways[] singlePointCrossover(Conways parent, Conways parent2){

        Conways[] children = new Conways[2];

        String pStr = parent.toBitString();
        String pStr2 = parent.toBitString();

        //make children strings
        String cStr = pStr.substring(0,150) + pStr2.substring(150,pStr2.length());
        String cStr2 = pStr2.substring(0,150) + pStr.substring(150,pStr2.length());

        children[0] = new Conways(parent.toBoolArr(cStr));
        children[1] = new Conways(parent.toBoolArr(cStr2));

        return children;
    }

    private Conways[] doublePointCrossover(Conways parent, Conways parent2){
        Conways[] children = new Conways[2];
        String pStr = parent.toBitString();
        String pStr2 = parent2.toBitString();

        String cStr = pStr.substring(0,100) + pStr2.substring(100,300) + pStr.substring(300,pStr.length());
        String cStr2 = pStr2.substring(0,100) + pStr.substring(100,300) + pStr2.substring(300,pStr2.length());

        children[0] = new Conways(parent.toBoolArr(cStr));
        children[1] = new Conways(parent.toBoolArr(cStr2));

        return children;
    }
/*
    //two methods for deciding crossover methods: Union or Intersection?
    //because of the nature and unpredictability of Conways
    //I believe only testing will solve this problem

    private boolean[][] unionGeneCrossover(boolean[][] lifeForm1,
                                           boolean[][] lifeForm2) {

        boolean[][] superiorLifeForm = new boolean[20][20];

        for (int i = 0; i < lifeForm1.length; i++) {
            for (int j = 0; j < lifeForm1.length; j++) {
                superiorLifeForm[i][j] = lifeForm1[i][j];
                superiorLifeForm[i][j] = lifeForm2[i][j];
            }
        }

        return superiorLifeForm;
    }

    private boolean[][] intersectGeneCrossover(boolean[][] lifeForm1,
                                               boolean[][] lifeForm2) {
        boolean[][] superiorLifeForm = new boolean[20][20];

        for (int i = 0; i < lifeForm1.length; i++) {
            for (int j = 0; j < lifeForm1.length; j++) {
                if (lifeForm1[i][j] == ALIVE && lifeForm2[i][j] == ALIVE) {
                    superiorLifeForm[i][j] = ALIVE;
                } else {
                    superiorLifeForm[i][j] = DEAD;
                }
            }
        }
        return superiorLifeForm;
    }
*/
    //evaluates fitness
    private int fitness(Conways life, int iterations) {

        boolean[][] lifeForm = life.getLifeForm();

        int endLiveCells = 0;
        int startLiveCells = 0;

        startLiveCells = getLiveCells(lifeForm);

        if(startLiveCells == 0){
            return 0;
        }else {
            int count = 0;
            //find the end iterations
            while (count < iterations) {
                life.evolve();
                count++;
            }
            endLiveCells = getLiveCells(lifeForm);
        }

        return endLiveCells / (2 * startLiveCells);
    }
    private int getLiveCells(boolean[][] lifeForm){
        int count = 0;
        for(int i = 0; i < lifeForm.length; i++){
            for(int j = 0; j < lifeForm[i].length;j++){
                if(lifeForm[i][j]) count++;
            }
        }
        return count;
    }

    @Override
    public void dumpSuperiorLife(boolean printable) {
        lifeForms[bestFitnessIdx].dumpWorld(false, false);
        if(printable) lifeForms[bestFitnessIdx].dumpWorld(false,true);
    }
    public int getFitness(){
        return bestFitness;
    }

    //a generic test method for abitrary things
    public void test(int iterations) {

        Conways life = new Conways(PUFFER_2);
        //this is just for testing/debugging

        StopWatch time = new StopWatch();
        time.start();
        int i = 0;
        while (i != iterations) {
            life.evolve();
            i++;
            life.dumpWorld(false,false);
        }
        time.stop();
        long endTime = time.getTime();
        System.out.println("the time is: " + endTime);

    }


}
