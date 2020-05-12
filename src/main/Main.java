package main;

import rsa.encryption.Decryptor;
import rsa.encryption.Encryptor;
import rsa.encryption.helper.StringToBigIntegerConverter;
import rsa.keygeneration.KeyGenerator;
import rsa.keygeneration.PrivateKey;
import rsa.keygeneration.PublicKey;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		PublicKey publicKey = new PublicKey();
		PrivateKey privateKey = new PrivateKey();

		if(args.length != 1)
		{
			System.out.println("Usage:");
			System.out.println("\t java Main <keyfile>");
			System.out.println("-----------------------");
			System.out.println("\tThis application can only handle strings shorter than a certain (unknown yet) length.");
			System.out.println("\tHowever you can encrypt and decrypt any character from the unicode table.");
			System.exit(0);
		}
		else
		{
			File file = new File(args[0]);
			if(file.exists() && file.isFile())
			{
				try {
					Scanner fileReader = new Scanner(file);
					String p;
					String q;
					String d;
					String e;
					String n;
					p = fileReader.next();
					q = fileReader.next();
					d = fileReader.next();
					e = fileReader.next();
					n = fileReader.next();
					fileReader.close();
					privateKey = new PrivateKey(new BigInteger(p),new BigInteger(q), new BigInteger(d));
					publicKey = new PublicKey(new BigInteger(e), new BigInteger(n));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else
			{
				System.err.println("File " + args[0] + " cannot be found.");
				System.exit(-1);
			}
		}
		System.out.println(privateKey);
		System.out.println(publicKey);

		String message;
		Scanner stdinScanner = new Scanner(System.in);
		System.out.println("Please input the text to encrypt and decrypt:");
		message = stdinScanner.nextLine();
		stdinScanner.close();
		System.out.println("Message: " + message);

		BigInteger encryptedBigInteger = Encryptor.encrypt(message, publicKey);
		System.out.println("Encrypted message in decimal format: " + encryptedBigInteger);

		String decryptedMessage = Decryptor.decrypt(encryptedBigInteger, privateKey);
		System.out.println("Decrypted message: " + decryptedMessage);
	}
}
