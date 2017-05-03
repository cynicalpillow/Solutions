import java.util.*;
import java.math.*;
import java.io.*;
public class KefaFirstSteps {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		int n = Integer.parseInt(s.readLine());
		StringTokenizer st = new StringTokenizer(s.readLine());
		int[] a = new int[n];
		for(int i = 0; i < n; i++){
			a[i] = Integer.parseInt(st.nextToken());
		}
		int total = 1;
		int max = 1;
		for(int i = 1; i < n; i++){
			if(a[i-1]<= a[i])total++;
			else total = 1;
			max = Math.max(total, max);
		}
		System.out.println(max);
	}
}
