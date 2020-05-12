package rsa.keygeneration;

import rsa.keygeneration.helper.ExtendedEuclidean;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;

public class KeyGenerator {
	private PrivateKey privateKey;
	private PublicKey publicKey;
	private Random r;
	private boolean isKeyGenerated;
	private int certainty;

	public KeyGenerator()
	{
		r = new Random();
		isKeyGenerated = false;
	}

	public KeyGenerator(int certainty)
	{
		r = new Random();
		isKeyGenerated = false;
		this.certainty = certainty;
	}

	public void generateKey() throws Exception {
		generateKey(256,256);
	}

	public void generateKey(int p_bits, int q_bits) throws Exception {
		if(isKeyGenerated)
		{
			throw new Exception("Key already generated. To generate another key, create another instance of this class.");
		}
		BigInteger p = new BigInteger(p_bits, this.certainty, r);
		BigInteger q = new BigInteger(q_bits, this.certainty, r);

		BigInteger n = p.multiply(q);
		BigInteger phi_n = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));;

		BigInteger e = new BigInteger("53");
		while(!phi_n.gcd(e).equals(BigInteger.ONE))
		{
			e.add(new BigInteger("2"));
		}
		System.out.println("E generated");
		BigInteger d = ExtendedEuclidean.extendedEuclidean(e, phi_n).get(1);
		while(d.compareTo(BigInteger.ZERO) < 0)
		{
			d.add(phi_n);
		}
		System.out.println("D generated");
		privateKey = new PrivateKey(p, q, d);
		publicKey = new PublicKey(e, n);
		isKeyGenerated = true;
	}

	public void writeKeysToFile() throws IOException {
		writeKeysToFile("keys.txt");
	}

	public void writeKeysToFile(String fileName) throws IOException {
		PrintWriter printWriter = new PrintWriter(fileName);
		printWriter.println(privateKey.getP() + " " + privateKey.getQ() + " " + privateKey.getD());
		printWriter.println(publicKey.getE() + " " + publicKey.getN());
		printWriter.close();
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}
}
