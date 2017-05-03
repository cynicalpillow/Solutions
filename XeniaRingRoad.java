import java.util.*;
import java.math.*;
import java.io.*;
public class XeniaRingRoad {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		long[] tasks = new long[m];
		st = new StringTokenizer(s.readLine());
		for(int i = 0; i < m; i++){
			tasks[i] = Long.parseLong(st.nextToken());
		}
		long house = 1;
		long dist = 0;
		for(int i = 0; i < m; i++){
			if(i == 0)dist += tasks[i] - house;
			else{
				if(tasks[i] < house){
					dist += n-house;
					dist += tasks[i];
				} else if(tasks[i] > house){
					dist += tasks[i] - house;
				}
			}
			house = tasks[i];
		}
		System.out.println(dist);
	}
}
