package math;

import java.util.ArrayList;

public class PrimeFactors {

    /*
    1. Call precomp.
    2. Use primeFactorsOf as required
     */

    static int[] factorOf=new int[1_000_000];
    static ArrayList<Integer> primeFactsOf(int n) {
        ArrayList<Integer> toReturn=new ArrayList<>();
        while (n!=1) {
            toReturn.add(factorOf[n]);
            int added=factorOf[n];
            while (n%added==0) n/=added;
        }
        return toReturn;
    }

    static void precomp() {
        for (int i=2; i<factorOf.length; i++) {
            if (factorOf[i]!=0)
                continue;
            for (int j=i; j<factorOf.length; j+=i)
                factorOf[j]=i;
        }
    }
}
