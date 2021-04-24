package rsa.encryption;

import rsa.encryption.helper.StringToBigIntegerConverter;
import rsa.keygeneration.PublicKey;
import rsa.keygeneration.helper.FastExponentiationHelper;

import java.math.BigInteger;
/**
 * Class for encrypting messages using RSA encryption.
 * */
public class Encryptor {
	/**
	 * Encrypts the given message using the RSA algorithm.
	 * @param message The message to be encrypted.
	 * @param publicKey {@code PublicKey} object representing the public part of the key.
	 * @return {@code BigInteger} object representing the encrypted message
	 * */
	public static BigInteger encrypt(String message, PublicKey publicKey)
	{
		return FastExponentiationHelper.modPow(StringToBigIntegerConverter.stringToPaddedBigInteger(message), publicKey.getE(), publicKey.getN());
	}
}
