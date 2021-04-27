package rsa.keygeneration.helper;

import rsa.keygeneration.PrivateKey;

import java.math.BigInteger;
import java.util.List;

public class ChineseRemainderTheoremHelper {

    public static BigInteger chineseRemainder(BigInteger c, PrivateKey privateKey) {
        BigInteger d_p = privateKey.getD().mod(privateKey.getP().subtract(BigInteger.ONE));
        BigInteger d_q = privateKey.getD().mod(privateKey.getQ().subtract(BigInteger.ONE));
        BigInteger m_p = FastExponentiationHelper.modPow(c, d_p, privateKey.getP());
        BigInteger m_q = FastExponentiationHelper.modPow(c, d_q, privateKey.getQ());

        List<BigInteger> extEuclidResult = ExtendedEuclidean.extendedEuclidean(privateKey.getP(), privateKey.getQ());

        BigInteger m = m_p.multiply(extEuclidResult.get(2)).multiply(privateKey.getQ())
                .add(m_q.multiply(extEuclidResult.get(1)).multiply(privateKey.getP())).mod(privateKey.n());

        return m;
    }

}
