import java.util.ArrayList;

/**
 * Created by insidious on 4/28/16.
 */
public class PrimeNumbers {

    //prime numbers using a modified Sieve of Erastothenes.
    // it's modified, because it skips any even number
    //because even numbers will never be prime


    public static void findPrimes(int start, int end){

        int maxInt = ((end -3) / 2);

        //once we compute the multiples up to the square root of end,
        //we have reached every single multiple
        int maxPrime = (int)(Math.sqrt(end));

        //initialize all values to true at first
        boolean[] intList = new boolean[maxInt + 1];
        for(int i = 0; i < intList.length; i++){
           intList[i] = true;
        }

        int splitMax = (int)maxPrime / 4;
        int[] splits = new int[4];
        splits[0] = 3;
        splits[1] = splitMax;
        splits[2] = splitMax * 2;
        splits[3] = splitMax * 3;
        //max prime won't work if the beginning number is even
        //it also totally undermines the point of skipping over even numbers
        for(int i = 0; i < splits.length; i ++){
            if(splits[i] % 2 == 0){
                splits[i] = splits[i]-1;
            }
        }
        //threading is splitting up the work of finding multiples, ergo finding primes
        //thr0 finds the multiples of 3-splitMax, thr1 from splitMax - splitMax*2 etc
        primeThread thr0 = new primeThread(intList,splits[0],splitMax,maxInt,start);
        primeThread thr1 = new primeThread(intList,splits[1],splitMax*2,maxInt,start);
        primeThread thr2 = new primeThread(intList,splits[2],splitMax*3,maxInt,start);
        primeThread thr3 = new primeThread(intList,splits[3],maxPrime,maxInt,start);
        thr0.start();
        thr1.start();
        thr2.start();
        thr3.start();

        try{
            thr0.join();
            thr1.join();
            thr2.join();
            thr3.join();
        }catch(Exception e){
            System.out.println("Interrupted");
        }



        ArrayList<Integer> primeList = new ArrayList<Integer>();
        //adding two because we skip even numbers, but 2 is skipped and it's
        //prime, so if start is < 2 we have to prepend it

        if(start < 2){
            primeList.add(2);
        }

        for(int i = 0; i < intList.length; i++){
            int temp = 2*i +3;
            if(intList[i] && temp >= start) {
                primeList.add(temp);
            }
        }

        dumpPrimes(primeList,start,end) ;


    }
    private static void dumpPrimes(ArrayList<Integer> primeList, int start, int end){
        String formattedSize = Integer.toString(primeList.size());
        formattedSize = formatCommas(formattedSize);
        String startF = Integer.toString(start);
        startF = formatCommas(startF);
        String endF = Integer.toString(end);
        endF = formatCommas(endF);

        System.out.println("I found " + formattedSize + " primes between " + startF + " and " + endF);
        System.out.println("here are the first ten:");
        for(int i = 0; i < 10; i ++){
            String tempF = Integer.toString(primeList.get(i));
            tempF=formatCommas(tempF);
            System.out.println("\t" + tempF);
        }
    }

    //stole this from a machine org assignment I did earlier in the year
    //never thought it would come in handy
    private static String formatCommas(String str) {
        //if the string is less than four than no commas need to be added anymore, cause, no commas in 333
        if(str.length() < 4){
            return str;
        }
        //by itself this statement would just put a comma three away from the end, but recursively, the string keeps getting smaller by 3,
        //so it keeps adding commas
        //until the str.length < 4
        //and the recursion will stop
        //this took me forever to figure out
        return formatCommas(str.substring(0, str.length() - 3)) + "," + str.substring(str.length() - 3, str.length());
    }

}
