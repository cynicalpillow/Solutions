import java.util.*;
import java.math.*;
import java.io.*;
public class Vacations {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		int n = Integer.parseInt(s.readLine());
		int[] a = new int[n];
		StringTokenizer st = new StringTokenizer(s.readLine());
		for(int i = 0; i < n; i++){
			a[i] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[n][3];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < 3; j++){
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		if(a[0] == 0){
			dp[0][0] = Integer.MAX_VALUE;
			dp[0][1] = Integer.MAX_VALUE;
			dp[0][2] = 1;
		} else if (a[0] == 1){
			dp[0][0] = Integer.MAX_VALUE;
			dp[0][1] = 0;
			dp[0][2] = 1;
		} else if (a[0] == 2){
			dp[0][0] = 0;
			dp[0][1] = Integer.MAX_VALUE;
			dp[0][2] = 1;
		} else {
			dp[0][0] = 0;
			dp[0][1] = 0;
			dp[0][2] = 1;
		}
		for(int i = 1; i < n; i++){
			if(a[i] == 0){
				dp[i][0] = Integer.MAX_VALUE;
				
				dp[i][1] = Integer.MAX_VALUE;
				
				if(dp[i-1][1] != Integer.MAX_VALUE)dp[i][2] = Math.min(dp[i-1][1]+1, dp[i-1][2] + 1);
				if(dp[i-1][0] != Integer.MAX_VALUE)dp[i][2] = Math.min(dp[i-1][0] + 1, dp[i][2]);
				dp[i][2] = Math.min(dp[i-1][2]+1, dp[i][2]);
				
			} else if (a[i] == 1){
				if(dp[i-1][0] != Integer.MAX_VALUE)dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]+1);
				else dp[i][1] = dp[i-1][2];

				dp[i][0] = Integer.MAX_VALUE;
				
				if(dp[i-1][1] != Integer.MAX_VALUE)dp[i][2] = Math.min(dp[i-1][1]+1, dp[i-1][2] + 1);
				if(dp[i-1][0] != Integer.MAX_VALUE)dp[i][2] = Math.min(dp[i-1][0] + 1, dp[i][2]);
				dp[i][2] = Math.min(dp[i-1][2]+1, dp[i][2]);
			} else if (a[i] == 2){
				dp[i][1] = Integer.MAX_VALUE;
				if(dp[i-1][1] != Integer.MAX_VALUE)dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]+1);
				else dp[i][0] = dp[i-1][2];

				if(dp[i-1][1] != Integer.MAX_VALUE)dp[i][2] = Math.min(dp[i-1][1]+1, dp[i-1][2] + 1);
				if(dp[i-1][0] != Integer.MAX_VALUE)dp[i][2] = Math.min(dp[i-1][0] + 1, dp[i][2]);
				dp[i][2] = Math.min(dp[i-1][2]+1, dp[i][2]);
			} else {
				if(dp[i-1][1] != Integer.MAX_VALUE)dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]+1);
				else dp[i][0] = dp[i-1][2];
					
				if(dp[i-1][0] != Integer.MAX_VALUE)dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]+1);
				else dp[i][1] = dp[i-1][2];

				if(dp[i-1][1] != Integer.MAX_VALUE)dp[i][2] = Math.min(dp[i-1][1]+1, dp[i-1][2] + 1);
				if(dp[i-1][0] != Integer.MAX_VALUE)dp[i][2] = Math.min(dp[i-1][0] + 1, dp[i][2]);
				dp[i][2] = Math.min(dp[i-1][2]+1, dp[i][2]);
			}
		}
		System.out.println(Math.min(dp[n-1][0],Math.min(dp[n-1][1], dp[n-1][2])));
	}
}
