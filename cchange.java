import java.util.*;
public class cchange {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int x = s.nextInt();
		int[] c = new int[x];
		for(int i = 0; i < x; i++){
			c[i] = s.nextInt();
		}
		Arrays.sort(c);
		dp(c, n);
	}
	public static void dp(int[] c, int n){
		int[] dp = new int[n+1];
		dp[0] = 0;
		for(int i = 1; i < n+1; i++){
			dp[i] = Integer.MAX_VALUE;
		}
		for(int i = 1; i < n+1; i++){
			for(int j = 0; j < c.length; j++){
				if(c[j] <= i && i-c[j] >= 0){
					if(dp[i-c[j]] != Integer.MAX_VALUE && dp[i-c[j]] + 1 < dp[i]){
						dp[i] = dp[i-c[j]] + 1;
					}
				}
			}
			if(dp[i] == Integer.MAX_VALUE){
				dp[i] = 0;
			}
		}
		System.out.println(dp[n]);
	}
}
