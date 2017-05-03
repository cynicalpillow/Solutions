
import java.util.*;
public class BuyingApples {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int q = s.nextInt();
		for(int i= 0; i < q; i++){
			int n = s.nextInt();
			int k = s.nextInt();
			int[] prices = new int[k+1];
			for(int j = 1; j < k+1; j++){
				prices[j] = s.nextInt();
			}
			dp(prices, n, k+1);
		}
	}
	public static void dp(int[] prices, int n, int k){
		int[][] matrix = new int[k+1][n+1];
		for(int i = 0 ; i < k+1; i++){
			for(int j = 0; j < n+1; j++){
				if(i == 0 || j == 0){
					matrix[i][j] = 0;
				} else if (j-1 <= k && prices[j-1] != -1){
					matrix[i][j] = Math.min(prices[i-1] + matrix[i-1][k-(j-1)], matrix[i-1][j]);
				} else {
					matrix[i][j] = matrix[i-1][j];
				}
			}
		}
		System.out.println(matrix[k][n]);
	}
}
