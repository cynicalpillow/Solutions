import java.util.*;
import java.math.*;
public class FibonnaciDiv2 {
	
	/*
	 * Problem Statement
    	The Fibonacci sequence is defined as follows:
		F[0] = 0
		F[1] = 1
		for each i >= 2: F[i] = F[i-1] + F[i-2]
		Thus, the Fibonacci sequence starts as follows: 0, 1, 1, 2, 3, 5, 8, 13, ... 
		The elements of the Fibonacci sequence are called Fibonacci numbers. 
		You're given an int N. You want to change N into a Fibonacci number. 
		This change will consist of zero or more steps. In each step, you can either increment or decrement the number you currently have. 
		That is, in each step you can change your current number X either to X+1 or to X-1. 
		Return the smallest number of steps needed to change N into a Fibonacci number.
	 */
	
	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		twoPointer(n);
	}
	
	public static void solve(int n){
		if(n == 1 || n == 0 || n == 2){
			System.out.println(0);
			return;
		}
		long[] fib = new long[1000000];
		long[] diffs = new long[1000000];
		fib[0] = 0;
		fib[1] = 1;
		long min = Long.MAX_VALUE;
		for(int i = 2; i < 1000000; i++){
			fib[i] = fib[i-1] + fib[i-2];
			diffs[i] = Math.abs((long)n-fib[i]);
			min = Math.min(diffs[i], min);
		}
		System.out.println(min);
	}
	//Alternate
	public static void twoPointer(int n){
		long b = 0;
		long a = 1;
		long min = Long.MAX_VALUE;
		while(b < n){
			long temp = a;
			a = a+b;
			min = Math.min(a-n,n-b);
			b = temp;
		}
		System.out.println(Math.min(min, b-n));
	}
}
