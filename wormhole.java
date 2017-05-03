/*
ID: cynical
LANG: JAVA
TASK: wormhole
*/
import java.util.*;
import java.math.*;
import java.io.*;
public class wormhole {
	private static class Coordinate{
		int x;
		int y;
		public Coordinate(int i, int j){
			x = i;
			y = j;
		}
	}
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("wormhole.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
		int n = Integer.parseInt(s.readLine());
		Coordinate[] coords = new Coordinate[n];
		for(int i = 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			coords[i] = new Coordinate(x, y);
		}
		out.close();
	}
	public static boolean check(Coordinate one, Coordinate two, Coordinate three, Coordinate four){
		return true;
	}
}
