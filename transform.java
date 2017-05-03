/*
ID: cynical
LANG: JAVA
TASK: transform
*/
import java.io.*;
import java.util.*;
import java.math.*;

public class transform {
	public static void main(String args[]) throws Exception {
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader s = new BufferedReader(new FileReader("transform.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		int n = Integer.parseInt(s.readLine());
		int[][] a = new int[n][n];
		for(int i = 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(s.readLine(), "-@", true);
			for(int j = 0; j < n; j++){
				if(st.nextToken().equals("@")){
					a[i][j] = 1;
				} else {
					a[i][j] = 0;
				}
			}
		}
		int[][] b = new int[n][n];
		for(int i = 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(s.readLine(), "-@", true);
			for(int j = 0; j < n; j++){
				if(st.nextToken().equals("@")){
					b[i][j] = 1;
				} else {
					b[i][j] = 0;
				}
			}
		}
		if(rotate90(a, b))out.println(1);
		else if(rotate180(a,b))out.println(2);
		else if(rotate270(a,b))out.println(3);
		else if(reflect(a,b))out.println(4);
		else if (combination(a,b))out.println(5);
		else if(nochange(a,b))out.println(6);
		else out.println(7);
		out.close();
	}
	public static boolean rotate90(int[][] a, int[][] b){
		int[][] c = new int[a.length][a.length];
		int ix = 0;
		for(int i = a.length - 1; i >= 0; i--){
			int jx = 0;
			for(int j = 0; j < a.length; j++){
				c[ix][jx] = b[j][i];
				if(c[ix][jx] != a[ix][jx])return false;
				jx++;
			}
			ix++;
		}
		return true;
	}
	public static boolean rotate180(int[][] a, int[][] b){
		int[][] c = new int[a.length][a.length];
		int ix = 0;
		for(int i = a.length - 1; i >= 0; i--){
			int jx = 0;
			for(int j = a.length-1; j >= 0; j--){
				c[ix][jx] = b[i][j];
				if(c[ix][jx] != a[ix][jx])return false;
				jx++;
			}
			ix++;
		}
		return true;
	}
	public static boolean rotate270(int[][] a, int[][] b){
		int[][] c = new int[a.length][a.length];
		int ix = a.length-1;
		for(int i = a.length - 1; i >= 0; i--){
			int jx = 0;
			for(int j = a.length-1; j >= 0; j--){
				c[ix][jx] = b[j][i];
				if(c[ix][jx] != a[ix][jx])return false;
				jx++;
			}
			ix--;
		}
		return true;
	}
	public static boolean reflect(int[][] a, int[][] b){
		int[][] c = new int[a.length][a.length];
		int ix = 0;
		for(int i = a.length - 1; i >= 0; i--){
			int jx = 0;
			for(int j = 0; j < a.length; j++){
				c[jx][ix] = b[j][i];
				if(c[jx][ix] != a[jx][ix])return false;
				jx++;
			}
			ix++;
		}
		return true;
	}
	public static boolean combination(int[][] a, int[][] b){
		int[][] c = new int[a.length][a.length];
		int ix = 0;
		for(int i = a.length - 1; i >= 0; i--){
			int jx = 0;
			for(int j = 0; j < a.length; j++){
				c[jx][ix] = b[j][i];
				jx++;
			}
			ix++;
		}
		if(rotate90(a, c) || rotate180(a, c) || rotate270(a, c))return true;
		return false;
	}
	public static boolean nochange(int[][] a, int[][] b){
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a.length; j++){
				if(a[i][j] != b[i][j])return false;
			}
		}
		return true;
	}
}
