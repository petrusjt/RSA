package rsa.keygeneration;

import java.math.BigInteger;

/**
 * Class representing the private part of an RSA keypair.
 * */
public class PublicKey {
	private BigInteger e;
	private BigInteger n;

	public PublicKey() {
	}

	public PublicKey(BigInteger e, BigInteger n)
	{
		this.e = e;
		this.n = n;
	}

	public BigInteger getE() {
		return e;
	}

	public BigInteger getN() {
		return n;
	}

	@Override
	public String toString() {
		return "PublicKey{" +
				"e=" + e +
				", n=" + n +
				'}';
	}
}
