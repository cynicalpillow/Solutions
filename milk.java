/*
ID: cynical
LANG: JAVA
TASK: milk
*/
import java.io.*;
import java.util.*;
import java.math.*;

public class milk {
	private static class Farmer implements Comparable<Farmer>{
		int price;
		int units;
		public Farmer(int price, int unit){
			this.price = price;
			this.units = unit;
		}
		public int compareTo(Farmer f){
			if(f.price > this.price)return -1;
			else if(f.price < this.price)return 1;
			return 0;
		}
	}
	public static void main(String args[]) throws Exception{
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader s = new BufferedReader(new FileReader("milk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Farmer[] farmers = new Farmer[k];
		for(int i = 0; i < k; i++){
			st = new StringTokenizer(s.readLine());
			int f = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			farmers[i] = new Farmer(f, u);
		}
		Arrays.sort(farmers);
		int total = 0;
		for(int i = 0; i < k; i++){
			int price = farmers[i].price;
			int units = farmers[i].units;
			if(units < n){
				n-=units;
				total += units * price;
			} else {
				total += (n * price);
				n-= n;
			}
			if(n == 0)break;
		}
		out.println(total);
		out.close();
	}
}
