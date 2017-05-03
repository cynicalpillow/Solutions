import java.util.*;
import java.math.*;
import java.io.*;
public class MNYCAsciiArt {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		HashSet<Character> colorchange = new HashSet<>();
		int c = 0;
		for(int i = 0; i < y; i++){
			String z = s.readLine();
			for(int j = 0; j < x; j++){
				if(z.charAt(j) != ' '){
					c++;
					if(z.charAt(j) != '.')colorchange.add(z.charAt(j));
				}
			}
		}
		System.out.println(c + colorchange.size());
	}
}
