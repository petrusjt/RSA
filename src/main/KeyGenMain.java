package main;

import rsa.keygeneration.KeyGenerator;

import java.io.IOException;
/**
 * Main class for key generation.
 * */
public class KeyGenMain {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		KeyGenerator keyGenerator = new KeyGenerator();
		try {
			keyGenerator.generateKey(4096,4096);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		long end = System.currentTimeMillis();
		System.out.println("Kulcsgenerálás: " + (end - start) + "ms");
		try {
			keyGenerator.writeKeysToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
