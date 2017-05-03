import java.util.*;
import java.math.*;
import java.io.*;
public class VMSSGGString {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		String x = s.readLine();
		char[] y = x.toCharArray();
		int[] gs = new int[x.length()];
		if(y[0] == 'G')gs[0] = 1;
		else gs[0] = 0;
		for(int i = 1; i  < y.length; i++){
			if(y[i] == 'G')gs[i] = gs[i-1] + 1;
			else gs[i] = gs[i-1];
		}
		int n = Integer.parseInt(s.readLine());
		for(int i = 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			int low = Integer.parseInt(st.nextToken());
			int high = Integer.parseInt(st.nextToken());
			if(low == 0)System.out.println(gs[high]);
			else System.out.println(gs[high] - gs[low-1]);
		}
	}
}
