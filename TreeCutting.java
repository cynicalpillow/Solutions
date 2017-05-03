import java.util.*;
import java.math.*;
import java.io.*;
public class TreeCutting {
	private static class State implements Comparable<State>{
		int count;
		int dist;
		int r;
		int c;
		public State(int x, int y, int d, int co){
			r = x;
			c = y;
			dist = d;
			count = co;
		}
		@Override
		public int compareTo(State a) {
			if(this.dist < a.dist)return -1;
			else if(this.dist > a.dist)return 1;
			return 0;
		}
	}
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[][] grid = new int[r][c];
		int startr = -1;
		int startc = -1;
		int largest = Integer.MIN_VALUE;
		int minr = 0;
		int minc = 0;
		int mindist = Integer.MAX_VALUE;
		for(int i= 0; i < r; i++){
			st = new StringTokenizer(s.readLine());
			for(int j = 0; j < c; j++){
				String z = st.nextToken();
				if(z.equals("."))grid[i][j] = 0;
				else if(z.equals("X")){
					grid[i][j] = 0;
					startr = i;
					startc = j;
				} else {
					grid[i][j] = Integer.parseInt(z);
					largest  = Math.max(largest, grid[i][j]);
				}
			}
		}
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(grid[i][j] == largest){
					if(Math.sqrt(Math.pow(i-startr, 2) + Math.pow(j-startc, 2)) < mindist){
						mindist = (int)Math.sqrt(Math.pow(i-startr, 2) + Math.pow(j-startc, 2));
						minr = i;
						minc = j;
					}
				}
			}
		}
		dijkstra(grid, r, c, startr, startc, minr, minc);
	}
	public static void dijkstra(int[][] grid, int r, int c, int startr, int startc, int minr, int minc){
		PriorityQueue<State> q = new PriorityQueue<>();
		int[][] dists = new int[r][c];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++)dists[i][j] = Integer.MAX_VALUE;
		}
		dists[startr][startc] = 0;
		int leastNum = Integer.MAX_VALUE;
		int leastCount = Integer.MAX_VALUE;
		q.add(new State(startr, startc, 0, 0));
		while(!q.isEmpty()){
			State s = q.poll();
			if(s.r == minr && s.c == minc){
				s.dist -= grid[s.r][s.c];
				s.count -= 1;
				if(s.dist < leastNum){
					leastNum = s.dist;
					leastCount = s.count;
				} else if(s.dist == leastNum)leastCount = Math.min(leastCount, s.count);
			}
			if(s.r + 1 < r && dists[s.r+1][s.c] > s.dist + grid[s.r+1][s.c]){
				dists[s.r+1][s.c] = s.dist + grid[s.r+1][s.c];
				if(grid[s.r+1][s.c] != 0)q.add(new State(s.r + 1, s.c, dists[s.r+1][s.c], s.count+1));
				else q.add(new State(s.r + 1, s.c, dists[s.r+1][s.c], s.count));
			}
			if(s.r - 1 >= 0 && dists[s.r-1][s.c] > s.dist + grid[s.r-1][s.c]){
				dists[s.r-1][s.c] = s.dist + grid[s.r-1][s.c];
				if(grid[s.r-1][s.c] != 0)q.add(new State(s.r - 1, s.c, dists[s.r-1][s.c], s.count+1));
				else q.add(new State(s.r - 1, s.c, dists[s.r-1][s.c], s.count));
			}
			if(s.c + 1 < c && dists[s.r][s.c+1] > s.dist + grid[s.r][s.c+1]){
				dists[s.r][s.c+1] = s.dist + grid[s.r][s.c+1];
				if(grid[s.r][s.c+1] != 0)q.add(new State(s.r, s.c+1, dists[s.r][s.c+1], s.count+1));
				else q.add(new State(s.r, s.c+1, dists[s.r][s.c+1], s.count));
			}
			if(s.c - 1 >= 0 && dists[s.r][s.c-1] > s.dist + grid[s.r][s.c-1]){
				dists[s.r][s.c-1] = s.dist + grid[s.r][s.c-1];
				if(grid[s.r][s.c-1] != 0)q.add(new State(s.r, s.c-1, dists[s.r][s.c-1], s.count+1));
				else q.add(new State(s.r, s.c-1, dists[s.r][s.c-1], s.count));
			}
		}
		System.out.println(leastCount);
	}
}
