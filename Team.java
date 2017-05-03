import java.util.*;
import java.math.*;
import java.io.*;
public class Team {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(s.readLine());
		int total = 0;
		for(int i = 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			int know = 0;
			for(int j = 0; j < 3; j++){
				int x = Integer.parseInt(st.nextToken());
				if(x == 1)know++;
			}
			if(know >= 2)total++;
		}
		System.out.println(total);
	}
}
