import java.util.*;
import java.math.*;
import java.io.*;

public class graph3p2 {

	public static void main(String args[]) throws Exception{
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] adj = new int[n+1][n+1];
		for(int i = 1; i < n+1; i++){
			for(int j = 1; j < n+1; j++){
				adj[i][j] = Integer.MIN_VALUE;
			}
		}
		for(int i = 0; i < m; i++){
			st = new StringTokenizer(s.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			adj[x][y] = z;
		}
		shortestPath(adj, 1);
	}
/*	public static void shortestPath(int[][] adj, int s){
		int[] dist = new int[adj.length];
		boolean[] visited = new boolean[adj.length];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[s] = 0;
		for(int i = 1; i < adj.length; i++){
			int min = findMinimum(dist, visited);
			if(min < 1)continue;
			visited[min] = true;
			for(int j = 1; j < adj.length; j++){
				if(adj[min][j] != Integer.MIN_VALUE && !visited[j] && dist[min] != Integer.MAX_VALUE && dist[min]+adj[min][j] < dist[j]){
					dist[j] = dist[min] + adj[min][j];
				}
			}
		}
		System.out.println(dist[adj.length-1]);
	}
	public static int findMinimum(int[] dist, boolean[] visited){
		int index = -1;
		int min = Integer.MAX_VALUE;
		for(int i = 1; i < dist.length; i++){
			if(dist[i] < min && !visited[i]){
				min = dist[i];
				index = i;
			}
		}
		return index;
	}*/
	public static void shortestPath(int[][] adj, int s){
		boolean[] visited = new boolean[adj.length];
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for(int i = 1; i < adj.length; i++){
			if(!visited[i]){
				topologicalSort(stack, adj, visited, s);
			}
		}
		int[] dist = new int[adj.length];
		for(int i = 1; i < adj.length; i++){
			dist[i] = Integer.MAX_VALUE;
		}
		dist[s] = 0;
		while(!stack.isEmpty()){
			int u = stack.poll();
			if(dist[u] != Integer.MAX_VALUE){
				for(int i = 1; i < adj.length; i++){
					if(adj[u][i] != Integer.MIN_VALUE && adj[u][i] + dist[u] < dist[i]){
						dist[i] = adj[u][i] + dist[u];
					}
				}
			}
		}
		System.out.println(dist[adj.length-1]);
	}
	public static void topologicalSort(ArrayDeque<Integer> stack, int[][] adj, boolean[] visited, int i){
		visited[i] = true;
		for(int j = 1; j < adj.length; j++){
			if(!visited[j] && adj[i][j] != Integer.MIN_VALUE){
				topologicalSort(stack, adj, visited, j);
			}
		}
		stack.offerFirst(i);
	}
}
