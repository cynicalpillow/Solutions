/*
ID: cynical
LANG: JAVA
TASK: beads
*/
import java.io.*;
import java.util.*;
import java.math.*;

public class beads {

	public static void main(String args[]) throws IOException{
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader s = new BufferedReader(new FileReader("beads.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
		int n = Integer.parseInt(s.readLine());
		String y = s.readLine();
		int count = 0;
		for(int i = 0; i < n; i++){
			boolean[] visited = new boolean[n];
			char foundOne = 'w';
			int curr = 0;
			int j = i;
			while(true){
				if(j == -1)j = n-1;
				if(visited[j])break;
				if(y.charAt(j) == 'w'){
					curr++;
				} else if (y.charAt(j) != 'w' && foundOne == 'w'){
					foundOne = y.charAt(j);
					curr++;
				} else if (foundOne != 'w' && y.charAt(j) != 'w' && y.charAt(j) != foundOne){
					break;
				} else {
					curr++;
				}
				visited[j] = true;
				j--;
			}
			j = i+1;
			foundOne = 'w';
			while(true){
				if(j == n)j = 0;
				if(visited[j])break;
				if(y.charAt(j) == 'w'){
					curr++;
				} else if (y.charAt(j) != 'w' && foundOne == 'w'){
					foundOne = y.charAt(j);
					curr++;
				} else if (foundOne != 'w' && y.charAt(j) != 'w' && y.charAt(j) != foundOne){
					break;
				} else {
					curr++;
				}
				visited[j] = true;
				j++;
			}
			count = Math.max(curr, count);
		}
		out.println(count);
		out.close();
	}
	
}
