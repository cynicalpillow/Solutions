/*
ID: cynical
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.*;
import java.math.*;
public class crypt1 {
	public static void main(String args[]) throws Exception{
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader s = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
		int n = Integer.parseInt(s.readLine());
		StringTokenizer st = new StringTokenizer(s.readLine());
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int i = 0; i < n; i++){
			a.add(Integer.parseInt(st.nextToken()));
		}
		int total = 0;
		for(int x = 11; x <= 99; x++){
			int ones = x % 10;
			if(ones == 0 || !a.contains(ones))continue;
			int tens = (x - ones) / 10;
			if(tens == 0 || !a.contains(tens))continue;
			for(int y = 111; y <= 999; y++){
				String xy = String.valueOf(y);
				boolean possible = true;
				for(int i = 0; i < xy.length(); i++){
					int temp = Integer.parseInt(xy.substring(i, i+1));
					if(!a.contains(temp)){
						possible = false;
						break;
					}
				}
				if(!possible)continue;
				boolean check = true;
				String firstProduct = String.valueOf(ones * y);
				if(firstProduct.length() != 3)continue;
				for(int i = 0; i < firstProduct.length(); i++){
					int temp = Integer.parseInt(firstProduct.substring(i, i+1));
					if(!a.contains(temp)){
						check = false;
						break;
					}
				}
				if(!check)continue;
				firstProduct = String.valueOf(tens * y);
				if(firstProduct.length() != 3)continue;
				for(int i = 0; i < firstProduct.length(); i++){
					int temp = Integer.parseInt(firstProduct.substring(i, i+1));
					if(!a.contains(temp)){
						check = false;
						break;
					}
				}
				if(!check)continue;
				firstProduct = String.valueOf(x * y);
				if(firstProduct.length() != 4)continue;
				for(int i = 0; i < firstProduct.length(); i++){
					int temp = Integer.parseInt(firstProduct.substring(i, i+1));
					if(!a.contains(temp)){
						check = false;
						break;
					}
				}
				if(!check)continue;
				if(check)total++;
			}
		}
		out.println(total);
		out.close();
	}
}
