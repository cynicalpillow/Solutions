import java.util.*;
import java.math.*;
import java.io.*;

public class ioi1102 {

	public static void main(String args[]) throws Exception{
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		ArrayList<Integer> a = new ArrayList<>();
		int total = 0;
		for(int i = 0; i < k; i++){
			int x = Integer.parseInt(s.readLine());
			total+=x;
			a.add(x);
		}
		int max = total;
		for(int i = k; i < n; i++){
			total -= a.get(0);
			a.remove(0);
			int x = Integer.parseInt(s.readLine());
			total+=x;
			a.add(x);
			max = Math.max(total, max);
		}
		System.out.println(max);
	}
	
}
