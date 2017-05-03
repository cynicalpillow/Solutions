import java.util.*;
import java.math.*;
import java.io.*;
public class Taxi {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		int n = Integer.parseInt(s.readLine());
		StringTokenizer st = new StringTokenizer(s.readLine());
		int[] a = new int[n];
		for(int i= 0; i < n; i++){
			a[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(a);
		int cars = 0;
		boolean[] removed = new boolean[n];
		int lowest = n-1;
		System.out.println(cars);
	}
}
