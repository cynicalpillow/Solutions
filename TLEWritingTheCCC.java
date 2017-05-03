import java.util.*;
import java.math.*;
import java.io.*;
public class TLEWritingTheCCC {
	private static class Data implements Comparable<Data>{
		String x;
		int idx;
		int priority;
		public Data(String y, int id){
			idx = id;
			x = y;
			priority = map.get(y);
		}
		@Override
		public int compareTo(Data a) {
			if(this.priority < a.priority)return -1;
			else if(this.priority > a.priority)return 1;
			else {
				if(this.idx < a.idx)return -1;
				else if(this.idx > a.idx)return 1;
				else return 0;
			}
		}
	}
	static HashMap<String, Integer> map;
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		map = new HashMap<>();
		int t = Integer.parseInt(s.readLine());
		for(int i = 0; i < t; i++){
			String z = s.readLine();
			map.put(z, i);
		}
		int n = Integer.parseInt(s.readLine());
		Data[] d = new Data[n];
		for(int j = 0; j < n; j++){
			String z = s.readLine();
			d[j] = new Data(z, j+1);
		}
		Arrays.sort(d);
		for(int i = 0; i < n; i++){
			System.out.println(d[i].idx);
		}
	}
}
