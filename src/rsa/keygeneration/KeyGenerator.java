package rsa.keygeneration;

import rsa.keygeneration.helper.ExtendedEuclidean;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Class for generating the encryption and decryption keys using the RSA algorithm.
 * */
public class KeyGenerator {
	private PrivateKey privateKey;
	private PublicKey publicKey;
	private SecureRandom r;
	private boolean isKeyGenerated;
	private int certainty;

	public KeyGenerator()
	{
		this(1000);
	}

	/**
	 * Creates {@code KeyGenerator} object.
	 * @param certainty The certainty that p and q will be prime numbers.
	 * */
	public KeyGenerator(int certainty)
	{
		r = new SecureRandom();
		isKeyGenerated = false;
		this.certainty = certainty;
	}
	/**
	 * Generates key using the RSA algorithm.
	 * @throws Exception if a keypair is already generated
	 * */
	public void generateKey() throws Exception {
		generateKey(256,256);
	}

	/**
	 * Generates key using the RSA algorithm.
	 * @param p_bits The length of the p prime number
	 * @param q_bits The length of the q prime number
	 * @throws Exception if a keypair is already generated
	 * */
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

	/**
	 * Writes the keys into a file specified by parameter {@code fileName}.
	 * @param fileName Name of the file the keys will be written to.
	 * @throws IOException if IOException occurs
	 * */
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
