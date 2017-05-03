import java.util.*;
import java.math.*;
import java.io.*;
public class CCCComputerPurchase{
	private static class C implements Comparable<C>{
		String id;
		long total;
		public C(String i, long t){
			id = i;
			total = t;
		}
		@Override
		public int compareTo(C o) {
			if(this.total > o.total)return -1;
			else if(this.total < o.total)return 1;
			else {
				if(this.id.compareTo(o.id) < 0)return -1;
				else if(this.id.compareTo(o.id) > 0)return 1;
				else return 0;
			}
		}
	}
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		C[] c = new C[n];
		if(n == 0)return;
		for(int i= 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			String id = st.nextToken();
			long r = Long.parseLong(st.nextToken());
			long ss = Long.parseLong(st.nextToken());
			long d = Long.parseLong(st.nextToken());
			long total = 2*r + 3*ss + d;
			c[i] = new C(id, total);
		}
		Arrays.sort(c);
		if(n >= 1)System.out.println(c[0].id);
		if(n >= 2)System.out.println(c[1].id);
	}
}
