package rsa;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.io.IOException;

public class RSA {

	private BigInteger p, q, e, d, n, phi_n;
	private int size = 512;

	public RSA() {
		initialize();
	}

	public void initialize() {
		e = BigInteger.valueOf(65537);
		SecureRandom sr = new SecureRandom();

		BigInteger diff = BigInteger.valueOf(2).pow(256);

		/*
		generate 2 prime numbers greater than 512 bits and that have difference of 2^256
		*/
		do {
			sr.generateSeed(512);
			p = new BigInteger(512, 256, sr);
			sr.generateSeed(512);
			q = new BigInteger(512, 256, sr);

		} while (p.bitLength() < size
				|| q.bitLength() < size
				|| !p.isProbablePrime(256)
				|| !q.isProbablePrime(256)
				|| p.subtract(q).abs().compareTo(diff) != 1);

		n = p.multiply(q);
		phi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		d = e.modInverse(phi_n);
	}

	public BigInteger encrypt(BigInteger plainText) {
		return plainText.modPow(e, n);
	}

	public BigInteger decrypt(BigInteger cipherText) {
		return cipherText.modPow(d, n);
	}

	public static void main(String[] args) throws IOException {
        /*
	    Requirements:
	        - BigInteger and SecureRandom should be used
	        - p and q should be at least 512-bit
	        - difference between p and q should be bigger than 2^256
	        - demonstration of encryption and decryption should be given
	        - should include encryption and decryption timing
	        - public key e is 65537

	    Key Generation:
			1. choose 2 distinct prime numbers p and q
			2. compute n = pq
			3. compute phi(n) = (p-1)(q-1)
			4. choose an integer e such that 1 < e < phi(n) and gcd(e, phi(n)) = 1
			5. Determine d as d(inverse) = e (mod phi(n))

		Public Key:
			- consists of the modulus n and the public (encryption) exponent e

		Private Key:
			- consists of the modulus n and the private (decryption) exponent d

		Notes:
			- d, p, q, and phi(n) must be kept secret

		Encryption:
			- c = m^e (mod n)
			- must use modulus exponentiation

		Decryption:
			- m = c^d (mod n)
			- reverse the padding scheme
		*/

		RSA rsa = new RSA();
		int plainText;
		BigInteger b_plainText, b_cipherText;
		int e_start, e_end, encryptTime, d_start, d_end, decryptTime;

		System.out.println("Enter any character: " );
		plainText = System.in.read();
		
		b_plainText = BigInteger.valueOf(plainText);
		e_start = (int)System.nanoTime();
		b_cipherText = rsa.encrypt(b_plainText);
		e_end = (int)System.nanoTime();

		System.out.println("plaintext:\t" + b_plainText.toString());
		System.out.println("ciphertext:\t" + b_cipherText.toString());

		d_start = (int)System.nanoTime();
		b_plainText = rsa.decrypt(b_cipherText);
		d_end = (int)System.nanoTime();
		System.out.println("plaintext after decryption:\t" + b_plainText.toString());

		encryptTime = e_end - e_start;
		decryptTime = d_end - d_start;
		System.out.println("encryption time (ns): " + String.valueOf(encryptTime) + ", decryption time (ns): " + String.valueOf(decryptTime));
    }
}
