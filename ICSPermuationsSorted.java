import java.io.*;
public class ICSPermuationsSorted {
	static int count = 0;
	public static void main(String args[]) throws Exception {
		System.out.println("Enter the number of balls (1-26)");
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		while(n > 26){
			System.out.println("Too high, please enter a number between 1-26");
			n = Integer.parseInt(s.readLine());
		}
		for(int i = 0; i < n; i++){
			System.out.println((char)(i+'A'));
			for(int j = 2; j <= n; j++){
				boolean[] visited = new boolean[n];
				String z = String.valueOf((char)(i + 'A'));
				solve(z, i, 1, j, visited);
				System.out.println();
			}
		}
		System.out.println(count);
	}
	//Recursively add letters to the strings, but don't choose the same item. Hence the visited array.
	public static void solve(String i, int id, int csize, int msize, boolean[] visited){
		if(csize == msize){
			count++;
			System.out.print(i + " ");
		} else {
			visited[id] = true;
			//Increase size
			csize++;
			for(int j = 0; j < visited.length; j++){
				if(!visited[j]){
					//Add if not the same letter
					solve(i+String.valueOf((char)(j + 'A')), j, csize, msize, visited);
					//Remove this letter
					visited[j] = false;
				}
			}
		}
	}
}
