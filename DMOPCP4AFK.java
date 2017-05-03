import java.util.*;
import java.io.*;
public class DMOPCP4AFK {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		for(int i = 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int[][] a = new int[r][c];
			int startr = -1;
			int startc = -1;
			for(int j = 0; j < r; j++){
				String y = s.readLine();
				for(int z = 0; z < c; z++){
					if(y.charAt(z) == 'C'){
						startr = j;
						startc = z;
						a[j][z] = -1;
					} else if(y.charAt(z) == 'W'){
						a[j][z] = 1;
					} else if(y.charAt(z) == 'X'){
						a[j][z] = -1;
					}
				}
			}
			bfs(startr, startc, a, r, c);
		}
	}
	public static void bfs(int startr, int startc, int[][] a, int r, int c){
		ArrayDeque<Integer> rs = new ArrayDeque<>();
		rs.add(startr);
		rs.add(startc);
		int finaldist = Integer.MAX_VALUE;
		int[][] dists = new int[r][c];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				dists[i][j] = Integer.MAX_VALUE;
			}
		}
		dists[startr][startc] = 0;
		while(!rs.isEmpty()){
			int rcurr = rs.poll();
			int ccurr = rs.poll();
			int dist = dists[rcurr][ccurr];
			if(a[rcurr][ccurr] == -1){
				if(rcurr == startr && ccurr == startc){
				} else continue;
			}
			if(dist >= 60)continue;
			if(a[rcurr][ccurr] == 1){
				finaldist = Math.min(dist, finaldist);
				break;
			}
			if(dist+ 1 < 60 && rcurr + 1 < r && dists[rcurr+1][ccurr] > dist+1){
				rs.add(rcurr+1); rs.add(ccurr); dists[rcurr+1][ccurr] = Math.min(dist+1, dists[rcurr+1][ccurr]);
			}
			if(dist+ 1 < 60 && rcurr - 1 >= 0 && dists[rcurr-1][ccurr] > dist+1){
				rs.add(rcurr-1); rs.add(ccurr); dists[rcurr-1][ccurr] = Math.min(dist+1,dists[rcurr-1][ccurr]);
			}
			if(dist+ 1 < 60 && ccurr + 1 < c && dists[rcurr][ccurr+1] > dist+1){
				rs.add(rcurr); rs.add(ccurr+1); dists[rcurr][ccurr+1] = Math.min(dist+1, dists[rcurr][ccurr+1]);
			}
			if(dist+ 1 < 60 && ccurr - 1>=0 && dists[rcurr][ccurr-1] > dist+1){
				rs.add(rcurr); rs.add(ccurr-1); dists[rcurr][ccurr-1] = Math.min(dist+1, dists[rcurr][ccurr-1]);
			}
		}
		if(finaldist == Integer.MAX_VALUE)System.out.println("#notworth");
		else System.out.println(finaldist);
	}
}
