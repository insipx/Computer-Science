package conway.highest.value;

import conways.Conways;

import java.util.Random;


/**
 * Created by insidious on 4/5/16.
 * <p>
 * Optimizer uses the Gene Algorithm to compute best fitness(EndLiveCells/2*StartLiveCells)
 * of conways Game of life
 *
 * uses bitStrings to store Game of Life Representation for Mutation
 *
 * it seems as if the fitness and evolve functions are the bottlenecks
 *      if adding onto this in the future, it's worth looking into a different method
 *      for checking what cells are alive/dead to speed up the program
 *
 */

public class ConwaysOptimizer extends Conways implements OptimizerInterface {

    //Global Variables
    private int iterations;
    private double bestFitness;

    private int bestFitIdx;
    private int secondBestFitIdx;
    private int worstFitIdx;

    private Conways[] lifeForms;

    //constructors, initFitness() will always be at the end to catch the fitness of any
    //seeds

    //default
    public ConwaysOptimizer() {
        lifeForms = new Conways[3];
        lifeForms[0] = new Conways(EMPTY);
        lifeForms[1] = new Conways(EMPTY);
        lifeForms[2] = new Conways(EMPTY);
        iterations = 1000;
        initFitness();
    }

    //default with iterations
    public ConwaysOptimizer(int iterations) {
        lifeForms = new Conways[3];
        lifeForms[0] = new Conways(EMPTY);
        lifeForms[1] = new Conways(EMPTY);
        lifeForms[2] = new Conways(EMPTY);
        this.iterations = iterations;
        initFitness();
    }

    //seeded constructors
    public ConwaysOptimizer(Conways[] lifeForms) {
        this.lifeForms = lifeForms;
        iterations = 1000;
        initFitness();
    }

    //seeded /w iterations
    public ConwaysOptimizer(Conways[] lifeForms, int iterations) {
        this.lifeForms = lifeForms;
        this.iterations = iterations;
        initFitness();
    }

    //get functions (only one)
    public double getFitness(){
        return bestFitness;
    }

    public void run(){

        //only mutate the worst lifeForms using the best
        //elitist selection
        //Only mutate inferior lifeForms
        //because the nature of conways is unpredictable, so we don't want to throw out the best solution

        //highly selective algorithm, 1/500 chance of being mutated
        //found this selectiveness returns best fitness
        //lifeForm (boolean[][]) and probability (1/x, ie 1/20)

        lifeForms[secondBestFitIdx] = mutate(lifeForms[secondBestFitIdx], 20);
        lifeForms[worstFitIdx] = mutate(lifeForms[worstFitIdx], 20);


        nextGeneration();
    }

    private void nextGeneration() {

        initFitness();

        Conways[] children;

        //found that a singlePointCrossover works much better than doublePoint
        //probably because it preserves symmetry better than doublePoint
        //doublePoint obliterates any pattern it created so far
        children = singlePointCrossover(lifeForms[bestFitIdx], lifeForms[secondBestFitIdx] );
        lifeForms[worstFitIdx] = children[0];
        lifeForms[secondBestFitIdx] = children[1];
    }

    //randomly change something returns a new lifeForm object
    //uses StringBuilder java b/c Strings are immutable and
    //creating a workaround for that would be pointless...
    private Conways mutate(Conways lifeForm, int probability) {
        //performs one mutation
        Random rand = new Random();
        //string builder because Strings are immutable
        StringBuilder bitString = new StringBuilder(lifeForm.toBitString());

        for (int i = 0; i < bitString.length(); i++) {
            if (rand.nextInt(probability) == 0) {
                //toggle
                if (bitString.charAt(i) == '1') {
                    bitString.setCharAt(i, '0');
                } else {
                    bitString.setCharAt(i, '1');
                }
            }
        }
        String result = bitString.toString();

        return new Conways(lifeForm.toBoolArr(result));
    }

    //finds the fitness of all the lifeForms, putting them in their respective vars
    private void initFitness(){
        double worstFitness = 999999999.99;

        //this does not work at all
        double[] fitX = new double[3];
        fitX[0] = fitness(lifeForms[0],iterations);
        fitX[1] = fitness(lifeForms[1],iterations);
        fitX[2] = fitness(lifeForms[2],iterations);

        for(int i = 0; i < fitX.length; i++){

            if(worstFitness > fitX[i]){
                worstFitness = fitX[i];
                worstFitIdx = i;
            }
            if(bestFitness < fitX[i]){
                bestFitness = fitX[i];
                bestFitIdx = i;
            }
        }
        int i = 0;
        while(i == bestFitIdx || i == worstFitIdx){
            i++;
        }
        secondBestFitIdx = i;

    }


    private Conways[] singlePointCrossover(Conways parent, Conways parent2){

        Conways[] children = new Conways[2];

        String pStr = parent.toBitString();
        String pStr2 = parent.toBitString();

        //make children strings
        //from limited testing it seams like the crossover point is arbitrary
        //after 1000 iterations, returning 1-2/3-4 about the same rate
        String cStr = pStr.substring(0,150) + pStr2.substring(150,pStr2.length());
        String cStr2 = pStr2.substring(0,150) + pStr.substring(150,pStr2.length());

        children[0] = new Conways(parent.toBoolArr(cStr));
        children[1] = new Conways(parent.toBoolArr(cStr2));

        return children;
    }

    //this performs worse than singlepoint
    //keeping for reference
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

    //evaluates fitness
    //this is the bottleneck so I use it as sparsly as possible
    private double fitness(Conways life, int iterations) {

        //create a tmp because we don't want to evolve our pop of lifeForms
        Conways tmp = new Conways(life.getLifeForm());

        //uncomment this line for a cool representation of fitness
        //tmp.dumpWorld(false,true);

        double endLiveCells = 0;
        double startLiveCells = 0;

        startLiveCells = getLiveCells(tmp.getLifeForm());

        //a simple optimization
        //if the start live cells are already 0, then
        //nothing will ever evolve from that
        if(startLiveCells == 0){
            return 0;
        }else {
            int count = 0;
            //find the end iterations
            while (count < iterations) {
                tmp.evolve();
                count++;
            }
            endLiveCells = getLiveCells(tmp.getLifeForm());
        }
        double denominator = 2*startLiveCells;

        return endLiveCells / denominator;
    }

    private double getLiveCells(boolean[][] lifeForm){
        double count = 0;
        for(int i = 0; i < lifeForm.length; i++){
            for(int j = 0; j < lifeForm[i].length;j++){
                if(lifeForm[i][j]){ count++;}
            }
        }
        return count;
    }



    //Dump Functions
    //EndGrid and StartGrids
    //printable is for testing,
    //ie putting into a text file so conways can parse it and
    //evolve it to see if the algorithm actually worked
    @Override
    public void dumpSuperiorLife(boolean printable) {
        if(printable){ lifeForms[bestFitIdx].dumpWorld(true,true);

        }else{
            lifeForms[bestFitIdx].dumpWorld(true, false);
        }
    }
    //dumps the grid after the global iterations count
    public void dumpEndGrid(boolean printable){

        Conways tmp = new Conways(lifeForms[bestFitIdx].getLifeForm());
        for(int i = 0; i < iterations; i++){
            tmp.evolve();
        }

        if(printable){
            tmp.dumpWorld(true, true);
        }else{
            tmp.dumpWorld(true,false);
        }
    }

    //for general testing of the algorithm
    public void test(int lifeType, int iterations){

        Conways test = new Conways(lifeType);
        fitness(test, iterations);
        System.out.println(fitness(test,1000));



    }
}
