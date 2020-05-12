package rsa.encryption.helper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class StringToBigIntegerConverter {
	/**
	 * Each character will be padded to {@code stringBitLength} bits.
	 * */
	private static int stringBitLength = 32;

	public static BigInteger stringToPaddedBigInteger(String string)
	{
		String padded = "";
		for(int i = 0; i < string.length(); i++)
		{
			String c = String.valueOf(string.charAt(i));
			Integer integer = (int) c.charAt(0);
			c = integer.toString(integer.intValue(), 2);
			c = "0".repeat(stringBitLength - c.length()) + c;
			padded += c;
		}
		return new BigInteger(padded,2);
	}

	public static List<String> BigIntegerToPaddedStringArray(BigInteger bigInteger)
	{
		List<String> letters = new ArrayList<String>();
		String number = bigInteger.toString(2);
		number = "0".repeat(stringBitLength - number.length() % stringBitLength) + number;

		for(int i = 0; i < number.length(); i+= stringBitLength)
		{
			letters.add(String.valueOf((char)Long.parseLong(number.substring(i, i+stringBitLength), 2)));
		}

		return letters;
	}
}
