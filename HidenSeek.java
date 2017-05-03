import java.util.*;
import java.math.*;
import java.io.*;
public class HidenSeek {
	private static class Point implements Comparable<Point>{
		int y;
		int x;
		int id;
		int dist = Integer.MAX_VALUE;
		public Point(int z, int c, int id){
			y = z;
			x= c;
			this.id = id;
		}
		@Override
		public int compareTo(Point arg0) {
			if(this.dist < arg0.dist)return -1;
			else if(this.dist > arg0.dist)return 1;
			return 0;
		}
	}
	private static int currDist = 0;
	private static int minDist = Integer.MAX_VALUE;
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int[][] a = new int[n][m];
		Point[] points = new Point[t+1];
		int curr = 1;
		for(int i = 0; i < n; i++){
			String y = s.readLine();
			for(int j = 0; j < y.length(); j++){
				if(y.charAt(j) == 'G'){
					points[0] = new Point(i, j, 0);
					a[i][j] = -1;
				}
				if(y.charAt(j) == 'X')a[i][j] = -1;
				if(y.charAt(j) == 'H'){
					points[curr++] = new Point(i, j, curr-1);
					a[i][j] = 1;
				}
			}
		}
		points[0].dist = 0;
		boolean[] visited = new boolean[t+1];
		visited[0] = true;
		searchPermutations(0, points, a, n, m, visited, 0);
		System.out.println(minDist);
	}
	public static void searchPermutations(int x, Point[] points, int[][] a, int n, int m, boolean[] visited, int c){
		if(c == points.length-1){
			minDist = Math.min(currDist, minDist);
			return;
		}
		for(int i = 0; i < points.length; i++){
			if(!visited[i]){
				visited[i] = true;
				int z = bfs(points[x].y, points[x].x, i, points, a, n, m, new boolean[a.length][a[0].length]);
				currDist += z;
				searchPermutations(i, points, a, n, m, visited, c+1);
				visited[i] = false;
				currDist -= z;
			}
		}
	}
	public static int bfs(int y, int x, int i, Point[] points, int[][] a, int n, int m, boolean[][] visited){
		ArrayDeque<Integer> yvals = new ArrayDeque<>();
		ArrayDeque<Integer> xvals = new ArrayDeque<>();
		ArrayDeque<Integer> distances = new ArrayDeque<>();
		yvals.add(y);
		xvals.add(x);
		int[][] dists = new int[n][m];
		for(int z = 0; z< n; z++){
			for(int j = 0; j < m; j++){
				dists[z][j] = Integer.MAX_VALUE;
			}
		}
		dists[y][x] = 0;
		distances.add(0);
		while(!yvals.isEmpty() && !xvals.isEmpty()){
			int idx = xvals.poll();
			int idy = yvals.poll();
			if(a[idy][idx] == -1 && (idy != points[0].y || idx != points[0].x)){
				distances.poll();
				continue;
			}
			visited[idy][idx] = true;
			dists[idy][idx] = Math.min(distances.poll()+1, dists[idy][idx]);
			if(idx == points[i].x && idy == points[i].y)break;
			if(idy+1 <= n-1 && !visited[idy+1][idx]){
				yvals.add(idy+1); xvals.add(idx); distances.add(dists[idy][idx]);
			}
			if(idy - 1 >= 0 && !visited[idy-1][idx]){
				yvals.add(idy-1); xvals.add(idx); distances.add(dists[idy][idx]);
			}
			if(idx + 1 <= m-1 && !visited[idy][idx+1]){
				yvals.add(idy); xvals.add(idx+1); distances.add(dists[idy][idx]);
			}
			if(idx - 1 >= 0 && !visited[idy][idx-1]){
				yvals.add(idy); xvals.add(idx-1); distances.add(dists[idy][idx]);
			}
		}
		return dists[points[i].y][points[i].x];
	}
}
