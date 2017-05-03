import java.util.*;
import java.math.*;
import java.io.*;
public class VMSSRestoringReputation {
	public static void main(String args[]) throws Exception {
		Scanner s = new Scanner(System.in);
		int d = s.nextInt();
		int t = s.nextInt();
		int r = s.nextInt();
		String a = s.next();
		String b = s.next();
		int[][] dp = new int[a.length()+1][b.length()+1];
		for(int i = 0; i <= a.length(); i++){
			for(int j = 0; j <= b.length(); j++){
				if(i == 0 && j == 0)dp[i][j] = 0;
				else if(i == 0)dp[i][j] = dp[i][j-1] + t;
				else if(j == 0)dp[i][j] = dp[i-1][j] + d;
				else {
					if(a.charAt(i-1) == b.charAt(j-1)){
						dp[i][j] = dp[i-1][j-1];
					} else {
						dp[i][j] = Math.min(dp[i-1][j] + d, Math.min(dp[i-1][j-1] + r, dp[i][j-1] + t));
					}
				}
			}
		}
		System.out.println(dp[a.length()][b.length()]);
	}
}
