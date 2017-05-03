import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCP2StarStruckSqueeze {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		long d = Long.parseLong(st.nextToken());
		long[] jacks = new long[k];
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for(int i= 0; i < k; i++){
			jacks[i] = 1;
		}
		int count = 0;
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(s.readLine());
			if(st.nextToken().equals("T")){
				q.add(count);
				count++;
			} else {
				long id = Long.parseLong(st.nextToken());
				if(id == 1)continue;
				for(int j : q){
					jacks[j] *= id;
					if(jacks[j] > d)q.remove(Integer.valueOf(j));
				}
			}
		}
		StringBuilder x = new StringBuilder();
		for(int i = 0; i < k; i++){
			if(jacks[i] > d)x.append("dust\n");
			else x.append(jacks[i] + "\n");
		}
		System.out.println(x.toString());
	}
}
