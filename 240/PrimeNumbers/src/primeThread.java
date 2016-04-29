/**
 * Created by insidious on 4/29/16.
 */
public class primeThread extends Thread {

    boolean[] intList;
    int startMPrime;
    int maxPrime;
    int maxInt;
    int start;

    public primeThread(boolean[] intList, int startMPrime, int maxPrime, int maxInt, int start){
        this.intList = intList;
        this.startMPrime = startMPrime;
        this.maxPrime = maxPrime;
        this.start = start;
        this.maxInt = maxInt;
    }

    public void run(){
       for(int p = startMPrime; p < maxPrime+1; p = p +2) {

           int i = ((p - 3) / 2);
           synchronized (intList) {
               if (intList[i]) {
                   int i2 = ((p * p) - 3) / 2;

                   //make any multiples of the prime false, we only have to worry about primes > start
                   //reduces cycles of the for loop
                   for (int k = i2; k < maxInt + 1; k = k + p) {
                       intList[k] = false;
                   }

               }
           }
       }
    }
    public boolean[] getList(){
        return intList;
    }

}
