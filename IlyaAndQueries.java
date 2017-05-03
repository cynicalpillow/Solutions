import java.util.*;
import java.math.*;
import java.io.*;
public class IlyaAndQueries {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		String str = s.readLine();
		int[] dp = new int[str.length()];
		dp[0] = 0;
		for(int i = 1; i < str.length(); i++){
			if(str.charAt(i-1) == str.charAt(i))dp[i] = dp[i-1]+1;
			else dp[i] = dp[i-1];
		}
		int q = Integer.parseInt(s.readLine());
		for(int i = 0; i < q; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			System.out.println(dp[y] - dp[x]);
		}
	}
}
