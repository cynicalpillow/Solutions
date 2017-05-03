import java.util.*;
import java.math.*;
public class ShorterSuperSum {

	/*
	 	SuperSum is a function defined as:
		SuperSum(0 , n) = n, for all positive n.
		SuperSum(k , n) = SuperSum(k-1 , 1) + SuperSum(k-1 , 2) + ... + SuperSum(k-1 , n), for all positive k, n.
		Given k and n, return the value for SuperSum(k , n).
	 */
	
	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int k = s.nextInt();
		solve(n, k);
	}
	public static void solve(int n, int k){
		int[][] matrix = new int[n+1][k+1];
		for(int i = 0; i <= n; i++){
			matrix[i][0] = 0;
		}
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= k; j++){
				if(i == 1)matrix[i][j] = matrix[i][j-1] + j;
				else matrix[i][j] = matrix[i][j-1] + matrix[i-1][j];
			}
		}
		System.out.println(matrix[n][k]);
	}
}
