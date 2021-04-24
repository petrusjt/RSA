package rsa.keygeneration.helper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FastExponentiationHelper {

    public static BigInteger modPow(BigInteger a, BigInteger b, BigInteger m) {
        BigInteger x = BigInteger.ONE;

        while (b.compareTo(BigInteger.ZERO) > 0) {
            if (b.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
                x = x.multiply(a).mod(m);
            }

            a = a.pow(2).mod(m);
            b = b.divide(BigInteger.TWO);
        }

        return x;
    }
}
