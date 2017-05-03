/*
ID: cynical
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;
import java.math.*;

public class gift1 {

	public static void main(String args[]) throws IOException {
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader s = new BufferedReader(new FileReader("gift1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		int n = Integer.parseInt(s.readLine());
		long[] a = new long[n];
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0; i < n; i++){
			names.add(s.readLine());
		}
		for(int i = 0; i < n; i++){
			String name = s.readLine();
			int nameidx = names.indexOf(name);
			StringTokenizer st = new StringTokenizer(s.readLine());
			long cost = Long.parseLong(st.nextToken());
			int people = Integer.parseInt(st.nextToken());
			if(cost == 0 && people == 0)continue;
			a[nameidx]-=cost;
			if(people == 0)continue;
			long per = cost/(long)people;
			long extra = cost % people;
			a[nameidx] += extra;
			for(int j = 0; j < people; j++){
				name = s.readLine();
				nameidx = names.indexOf(name);
				a[nameidx] += per;
			}
		}
		for(int i = 0; i < n; i++){
			out.println(names.get(i) + " " + a[i]);
		}
		out.close();
	}
	
}
