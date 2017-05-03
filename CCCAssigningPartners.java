import java.util.*;
import java.math.*;
import java.io.*;
public class CCCAssigningPartners {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		StringTokenizer st = new StringTokenizer(s.readLine());
		HashMap<String, String> map = new HashMap<>();
		String[] people = new String[n];
		for(int i = 0; i < n; i++){
			people[i] = st.nextToken();
		}
		st = new StringTokenizer(s.readLine());
		String[] assign = new String[n];
		boolean check = true;
		for(int i = 0; i < n; i++){
			assign[i] = st.nextToken();
		}
		for(int i = 0; i < n; i++){
			if(people[i].equals(assign[i])){
				check = false;
				break;
			} else {
				if(!map.containsKey(people[i]) && !map.containsKey(assign[i])){
					map.put(people[i], assign[i]);
					map.put(assign[i], people[i]);
				} else {
					if(!map.get(people[i]).equals(assign[i])){
						check = false;
						break;
					}
				}
			}
		}
		if(check)System.out.println("good");
		else System.out.println("bad");
	}
}
