import java.util.*;
import java.math.*;

public class dp1p2 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] a = new int[n];
		for(int i = 0; i < n; i++){
			a[i] = s.nextInt();
		}
		solve(a, n);
	}
	
	public static void solve(int[] a, int n){
		int[][] dp = new int[n][2];
		int x = n-1;
		for(int i = 0; i < n; i++){
			if(i - 2 >= 0){
				dp[i][0] = Math.max(dp[i-2][0] + a[i], dp[i-2][1] + a[x]);
				dp[i][1] = Math.max(dp[i-2][0] + a[x], dp[i-2][1] + a[i]);
			} else {
				dp[i][0] = a[i];
				dp[i][1] = a[x];
			}
			x--;
		}
		for(int i = 0; i < n; i++){
			System.out.println(dp[i][0] + " " + dp[i][1]);
		}
		if(n % 2 == 0){
			System.out.println(Math.max(dp[n-2][0], dp[n-2][1]));
		} else {
			System.out.println(Math.max(dp[n-1][0], dp[n-1][1]));
		}
	}
	
}
