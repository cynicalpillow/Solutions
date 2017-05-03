import java.util.*;
import java.math.*;

public class dp1p4 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.next().length();
		if(n == 1)System.out.println(1);
		else factorial(n);
	}
	public static void factorial(int n){
		BigInteger[] a = new BigInteger[n];
		a[0] = BigInteger.ZERO;
		int val = 1;
		for(int i = 1; i < n; i++){
			val*=2;
			a[i] = a[i-1].add(new BigInteger(String.valueOf(val)));
		}
		System.out.println(a[n-1].mod(new BigInteger("10007")));
	}
}
