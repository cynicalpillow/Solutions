import java.util.*;
import java.math.*;
import java.io.*;
public class RGPCScrabbleNuts {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String a = s.readLine();
		String b = s.readLine();
		long[][] dp = new long[2][m];
		for(int i = 0; i < n+1; i++){
			for(int j = 0; j < m; j++){
				if(i == 0 && j == 0){
					dp[0][j] = 0;
				} else if(i == 0){
					dp[0][j] = dp[0][j-1]+1;
 				} else if (j == 0){
 					dp[1][j] = dp[0][j]+1;
 				} else {
 					if(a.charAt(i-1) == b.charAt(j-1)){
 						dp[1][j] = dp[0][j-1];
 					} else {
 						dp[1][j] = Math.min(dp[1][j-1] + 1, Math.min(dp[0][j] + 1, dp[0][j-1]+1));
 					}
 				}
			}
			if(i > 0){
				for(int j =0; j < m; j++){
					dp[0][j] = dp[1][j];
				}
			}
		}
		long total = 0;
		for(int j = 1; j < m; j++){
			total += dp[0][j];
		}
		System.out.println(total);
	}
}
