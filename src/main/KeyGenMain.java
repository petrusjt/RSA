package main;

import rsa.keygeneration.KeyGenerator;

import java.io.IOException;

public class KeyGenMain {
	public static void main(String[] args) {
		KeyGenerator keyGenerator = new KeyGenerator();
		try {
			keyGenerator.generateKey(2048,2048);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		try {
			keyGenerator.writeKeysToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
