import java.util.*;
import java.math.*;

public class Puzzles {
	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int m = s.nextInt();
		int[] puzzles = new int[m];
		for(int i = 0; i < m; i++){
			puzzles[i] = s.nextInt();
		}
		solve(puzzles, n, m);
	}
	public static void solve(int[] puzzles, int n, int m){
		Arrays.sort(puzzles);
		int diff = Integer.MAX_VALUE;
		for(int i = 0; i <= m-n; i++){
			int min = puzzles[i];
			int max = puzzles[i+n-1];
			diff = Math.min(max-min, diff);
		}
		System.out.println(diff);
	}
}
