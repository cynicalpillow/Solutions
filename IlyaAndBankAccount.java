import java.util.*;
import java.math.*;
import java.io.*;
public class IlyaAndBankAccount {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(s.readLine());
		if(n > 0)System.out.println(n);
		else {
			String x = String.valueOf(n);
			int max = Math.max(Integer.parseInt(x.substring(0, x.length()-1)), Integer.parseInt(x.substring(0, x.length()-2) + x.substring(x.length()-1, x.length())));
			System.out.println(max);
		}
	}
}
