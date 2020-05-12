package rsa.encryption;

import rsa.encryption.helper.StringToBigIntegerConverter;
import rsa.keygeneration.PublicKey;

import java.math.BigInteger;

public class Encryptor {
	public static BigInteger encrypt(String message, PublicKey publicKey)
	{
		return StringToBigIntegerConverter.stringToPaddedBigInteger(message).modPow(publicKey.getE(), publicKey.getN());
	}
}
