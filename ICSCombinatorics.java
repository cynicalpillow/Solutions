import java.io.*;
public class ICSCombinatorics {
	public static void main(String args[]) throws Exception {
		System.out.println("Enter the number of balls (1-26)");
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		while(n > 26){
			System.out.println("Too high, please enter a number between 1-26");
			n = Integer.parseInt(s.readLine());
		}
		for(int i = 0; i < n; i++){
			boolean[] visited = new boolean[n];
			solve(String.valueOf((char)(i + 'A')), i, 1, n, visited);
			System.out.println();
		}
	}
	//Recursively add letters to the strings, but don't choose the same item. Hence the visited array.
	public static void solve(String i, int id, int csize, int msize, boolean[] visited){
		System.out.println(i);
		if(csize < msize){
			visited[id] = true;
			csize++;
			for(int j = 0; j < visited.length; j++){
				if(!visited[j]){
					solve(i+String.valueOf((char)(j + 'A')), j, csize, msize, visited);
					visited[j] = false;
				}
			}
		}
	}
}
