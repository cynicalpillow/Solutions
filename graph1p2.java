import java.util.*;
import java.math.*;

public class graph1p2 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[][] matrix = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				matrix[i][j] = s.nextInt();
			}
		}
		djikstra(matrix, n);
	}
	public static void djikstra(int[][] matrix, int n){
		boolean[] visited = new boolean[n];
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		for(int j = 0; j < n-1; j++){
			int s = findminimum(dist, visited);
			if(s == -1)continue;
			for(int i = 0; i < n; i++){
				if(i != s && matrix[s][i] != 0 && dist[s] != Integer.MAX_VALUE && dist[s] + matrix[s][i] < dist[i]){
					dist[i] = dist[s] + matrix[s][i];
				}
			}
			visited[s] = true;
		}
		if(dist[n-1] == Integer.MAX_VALUE)System.out.println(-1);
		else System.out.println(dist[n-1]);
	}
	public static int findminimum(int[] dist, boolean[] visited){
		int index = -1;
		int max = Integer.MAX_VALUE;
		for(int i = 0; i < dist.length; i++){
			if(!visited[i] && max > dist[i]){
				index = i;
				max = dist[i];
			}
		}
		return index;
	}
}
