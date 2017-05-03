import java.util.*;
import java.io.*;
public class DMOPCCarnivalPhantasm {
	public static class Stand implements Comparable<Stand>{
		int k;
		int id;
		ArrayList<Integer> ap = new ArrayList<>();
		public Stand(int k, int i){
			this.k = k;
			id = i;
		}
		@Override
		public int compareTo(Stand o) {
			if(this.k < o.k)return -1;
			else if(this.k > o.k)return 1;
			else {
				if(this.id < o.id)return -1;
				else if(this.id > o.id)return 1;
				return 0;
			}
		}
	}
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int sells = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(s.readLine());
		Stand[] stands = new Stand[n];
		ArrayList<PriorityQueue<Stand>> map = new ArrayList<>();
		for(int i = 0; i < n; i++)stands[i] = new Stand(Integer.parseInt(st.nextToken()), i+1);
		for(int i = 0; i <= 100; i++) map.add(new PriorityQueue<>());
		for(int i = 0; i < sells; i++){
			st = new StringTokenizer(s.readLine());
			int sx = Integer.parseInt(st.nextToken())-1;
			int ap = Integer.parseInt(st.nextToken());
			map.get(ap).add(stands[sx]);
			stands[sx].ap.add(ap);
		}
		int q = Integer.parseInt(s.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < q; i++){
			st = new StringTokenizer(s.readLine());
			String z = st.nextToken();
			if(z.equals("A")){
				int x = Integer.parseInt(st.nextToken())-1;
				int ap = Integer.parseInt(st.nextToken());
				map.get(ap).add(stands[x]);
				stands[x].ap.add(new Integer(ap));
			} else if(z.equals("S")){
				int x = Integer.parseInt(st.nextToken())-1;
				int ap = Integer.parseInt(st.nextToken());
				map.get(ap).remove(stands[x]);
				stands[x].ap.remove(new Integer(ap));
			} else if(z.equals("E")){
				int x = Integer.parseInt(st.nextToken())-1;
				int k = Integer.parseInt(st.nextToken());
				for(Integer j : stands[x].ap){
					map.get(j).remove(stands[x]);
				}
				stands[x].k = k;
				stands[x].ap = new ArrayList<>();
			} else if(z.equals("Q")){
				int k = Integer.parseInt(st.nextToken());
				if(map.get(k).size() > 0){
					sb.append(map.get(k).peek().id + "\n");
				} else sb.append(-1 + "\n");
			}
		}
		System.out.println(sb.toString());
	}
}
