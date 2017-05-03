import java.util.*;

public class graph2p2 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[][] matrix = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				matrix[i][j] = s.nextInt();
			}
		}
		boolean check = false;
		for(int i = 0; i < n; i++){
			if(dfs(matrix, i)){
				check = true;
				continue;
			} else {
				check = false;
			}
		}
		if(!check)System.out.println("YES");
		else System.out.println("NO");
	}
	public static boolean dfs(int[][] matrix, int start){
		Stack<Integer> s = new Stack<>();
		s.add(start);
		boolean[] visited = new boolean[matrix.length];
		while(!s.isEmpty()){
			int n = s.pop();
			for(int i = 0; i < matrix.length; i++){
				if(!visited[i] && matrix[n][i] != 0){
					s.add(i);
				} else if(visited[i] && matrix[n][i] != 0){
					return true;
				}
			}
			visited[n] = true;
		}
		return false;
	}
}
