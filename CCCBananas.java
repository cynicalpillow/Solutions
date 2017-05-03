import java.util.*;
import java.math.*;
import java.io.*;
public class CCCBananas {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		String y = s.readLine();
		while(!y.equals("X")){
			if(recurse(y))System.out.println("YES");
			else System.out.println("NO");
			y = s.readLine();
		}
	}
	public static boolean aword(String y){
		if(y.equals("A"))return true;
		else {
			if(y.charAt(0) == 'B' && y.length() >= 3 && y.charAt(y.length()-1) == 'S' && recurse(y.substring(1, y.length()-1))){
				return true;
			}
		}
		return false;
	}
	public static boolean recurse(String y){
		if(aword(y)){
			return true;
		}
		boolean f = false;
		for(int i = 1; i < y.length()-1; i++){
			if(y.charAt(i) == 'N'){
				boolean z = aword(y.substring(0, i));
				boolean x = recurse(y.substring(i+1, y.length()));
				if(f || (z && x)){
					f = true;
				}
			}
		}
		return f;
	}
}
