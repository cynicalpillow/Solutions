import java.util.*;
import java.math.*;
import java.io.*;
public class Fence {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] fences = new int[n];
		int[] totals = new int[n];
		st = new StringTokenizer(s.readLine());
		for(int i = 0; i < n; i++){
			fences[i] = Integer.parseInt(st.nextToken());
			if(i == 0)totals[i] = fences[i];
			else totals[i] = totals[i-1] + fences[i];
		}
		int min = Integer.MAX_VALUE;
		int idc = 0;
		for(int i = k-1; i < n; i++){
			int thistotal = totals[i];
			if(i == k-1){
				min = Math.min(thistotal, min);
			} else {
				thistotal = thistotal - totals[i-k];
				if(thistotal < min){
					min = thistotal;
					idc = i-k+1;
				}
			}
		}
		System.out.println(idc+1);
	}
}
