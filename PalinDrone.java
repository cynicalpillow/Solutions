import java.util.*;
import java.math.*;
import java.io.*;
public class PalinDrone {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		BigInteger x = new BigInteger(s.readLine());
		if(x.mod(new BigInteger("2")).equals(BigInteger.ZERO)){
			System.out.println(new BigInteger("2").multiply((BigInteger.TEN.modPow(x.divide(new BigInteger("2")), new BigInteger("1000000000")).subtract(BigInteger.ONE))).mod(new BigInteger("1000000000")));
		} else {
			System.out.println(new BigInteger("11").multiply(BigInteger.TEN.modPow(x.subtract(BigInteger.ONE).divide(new BigInteger("2")), new BigInteger("1000000000"))).subtract(new BigInteger("2")).mod(new BigInteger("1000000000")));
		}
	}
}
