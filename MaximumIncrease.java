import java.util.*;
import java.math.*;
import java.io.*;
public class MaximumIncrease {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		int n = Integer.parseInt(s.readLine());
		int total = 1;
		int[] a = new int[n];
		StringTokenizer st = new StringTokenizer(s.readLine());
		for(int i = 0; i < n; i++){
			a[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		for(int i = 1; i < n; i++){
			if(a[i] > a[i-1])dp[i] = dp[i-1]+1;
			total = Math.max(total, dp[i]);
		}
		System.out.println(total);
	}
}
