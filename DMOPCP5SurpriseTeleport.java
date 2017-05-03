import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCP5SurpriseTeleport {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(s.readLine());
		int startr = Integer.parseInt(st.nextToken());
		int startc = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(s.readLine());
		int endr = Integer.parseInt(st.nextToken());
		int endc = Integer.parseInt(st.nextToken());
		int[][] arr = new int[r][c];
		for(int i= 0; i < r; i++){
			String y = s.readLine();
			for(int j = 0; j < c; j++){
				if(y.charAt(j) == 'X')arr[i][j] = -1;
			}
		}
		int t = Integer.parseInt(s.readLine());
		int[] rs = new int[t];
		int[] cs = new int[t];
		for(int i = 0; i < t; i++){
			st = new StringTokenizer(s.readLine());
			int z = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			rs[i] = z;
			cs[i] = x;
		}
		int z = bfs(startr, startc, endr, endc, arr);
		for(int i = 0; i < t; i++)arr[rs[i]][cs[i]] = 1;
		int x = bfs(startr, startc, endr, endc, arr);
		if(x < z)System.out.println(z-x);
		else System.out.println(0);
	}
	public static int bfs(int startr, int startc, int endr, int endc, int[][] a){
		ArrayDeque<Integer> r = new ArrayDeque<>();
		ArrayDeque<Integer> c = new ArrayDeque<>();
		ArrayDeque<Integer> count = new ArrayDeque<>();
		boolean[][] visited = new boolean[a.length][a[0].length];
		visited[startr][startc] = true;
		r.add(startr);
		c.add(startc);
		count.add(0);
		int shortest = 0;
		while(!r.isEmpty()){
			int rx = r.poll();
			int cx = c.poll();
			int dist = count.poll();
			if((rx == endr && cx == endc) || (a[rx][cx] == 1)){
				shortest = dist;
				break;
			}
			if(rx + 1 < a.length && !visited[rx+1][cx] && a[rx+1][cx] != -1){
				r.add(rx+1);
				c.add(cx);
				count.add(dist+1);
				visited[rx+1][cx] = true;
			}
			if(rx - 1 >= 0 && !visited[rx-1][cx] && a[rx-1][cx] != -1){
				r.add(rx-1);
				c.add(cx);
				count.add(dist+1);
				visited[rx-1][cx] = true;
			}
			if(cx + 1 < a[rx].length && !visited[rx][cx+1] && a[rx][cx+1] != -1){
				r.add(rx);
				c.add(cx+1);
				count.add(dist+1);
				visited[rx][cx+1] = true;
			}
			if(cx - 1 >= 0 && !visited[rx][cx-1] && a[rx][cx-1] != -1){
				r.add(rx);
				c.add(cx-1);
				count.add(dist+1);
				visited[rx][cx-1] = true;
			}
		}
		return shortest;
	}
}
