import java.util.*;
import java.math.*;
import java.io.*;
public class Joysticks {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int a1 = Integer.parseInt(st.nextToken());
		int a2 = Integer.parseInt(st.nextToken());
		if(a1 == 1 && a2 == 1){
			System.out.println(0);
			return;
		}
		int charger = 1;
		if(a1 < a2){
			charger = 1;
		} else {
			charger = 2;
		}
		int sec = 0;
		while(a1 > 0 && a2 > 0){
			if(charger == 1){
				a1++;
				a2-=2;
			}else {
				a2++;
				a1-=2;
			}
			if(a1 < a2){
				charger = 1;
			} else {
				charger = 2;
			}
			sec++;
		}
		System.out.println(sec);
	}
}
