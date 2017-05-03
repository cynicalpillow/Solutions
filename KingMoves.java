import java.util.*;
import java.math.*;
import java.io.*;
public class KingMoves {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		String st = s.readLine();
		int x = st.charAt(0) - 'a';
		int y = Integer.parseInt(st.substring(1, 2))-1;
		int total = 8;
		if((x == 0 || x == 7) && (y == 0 || y == 7))total-=5;
		else if((x == 0 || x == 7) || (y == 0 || y == 7))total-=3;
		System.out.println(total);
	}
}
