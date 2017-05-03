import java.util.*;
import java.math.*;
import java.io.*;
public class MWCFrenchWords {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(s.readLine());
		ArrayList<String> one = new ArrayList<>();
		for(int i = 0; i < x; i++){
			one.add(st.nextToken());
		}
		st = new StringTokenizer(s.readLine());
		String[] two = new String[y];
		for(int i = 0; i < y; i++){
			two[i] = st.nextToken();
		}
		HashSet<String> found = new HashSet<>();
		for(int i = 0; i < y; i++){
			if(Collections.binarySearch(one, two[i]) >= 0){
				found.add(two[i]);
			}
		}
		System.out.println(found.size());
	}
}
