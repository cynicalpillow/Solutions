import java.util.*;
import java.math.*;
import java.io.*;
public class FlippingGame {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		int n = Integer.parseInt(s.readLine());
		StringTokenizer st = new StringTokenizer(s.readLine());
		int[] a = new int[n];
		int be = 0;
		for(int i = 0; i < n; i++){
			a[i] = Integer.parseInt(st.nextToken());
			if(a[i] == 1)be++;
		}
		if(n == 1 && a[0] == 0){
			System.out.println(1);
			return;
		}
		int total = 0;
		for(int x = 0; x < n-1; x++){
			int currtotal = be;
			if(a[x] == 1)currtotal--;
			else currtotal++;
			for(int y = x; y < n; y++){
				if(y != x){
					if(a[y] == 1)currtotal--;
					else currtotal++;
				}
				total = Math.max(total, currtotal);
			}
		}
		System.out.println(total);
	}
}
