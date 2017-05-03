import java.util.*;
import java.math.*;
import java.io.*;
public class DivisibilityByEight {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		String str = s.readLine();
		boolean check = false;
		BigInteger num = BigInteger.ZERO;
		if(new BigInteger(str).mod(new BigInteger("8")) == BigInteger.ZERO){
			System.out.println("YES");
			System.out.println(str);
			return;
		}
		if(str.length() == 2){
			if(Integer.parseInt(str) % 8 == 0){
				System.out.println("YES");
				System.out.println(Integer.parseInt(str));
			} else {
				StringBuilder y = new StringBuilder(str);
				y.delete(0, 1);
				if(Integer.parseInt(y.toString()) % 8 == 0){
					System.out.println("YES");
					System.out.println(Integer.parseInt(y.toString()));
				} else {
					y = new StringBuilder(str);
					y.delete(1, 2);
					if(Integer.parseInt(y.toString()) % 8 == 0){
						System.out.println("YES");
						System.out.println(Integer.parseInt(y.toString()));
					} else {
						System.out.println("NO");
					}
				}
			}
			return;
		}
		for(int i = 1; i < str.length()-1; i++){
			for(int j = 0; j < str.length(); j++){
				StringBuilder y = new StringBuilder(str);
				y.delete(j, j+i);
				BigInteger x = new BigInteger(y.toString());
				if(y.charAt(0) != '0' && x.mod(new BigInteger("8")) == BigInteger.ZERO){
					check = true;
					num = x;
					break;
				} else {
					continue;
				}
			}
			if(check)break;
		}
		if(check){
			System.out.println("YES");
			System.out.println(num);
		} else {
			System.out.println("NO");
		}
	}
}
