/*
ID: cynical
LANG: JAVA
TASK: combo
*/
import java.io.*;
import java.util.*;
import java.math.*;
public class combo {
	public static int count = 0;
	public static void main(String args[]) throws Exception{
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader s = new BufferedReader(new FileReader("combo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
		int n = Integer.parseInt(s.readLine());
		StringTokenizer st = new StringTokenizer(s.readLine());
		int[] a = new int[3];
		for(int i = 0; i < 3; i++){
			a[i] = Integer.parseInt(st.nextToken());
		}
		int[] c = new int[3];
		st = new StringTokenizer(s.readLine());
		for(int i = 0; i < 3; i++){
			c[i] = Integer.parseInt(st.nextToken());
		}
		int total = 0;
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				for(int y = 1; y <= n; y++){
					if((closeEnough(i, a[0], n) && closeEnough(j, a[1], n) && closeEnough(y, a[2], n)) || (closeEnough(i, c[0], n) && closeEnough(j, c[1], n) && closeEnough(y, c[2], n))){
						total++;
					}
				}
			}
		}
		out.println(total);
		out.close();
	}
	public static boolean closeEnough(int n, int m, int max){
		if(n == m)return true;
		if(n > m){
			if(m+2 < n){
				if(m+2 >= n){
					return true;
				}
			} else {
				if(n > m && n <= max){
					return true;
				}
			}
			if(m - 2 <= 0){
				if(max - Math.abs(m-2) <= n){
					return true;
				}
			}
		} else {
			if(m-2 > 0){
				if(m-2 <= n){
					return true;
				}
			} else {
				if(n > 0 && n < m){
					return true;
				}
			}
			if(m + 2 > max){
				if((m+2) % max >= n){
					return true;
				}
			}
		}
		return false;
	}
}
