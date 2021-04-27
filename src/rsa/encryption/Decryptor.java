package rsa.encryption;

import rsa.encryption.helper.StringToBigIntegerConverter;
import rsa.keygeneration.PrivateKey;
import rsa.keygeneration.helper.ChineseRemainderTheoremHelper;

import java.math.BigInteger;
import java.util.List;

/**
 * Class for decrypting RSA encrypted messages.
 * */
public class Decryptor {
	/**
	 * Decrypts the RSA encrypted message.
	 * @param message {@code BigInteger} object that represents the encrypted message.
	 * @param privateKey {@code PrivateKey} object that represents the private part of the key the message was encrypted with.
	 * @return The original message.
	 * */
	public static String decrypt(BigInteger message, PrivateKey privateKey)
	{
		String decrypted = "";
		//BigInteger decryptedBigInteger = FastExponentiationHelper.modPow(message, privateKey.getD(), privateKey.n());
		BigInteger decryptedBigInteger = ChineseRemainderTheoremHelper.chineseRemainder(message, privateKey);
		List<String> letters = StringToBigIntegerConverter.BigIntegerToPaddedStringArray(decryptedBigInteger);
		for(String letter : letters)
		{
			decrypted += letter;
		}
		return decrypted;
	}
}
