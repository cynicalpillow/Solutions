import java.util.*;
import java.math.*;
import java.io.*;
public class COCISlikar {
	private static class Field{
		int r;
		int c;
		public Field(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int[][] floodTimes;
	static int[][] maze;
	static int[] move = {1, -1};
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		maze = new int[r][c];
		floodTimes = new int[r][c];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				floodTimes[i][j] = Integer.MAX_VALUE;
			}
		}
		int startr = 0;
		int startc = 0;
		int endr = 0;
		int endc = 0;
		ArrayList<Field> flooded = new ArrayList<>();
		for(int i = 0; i < r; i++){
			String y = s.readLine();
			for(int j = 0; j < c; j++){
				if(y.charAt(j) == '.'){
					maze[i][j] = 0;
				} else if(y.charAt(j) == 'X'){
					maze[i][j] = -1;
				} else if(y.charAt(j) == 'S'){
					startr = i;
					startc = j;
				} else if(y.charAt(j) == 'D'){
					endr = i;
					endc = j;
					maze[i][j] = -1;
				} else {
					flooded.add(new Field(i, j));
				}
			}
		}
		for(Field f : flooded){
			floodTimes[f.r][f.c] = 0;
			flood(f, flooded);
		}
		bfs(startr, startc, endr, endc, maze, floodTimes);
	}
	public static void flood(Field start, ArrayList<Field> f){
		ArrayDeque<Field> q = new ArrayDeque<>();
		q.add(start);
		while(!q.isEmpty()){
			Field x = q.poll();
			for(int i = 0; i < 2; i++){
				if(x.r + move[i] < maze.length && x.r + move[i] >= 0 && maze[x.r+move[i]][x.c] != -1){
					if(floodTimes[x.r+move[i]][x.c] > floodTimes[x.r][x.c]+1){
						floodTimes[x.r+move[i]][x.c] =floodTimes[x.r][x.c]+1;
						q.add(new Field(x.r+move[i], x.c));
					}
				}
				if(x.c + move[i] < maze[0].length && x.c + move[i] >= 0 && maze[x.r][x.c+move[i]] != -1){
					if(floodTimes[x.r][x.c+move[i]] > floodTimes[x.r][x.c]+1){
						floodTimes[x.r][x.c+move[i]] = floodTimes[x.r][x.c]+1;
						q.add(new Field(x.r, x.c+move[i]));
					}
				}
			}
		}
	}
	public static void bfs(int startr, int startc, int endr, int endc, int[][] maze, int[][] floodTimes){
		ArrayDeque<Integer> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		visited[startr][startc] = true;
		q.add(startr);
		q.add(startc);
		q.add(0);
		int end = -1;
		while(!q.isEmpty()){
			int thisr = q.poll();
			int thisc = q.poll();
			int time = q.poll();
			if(thisr == endr && thisc == endc){
				end = time;
				break;
			}
			for(int i = 0; i < 2; i++){
				if(thisr+move[i] < maze.length && thisr+move[i] >= 0){
					if((thisr+move[i] == endr && thisc == endc)){
						end = time+1;
						break;
					}
					if(maze[thisr+move[i]][thisc] != -1){
						if(time+1 < floodTimes[thisr+move[i]][thisc] && !visited[thisr+move[i]][thisc]){
							q.add(thisr+move[i]);
							q.add(thisc);
							q.add(time+1);
							visited[thisr+move[i]][thisc] = true;
						}
					}
				}
				if(thisc+move[i] < maze[0].length && thisc+move[i] >= 0){
					if(thisr == endr && thisc+move[i] == endc){
						end = time+1;
						break;
					}
					if((maze[thisr][thisc+move[i]] != -1)){
						if(time+1 < floodTimes[thisr][thisc+move[i]] && !visited[thisr][thisc+move[i]]){
							q.add(thisr);
							q.add(thisc+move[i]);
							q.add(time+1);
							visited[thisr][thisc+move[i]] = true;
						}
					}
				}
			}
			if(end != -1)break;
		}
		if(end == -1)System.out.println("KAKTUS");
		else System.out.println(end);
	}
}
