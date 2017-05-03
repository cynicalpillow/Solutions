/*
ID: cynical
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;
import java.math.*;

public class milk2 {

	private static class Interval implements Comparable<Interval>{
		int start;
		int end;
		public Interval(int s, int e){
			start = s;
			end = e;
		}
		public int compareTo(Interval i){
			if(i.start < this.start)return 1;
			else if(i.start > this.start)return -1;
			return 0;
		}
	}
	
	public static void main(String args[]) throws Exception{
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader s = new BufferedReader(new FileReader("milk2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
		int n = Integer.parseInt(s.readLine());
		Interval[] a = new Interval[n];
		int max = 0;
		int nmax = 0;
		int low = 0;
		int high = 0;
		for(int i = 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			a[i] = new Interval(start, end);
		}
		Arrays.sort(a);
		for(int i = 0; i < n; i++){
			int end = a[i].end;
			int start = a[i].start;
			if(i == 0){
				low = start;
				high = end;
			} else {
				if(start > high){
					nmax = Math.max(start-high, nmax);
					max = Math.max(high-low, max);
					low = start;
					high = end;
				} else {
					high = Math.max(high, end);
				}
			}
		}
		max = Math.max(high-low, max);
		out.println(max  + " " + nmax);
		out.close();
	}
	
}
