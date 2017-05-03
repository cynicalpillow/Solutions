import java.util.*;
import java.math.*;
import java.io.*;
public class JoeyAndBiology {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String a = s.readLine();
		String b = s.readLine();
		int[][] dp = new int[n+1][m+1];
		for(int i = 0; i < n+1; i++){
			for(int j = 0; j < m+1; j++)dp[i][j] = Integer.MAX_VALUE;
		}
		for(int i = 0; i < n+1; i++){
			for(int j = 0; j < m+1; j++){
				if(i == 0 && j == 0){
					dp[i][j] = 0;
				} else if(i == 0){
					if(j - 3 >= 0)dp[i][j] = Math.min(dp[i][j-3]+1, dp[i][j]);
					if(j - 2 >= 0)dp[i][j] = Math.min(dp[i][j-2]+1, dp[i][j]);
					if(j - 1 >= 0)dp[i][j] = Math.min(dp[i][j-1]+1, dp[i][j]);
				} else if(j == 0){
					if(i - 3 >= 0)dp[i][j] = Math.min(dp[i-3][j]+1, dp[i][j]);
					if(i - 2 >= 0)dp[i][j] = Math.min(dp[i-2][j]+1, dp[i][j]);
					if(i - 1 >= 0)dp[i][j] = Math.min(dp[i-1][j]+1, dp[i][j]);
				} else {
					if(a.charAt(i-1) == b.charAt(j-1))dp[i][j] = dp[i-1][j-1];
					else {
						for(int z = 3; z >= 1; z--){
							if(i-z >= 0)dp[i][j] = Math.min(dp[i-z][j] + 1, dp[i][j]);
							if(j-z >= 0)dp[i][j] = Math.min(dp[i][j-z] + 1, dp[i][j]);
						}
						dp[i][j] = Math.min(dp[i-1][j-1] + 1, dp[i][j]);
					}
				}
			}
		}
		System.out.println(dp[a.length()][b.length()]);
	}
}
