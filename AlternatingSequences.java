import java.util.*;
import java.math.*;

public class AlternatingSequences {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] a = new int[n];
		for(int i = 0; i < n; i++){
			a[i] = s.nextInt();
		}
		solve(a);
	}
	public static void solve(int[] a){
		int[] dp = new int[a.length];
		for(int i = 0; i < a.length; i++){
			dp[i] = 1;
		}
		int max = Integer.MIN_VALUE;
		for(int i = 1; i < a.length; i++){
			for(int j = 0; j < i; j++){
				if(a[i] > 0){
					if(a[j] < 0 && Math.abs(a[j]) < Math.abs(a[i])){
						dp[i] = Math.max(dp[j]+1, dp[i]);
					}
				} else {
					if(a[j] > 0 && Math.abs(a[j]) < Math.abs(a[i])){
						dp[i] = Math.max(dp[j]+1, dp[i]);
					}
				}
			}
			max = Math.max(dp[i], max);
		}
		if(max == Integer.MIN_VALUE)System.out.println(1);
		else System.out.println(max);
	}
}
