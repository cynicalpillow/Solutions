import java.util.*;
import java.math.*;

public class dp1p1 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int q = s.nextInt();
		int[] a= new int[q];
		for(int i = 0; i < q; i++){
			a[i] = s.nextInt();
		}
		dp(a);
	}
	public static void dp(int[] a){
		int[] dp = a.clone();
		int max = dp[0];
		for(int i = 2; i < a.length; i++){
			int ix = dp[i];
			dp[i] = Math.max(dp[i], dp[i-2] + ix);
			if(i-3 >= 0){
				dp[i] = Math.max(dp[i], dp[i-3] + ix);
			}
			max = Math.max(dp[i], max);
		}
		System.out.println(max);
	}
}
