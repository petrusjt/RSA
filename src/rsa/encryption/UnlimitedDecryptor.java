package rsa.encryption;

import rsa.keygeneration.PrivateKey;
import rsa.keygeneration.PublicKey;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class UnlimitedDecryptor {

    private static String concatStrings(final List<String> str) {
        String result = "";

        for (int i = 0; i < str.size(); i++) {
            result += str.get(i);
        }

        return result;
    }

    public static String decryptBigIntList(final List<BigInteger> bigIntList, final PrivateKey privateKey) {
        List<String> decryptedTextList = new ArrayList<>();

        bigIntList.stream()
                .forEach(encryptedBigInt -> {
                    decryptedTextList.add(Decryptor.decrypt(encryptedBigInt, privateKey));
                });

        return concatStrings(decryptedTextList);
    }

}
