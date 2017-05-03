import java.util.*;
import java.math.*;
import java.io.*;
public class StringTask {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		String str = s.readLine();
		StringBuilder sb = new StringBuilder();
		sb.append('.');
		for(int i= 0; i < str.length(); i++){
			if(str.charAt(i) != 'A' && str.charAt(i) != 'a' && str.charAt(i) != 'O' && str.charAt(i) != 'o' && str.charAt(i) != 'U' && str.charAt(i) != 'u' && str.charAt(i) != 'Y' && str.charAt(i) != 'y' && str.charAt(i) != 'E' && str.charAt(i) != 'e' && str.charAt(i) != 'I' && str.charAt(i) != 'i'){
				sb.append(str.charAt(i));
				sb.append('.');
			}
		}
		if(sb.charAt(sb.length()-1) == '.')sb.delete(sb.length()-1, sb.length());
		System.out.println(sb.toString().toLowerCase());
	}
}
