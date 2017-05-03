import java.io.*;
public class DMPGMarciaAndMaze {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		int[][] maze = new int[n][n];
		int[][] horizontal = new int[n][n];
		int[][] vertical = new int[n][n];
		for(int i = 0; i < n; i++){
			String z = s.readLine();
			for(int j = 0; j < n; j++){
				if(z.charAt(j) == '#')maze[i][j] = -1;
			}
		}
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(maze[i][j] == -1)horizontal[i][j] = vertical[i][j] = 0;
				else {
					if(j == 0)horizontal[i][j] = 1;
					else horizontal[i][j] = horizontal[i][j-1]+1;
					if(i == 0)vertical[i][j] = 1;
					else vertical[i][j] = vertical[i-1][j]+1;
				}
			}
		}
		int max = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				for(int z = max+1; z < n; z++){
					if(j+z < n && horizontal[i][j+z] > z && i + z < n && vertical[i+z][j] > z && horizontal[i+z][j+z] > z && vertical[i+z][j+z] > z) max=Math.max(max, z);
				}
				if(max == n-1)break;
			}
			if(max == n-1)break;
		}
		System.out.println(max);
	}
}
