package rsa.keygeneration.helper;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PrimeTestHelper {
    public static boolean isProbablePrime(BigInteger p) {
        return millerRabinTest(p, 200, new SecureRandom());
    }

    private static boolean millerRabinTest(BigInteger p, int iterations, Random rnd) {
        // Find a and m such that m is odd and this == 1 + 2**a * m
        BigInteger thisMinusOne = p.subtract(BigInteger.ONE);
        BigInteger m = thisMinusOne;
        int a = m.getLowestSetBit();
        m = m.shiftRight(a);

        // Do the tests
        if (rnd == null) {
            rnd = ThreadLocalRandom.current();
        }
        for (int i=0; i < iterations; i++) {
            // Generate a uniform random on (1, this)
            BigInteger b;
            do {
                b = new BigInteger(p.bitLength(), rnd);
            } while (b.compareTo(BigInteger.ONE) <= 0 || b.compareTo(p) >= 0);

            int j = 0;
            BigInteger z = b.modPow(m, p);
            while (!((j == 0 && z.equals(BigInteger.ONE)) || z.equals(thisMinusOne))) {
                if (j > 0 && z.equals(BigInteger.ONE) || ++j == a)
                    return false;
                z = z.modPow(BigInteger.TWO, p);
            }
        }
        return true;
    }
}
