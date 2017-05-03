import java.util.*;
import java.math.*;
import java.io.*;
public class CCCFix {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		for(int i = 0; i < n; i++){
			String a = s.readLine();
			String b = s.readLine();
			String c = s.readLine();
			if(a.startsWith(b) || a.startsWith(c) || b.startsWith(a) || b.startsWith(c) || c.startsWith(a) || c.startsWith(b)){
				System.out.println("No");
			} else {
				if(a.endsWith(b) || a.endsWith(c) || b.endsWith(a) || b.endsWith(c) || c.endsWith(a) || c.endsWith(b)){
					System.out.println("No");
				} else {
					System.out.println("Yes");
				}
			}
		}
	}
}
