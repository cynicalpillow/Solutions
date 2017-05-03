import java.util.*;
import java.math.*;
import java.io.*;
public class Speech {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		HashMap<String, String> map = new HashMap<>();
		for(int i = 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			map.put(st.nextToken(), st.nextToken());
		}
		String result = s.readLine();
		result = result.substring(0, result.length()-1);
		for(Map.Entry<String, String> entry : map.entrySet()){
			StringTokenizer st = new StringTokenizer(result);
			StringBuilder x = new StringBuilder();
			while(st.hasMoreTokens()){
				String z  = st.nextToken();
				if(z.equals(entry.getValue())) x.append(entry.getKey());
				else x.append(z);
				x.append(" ");
			}
			result = x.toString().trim();
		}
		System.out.println(result + ".");
	}
}
