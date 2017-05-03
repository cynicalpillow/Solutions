import java.util.*;
import java.math.*;
import java.io.*;
public class NextRound {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] a = new int[n];
		st = new StringTokenizer(s.readLine());
		for(int i = 0; i < n; i++){
			a[i] = Integer.parseInt(st.nextToken());
		}
		int x = a[k-1];
		int total = 0;
		for(int i = 0; i < n; i++){
			if(a[i] >= x && a[i] != 0)total++;
			else break;
		}
		System.out.println(total);
	}
}
