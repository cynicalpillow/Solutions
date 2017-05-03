import java.util.*;
import java.io.*;
public class WinningAtSports {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(s.readLine());
		for(int tt = 0; tt < t; tt++){
			StringTokenizer st = new StringTokenizer(s.readLine(), "-");
			int yScore = Integer.parseInt(st.nextToken());
			int xScore = Integer.parseInt(st.nextToken());
			System.out.println("Case #" + (tt+1) + ": " + stressFree(yScore, xScore) + " " + stressFul(xScore));
		}
	}
	private static long stressFree(int yScore, int xScore){
		long[][] dp = new long[xScore+1][yScore+1];
		for(int i = 0; i <= xScore; i++){
			for(int j = 0; j <= yScore; j++){
				if(i == 0 && j == 0)dp[i][j] = 0;
				else if(i == 0)dp[i][j] = 1;
				else if(j == 0)dp[i][j] = 0;
				else {
					if(j > i)dp[i][j] = (dp[i-1][j] + dp[i][j-1])%1000000007;
				}
			}
		}
		return dp[xScore][yScore];
	}
	private static long stressFul(int xScore){
		long[] dp = new long[xScore+1];
		dp[0] = 1;
		for(int i = 1; i <= xScore; i++){
			for(int j = 0; j < i; j++){
				dp[i] += (dp[j] * dp[i-j-1])%1000000007;
			}
			dp[i] %=1000000007;
		}
		return dp[xScore];
	}
}
