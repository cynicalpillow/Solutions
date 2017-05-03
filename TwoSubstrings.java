import java.util.*;
import java.math.*;
import java.io.*;
public class TwoSubstrings {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		String str = s.readLine();
		boolean ab = false;
		boolean ba = false;
		int i = 1;
		for(i = 1; i < str.length(); i++){
			if(str.charAt(i) == 'B' && str.charAt(i-1) == 'A' && !ab){
				ab = true;
				i++;
				break;
			}
		}
		for(i = i+1;i<str.length(); i++){
			if (str.charAt(i) == 'A' && str.charAt(i-1) == 'B' && !ba){
				ba = true;
				i++;
				break;
			}
		}
		if(ab && ba){
			System.out.println("YES");
			return;
		}
		if(!(ab&&ba)){
			ab = false;
			ba = false;
			i = 1;
			for(i = 1; i < str.length(); i++){
				if(str.charAt(i) == 'A' && str.charAt(i-1) == 'B' && !ab){
					ab = true;
					i++;
					break;
				}
			}
			for(i = i+1;i<str.length(); i++){
				if (str.charAt(i) == 'B' && str.charAt(i-1) == 'A' && !ba){
					ba = true;
					i++;
					break;
				}
			}
		}
		System.out.println((ab && ba)? "YES":"NO");
	}
}
