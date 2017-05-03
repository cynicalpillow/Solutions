import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.*;
public class graph2p1 {

	static ArrayList<Integer> connectedComponents = new ArrayList<>();
	
	public static void main(String args[]) throws Exception{
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		int[][] matrix = new int[n+1][n+1];
		for(int i = 1; i < n+1; i++){
			StringTokenizer y = new StringTokenizer(s.readLine());
			for(int j = 1; j < n+1; j++){
				matrix[i][j] = Integer.parseInt(y.nextToken());
			}
		}
		findCC(matrix, n+1);
	}
	public static void findCC(int[][] matrix, int n){
		boolean[] visited = new boolean[n];
		for(int i = 1; i < n; i++){
			connectedComponents.clear();
			if(!visited[i]){
				dfs(matrix, visited, i, n);
			}
			Collections.sort(connectedComponents);
			for(int j : connectedComponents){
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
	public static void dfs(int[][] matrix, boolean[] visited, int i, int n){
		visited[i] = true;
		for(int j = 1; j < n; j++){
			if(!visited[j] && matrix[i][j] != 0){
				dfs(matrix, visited, j, n);
			}
		}
		connectedComponents.add(i);
	}
}
