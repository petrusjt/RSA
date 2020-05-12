package rsa.keygeneration;

import java.math.BigInteger;

/**
 * Class representing the private part of an RSA keypair.
 * */
public class PrivateKey {
	private BigInteger p;
	private BigInteger q;
	private BigInteger d;

	public PrivateKey() {
	}

	public PrivateKey(BigInteger p, BigInteger q, BigInteger d)
	{
		this.p = p;
		this.q = q;
		this.d = d;
	}

	public BigInteger n()
	{
		return p.multiply(q);
	}

	public BigInteger phi_n()
	{
		return p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
	}

	public BigInteger getP() {
		return p;
	}

	public BigInteger getQ() {
		return q;
	}

	public BigInteger getD() {
		return d;
	}

	@Override
	public String toString() {
		return "PrivateKey{" +
				"p=" + p +
				", q=" + q +
				", d=" + d +
				'}';
	}
}
