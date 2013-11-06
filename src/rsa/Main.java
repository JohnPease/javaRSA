package rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Main {

    public static void main(String[] args) {
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
    }
}
