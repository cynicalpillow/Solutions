import java.util.*;
import java.math.*;
import java.io.*;
public class LeastRoundWay {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		int n = Integer.parseInt(s.readLine());
		int[][] matrix = new int[n][n];
		for(int i = 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			for(int j = 0; j < n; j++){
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		long[][][] dp = new long[n][n][2];
		String[][][] way = new String[n][n][2];
		int zeros = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(i == 0 && j == 0){
					dp[i][j][0] = matrix[i][j];
					way[i][j][0] = "";
				}else if(i == 0){
					dp[i][j][0] = dp[i][j-1][0] * matrix[i][j];
					way[i][j][0] = way[i][j-1][0]+"R";
				} else {
					if(j == 0){
						dp[i][j][0] = dp[i-1][j][0] * matrix[i][j];
						way[i][j][0] = way[i-1][j][0]+"D";
					} else {
						int total = Integer.MAX_VALUE;
						if(dp[i-1][j][1] != 0){
							String y = String.valueOf(dp[i-1][j][1] * matrix[i][j]);
							int temp = 0;
							int x = y.length()-1;
							while(x >= 0 && y.charAt(x) == '0'){
								temp++;
								x--;
							}
							total = temp;
						}
						String y = String.valueOf(dp[i-1][j][0] * matrix[i][j]);
						int x = y.length()-1;
						int temp = 0;
						while(x >= 0 && y.charAt(x) == '0'){
							temp++;
							x--;
						}
						total = Math.min(temp, total);
						int newt = Integer.MAX_VALUE;
						if(dp[i][j-1][1] != 0){
							y = String.valueOf(dp[i][j-1][1] * matrix[i][j]);
							int tempj = 0;
							x = y.length()-1;
							while(x >= 0 && y.charAt(x) == '0'){
								tempj++;
								x--;
							}
							newt = tempj;
						}
						y = String.valueOf(dp[i][j-1][0] * matrix[i][j]);
						x = y.length()-1;
						int tempj = 0;
						while(x >= 0 && y.charAt(x) == '0'){
							tempj++;
							x--;
						}
						newt = Math.min(newt, tempj);
						if(total < newt){
							dp[i][j][0] = dp[i-1][j][0] * matrix[i][j];
							way[i][j][0] = way[i-1][j][0]+"D";
						} else if (newt < total) {
							dp[i][j][0] = dp[i][j-1][0] * matrix[i][j];
							way[i][j][0] = way[i][j-1][0]+"R";
						} else {
							if(newt == tempj){
								dp[i][j][1] = dp[i][j-1][0] * matrix[i][j];
								way[i][j][1] = way[i][j-1][0]+"R";
							} else {
								dp[i][j][1] = dp[i][j-1][1] * matrix[i][j];
								way[i][j][1] = way[i][j-1][1]+"R";
							}
							if(total == temp){
								dp[i][j][0] = dp[i-1][j][0] * matrix[i][j];
								way[i][j][0] = way[i-1][j][0]+"D";
							} else {
								dp[i][j][0] = dp[i-1][j][1] * matrix[i][j];
								way[i][j][0] = way[i-1][j][1]+"D";
							}
						}
						if(i == n-1 && j == n-1)zeros = Math.min(total, newt);
					}
				}
			}
		}
		System.out.println(zeros);
		System.out.println(way[n-1][n-1][0]);
	}
}
