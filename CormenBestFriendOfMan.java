import java.util.*;
import java.math.*;
import java.io.*;
public class CormenBestFriendOfMan {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(s.readLine());
		int[] a = new int[n];
		for(int i = 0; i < n; i++){
			a[i] = Integer.parseInt(st.nextToken());
		}
		int add = 0;
		for(int i = 0; i < n-1; i++){
			if(a[i] + a[i+1] < m){
				add += m - (a[i] + a[i+1]);
				a[i+1] += m - (a[i] + a[i+1]);
			}
		}
		System.out.println(add);
		for(int i = 0; i < n; i++){
			System.out.print(a[i] + " ");
		}
	}
}
