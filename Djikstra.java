import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Djikstra {
	private static class Node implements Comparable<Node>{
		int n;
		int dist;
		public Node(int n){
			this.n = n;
			dist = 0;
		}
		public int compareTo(Node node){
			if(this.dist < node.dist)return -1;
			if(this.dist > node.dist)return 1;
			return 0;
		}
	}
    public static void main(String args[] ) throws Exception {
        BufferedReader s = new BufferedReader(new FileReader(new File("C:/Users/Rui/Desktop/Developing/input.txt")));
        StringTokenizer st = new StringTokenizer(s.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Node[] nodes = new Node[n+1];
        for(int i = 1; i < n+1; i++){
        	nodes[i] = new Node(i);
        }
        int[][] matrix = new int[n+1][n+1];
        for(int i = 0; i < m; i++){
        	String line = s.readLine();
        	if(line == null)break;
        	st = new StringTokenizer(line);
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	int z = Integer.parseInt(st.nextToken());
        	if(matrix[x][y] != 0){
        		matrix[x][y] = Math.min(z, matrix[x][y]);
        		matrix[y][x] = Math.min(z, matrix[y][x]);
        	} else {
        		matrix[x][y] = z;
        		matrix[y][x] = z;
        	}
        }
        djikstra(matrix, 1, nodes);
    }
    public static void djikstra(int[][] matrix, int start, Node[] nodes){
    	int[] dist = new int[matrix.length];
    	boolean[] visited = new boolean[matrix.length];
    	for(int i = 1; i < dist.length; i++){
    		dist[i] = Integer.MAX_VALUE;
    	}
    	PriorityQueue<Node> q = new PriorityQueue<>();
    	dist[start] = 0;
    	q.add(nodes[start]);
    	while(!q.isEmpty()){
    		Node min = q.poll();
    		if(visited[min.n]){
    			continue;
    		}
    		visited[min.n]= true;
    		for(int j = 1; j < dist.length; j++){
    			if(matrix[min.n][j] != 0 && dist[j] > matrix[min.n][j] + dist[min.n]){
    				dist[j] = matrix[min.n][j] + dist[min.n];
    				nodes[j].dist = matrix[min.n][j] + dist[min.n];
    				q.add(nodes[j]);
    			}
    		}
    	}
    	for(int x = 2; x < nodes.length; x++){
    		if(nodes[x].dist == 0)System.out.print("1000000000" + " ");
    		else System.out.print(nodes[x].dist+ " ");
    	}
    }
}
