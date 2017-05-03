import java.util.*;
import java.math.*;
import java.io.*;
public class CutRibbon {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] sizes = new int[3];
		for(int i = 0; i < 3; i++){
			sizes[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[n+1];
		dp[0] = 0;
		for(int i = 1; i <= n; i++){
			for(int j = 0; j < 3; j++){
				if(i == sizes[j])dp[i] = 1;
			}
		}
		for(int i = 1; i <= n; i++){
			for(int j = 0; j < 3; j++){
				if(i - sizes[j] >= 0 && dp[i-sizes[j]] != 0){
					dp[i] = Math.max(dp[i], dp[i-sizes[j]] + 1);
				}
			}
		}
		System.out.println(dp[n]);
	}
}
