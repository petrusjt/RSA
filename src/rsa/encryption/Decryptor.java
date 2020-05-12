package rsa.encryption;

import rsa.encryption.helper.StringToBigIntegerConverter;
import rsa.keygeneration.PrivateKey;

import java.math.BigInteger;
import java.util.List;

public class Decryptor {
	public static String decrypt(BigInteger message, PrivateKey privateKey)
	{
		String decrypted = "";
		BigInteger decryptedBigInteger = message.modPow(privateKey.getD(), privateKey.n());
		List<String> letters = StringToBigIntegerConverter.BigIntegerToPaddedStringArray(decryptedBigInteger);
		for(String letter : letters)
		{
			decrypted += letter;
		}
		return decrypted;
	}
}
