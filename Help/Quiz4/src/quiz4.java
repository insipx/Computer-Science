/**
 * Created by insidious on 5/11/16.
 */
public class quiz4 {

    /* Repeatedly tosses the specified TossableCoin (coin) until the number of
 ** times that the specified face value (face) was tossed exceeds the number
 ** of times that the opposite face value was tossed.  The value returned is
 ** the number of times that the coin was tossed (by this method).
 ** pre-condition: coin != null  &&
 **                face == TossableCoin.HEADS || face == TossableCoin.TAILS
 */
    public static int tossUntilExceeds(TossableCoin coin, char face)
    {
        int faceCntr = 0;     // # of times face was tossed
        int oppFaceCntr = 0;  // # of times the opposite to face was tossed


        while ( faceCntr <= oppFaceCntr)
        {
            char tossed = coin.toss();
            if(tossed == face){
                faceCntr++;
            }else{
                oppFaceCntr++;
            }

        }

        return faceCntr + oppFaceCntr;
    }
}
