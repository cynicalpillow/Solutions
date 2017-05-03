import java.util.*;
import java.math.*;
public class dp1p3 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n  =s.nextInt();
		int[] a=new int[n];
		for(int i= 0; i < n; i++){
			a[i] = s.nextInt();
		}
		solve(a);
	}
	public static void solve(int[] a){
		int[] dp = new int[a.length];
		for(int i = 0; i < a.length; i++){
			dp[i] = 1;
		}
		int max = Integer.MIN_VALUE;
		for(int i = 1; i < a.length; i++){
			for(int j = 0; j < i; j++){
				if(a[i] > a[j]){
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			max = Math.max(dp[i], max);
		}
		System.out.println(max);
	}
}
