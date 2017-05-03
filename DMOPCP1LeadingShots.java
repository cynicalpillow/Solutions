import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCP1LeadingShots {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n1 = Integer.parseInt(s.readLine());
		String y = s.readLine();
		int n2 = Integer.parseInt(s.readLine());
		if(y.equals("Infront")){
			System.out.println(n1-n2);
		} else {
			System.out.println(n1+n2);
		}
	}
}
