package rsa.encryption;

import rsa.keygeneration.PublicKey;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class UnlimitedEncryptor {

    private static List<String> partitionStringBy(final String str, final int partitionSize) {
        List<String> result = new ArrayList<>();

        int index = -1;
        for (int i = 0; i < str.length(); i++) {
            if (i % partitionSize == 0) {
                result.add("");
                index++;
            }
            result.set(index, result.get(index).concat(String.valueOf(str.charAt(i))));
        }

        return result;
    }

    public static List<BigInteger> encryptText(final String text, final PublicKey publicKey) {
        List<BigInteger> encryptedPartitions = new ArrayList<>();

        partitionStringBy(text, 64).stream()
                .forEach(str -> {
                    encryptedPartitions.add(Encryptor.encrypt(str, publicKey));
                        });


        return encryptedPartitions;
    }
}
