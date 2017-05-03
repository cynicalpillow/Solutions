import java.util.*;
import java.math.*;
import java.io.*;
public class AlexAndAnimalRights {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[][] matrix = new int[r][c];
		for(int i = 0; i < r; i++){
			String y = s.readLine();
			for(int j = 0; j < c; j++){
				if(y.charAt(j) == '#')matrix[i][j] = -1;
				else if(y.charAt(j) == 'M') matrix[i][j] = 1;
			}
		}
		boolean[][] visited = new boolean[r][c];
		int count = 0;
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(!visited[i][j])count += dfs(i, j, matrix, visited);
			}
		}
		System.out.println(count);
	}
	public static int dfs(int r, int c, int[][] matrix, boolean[][] visited){
		int x = 0;
		visited[r][c] = true;
		if(matrix[r][c] == 1){
			x = 1;
		}
		if(r-1 >= 0 && matrix[r-1][c] != -1 && !visited[r-1][c])x = Math.max(dfs(r-1, c, matrix, visited), x);
		if(r+1 < matrix.length && matrix[r+1][c] != -1 && !visited[r+1][c])x = Math.max(dfs(r+1, c, matrix, visited), x);
		if(c-1 >= 0 && matrix[r][c-1] != -1 && !visited[r][c-1])x = Math.max(dfs(r, c-1, matrix, visited), x);
		if(c+1 < matrix[r].length && matrix[r][c+1] != -1 && !visited[r][c+1])x = Math.max(dfs(r, c+1, matrix, visited), x);
		return x;
	}
}
