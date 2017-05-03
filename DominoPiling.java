import java.util.*;
import java.math.*;
import java.io.*;
public class DominoPiling {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int vert = 0;
		int hor = (m/2)*n;
		if(m%2 == 1){
			vert = (n/2);
		}
		System.out.println(vert + hor);
	}
}
