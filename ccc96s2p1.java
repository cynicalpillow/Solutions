import java.util.*;
import java.math.*;

public class ccc96s2p1 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int q = s.nextInt();
		for(int qs = 0; qs < q; qs++){
			int n = s.nextInt();
			int[] a = new int[n];
			for(int i = 0; i < n; i++){
				a[i] = s.nextInt();
			}
			solve(a, n);
			System.out.println();
		}
	}
	
	public static void solve(int[] a, int n){
		int swaps = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n-1; j++){
				if(a[j] > a[j+1]){
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
					swaps++;
				}
			}
		}
		System.out.print("Optimal train swapping takes " + swaps + " swaps(s).");
	}
	
}
