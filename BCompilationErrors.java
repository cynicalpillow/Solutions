import java.util.*;
import java.math.*;
import java.io.*;
public class BCompilationErrors {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(s.readLine());
		StringTokenizer st = new StringTokenizer(s.readLine());
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < n; i++){
			int x =Integer.parseInt(st.nextToken());
			if(map.containsKey(x)){
				map.put(x, map.get(x)+1);
			} else {
				map.put(x, 1);
			}
		}
		for(int i = 0; i <= 1; i++){
			st = new StringTokenizer(s.readLine());
			HashMap<Integer, Integer> a = new HashMap<>();
			int y = st.countTokens();
			for(int j = 0; j < y; j++){
				int x = Integer.parseInt(st.nextToken());
				if(a.containsKey(x)){
					a.put(x, a.get(x)+1);
				} else {
					a.put(x, 1);
				}
			}
			int missing = 0;
			for(Map.Entry<Integer, Integer> entry : map.entrySet()){
				if(!a.containsKey(entry.getKey())){
					missing = entry.getKey();
					break;
				} else {
					if(a.get(entry.getKey()) < entry.getValue()){
						missing = entry.getKey();
						break;
					}
				}
			}
			map = a;
			System.out.println(missing);
		}
	}
}
