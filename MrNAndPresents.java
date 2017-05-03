import java.util.*;
import java.math.*;
import java.io.*;
public class MrNAndPresents {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int q = Integer.parseInt(s.readLine());
		ArrayDeque<Integer> a = new ArrayDeque<>();
		for(int i = 0; i < q; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			String x = st.nextToken();
			if(x.equals("F")){
				a.push(Integer.parseInt(st.nextToken()));
			} else if(x.equals("E")){
				a.add(Integer.parseInt(st.nextToken()));
			} else {
				a.remove(Integer.parseInt(st.nextToken()));
			}
		}
		for(Integer i : a){
			System.out.println(i);
		}
	}
}
