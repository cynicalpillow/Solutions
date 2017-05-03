import java.util.*;
import java.math.*;
import java.io.*;
public class BatchSort {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] a = new int[n][m];
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(s.readLine());
			for(int j = 0; j < m; j++){
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean check = true;
		for(int i = 0; i < n; i++){
			int offs = 0;
			for(int y = 0; y < m; y++){
				if(y+1 != a[i][y])offs++;
			}
			if(offs > 2){
				check = false;
				break;
			}
		}
		if(check){
			System.out.println("YES");
			return;
		}
		boolean ov = false;
		for(int i = 0; i < m-1; i++){
			for(int j = i+1; j < m; j++){
				int[][] temp = new int[n][m];
				for(int x = 0; x < n; x++){
					for(int y = 0; y < m; y++){
						temp[x][y] = a[x][y];
					}
				}
				boolean possible = true;
				for(int x = 0; x < n; x++){
					int xx = temp[x][j];
					temp[x][j] = temp[x][i];
					temp[x][i] = xx;
				}
				for(int x = 0; x < n; x++){
					int offs = 0;
					for(int y = 0; y < m; y++){
						if(y+1 != temp[x][y])offs++;
					}
					if(offs > 2){
						possible = false;
						break;
					}
				}
				if(possible){
					ov = true;
					break;
				}
			}
			if(ov)break;
		}
		if(ov)System.out.println("YES");
		else System.out.println("NO");
	}
}
