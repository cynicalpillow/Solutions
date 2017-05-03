/*
ID: cynical
LANG: JAVA
TASK: friday
*/
import java.io.*;
import java.util.*;
import java.math.*;

public class friday {

	public static void main(String args[]) throws IOException{
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader s = new BufferedReader(new FileReader("friday.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
		int n = Integer.parseInt(s.readLine());
		int[] a = new int[7];
		int curr = 0;
		int year = 1900;
		for(int i = year; i <= year + n-1; i++){
			for(int j = 1; j <= 12; j++){
				a[curr]+=1;
				if(i == n-1 && j == 12)break;
				if(j == 1 || j == 3 || j == 5 || j == 7 || j == 8 || j == 10 || j == 12){
					curr = (curr + 3) % 7;
				} else if (j == 4 || j == 6 || j == 9 || j == 11){
					curr = (curr + 2) % 7;
				} else {
					if(i% 4 == 0 &&  i% 100 != 0){
						curr = (curr + 1) % 7;
					} else if (i % 100 == 0 && i% 400 == 0){
						curr = (curr + 1) % 7;
					}
				}
			}
		}
		for(int i = 0; i < 7; i++){
			if(i == 6)out.print(a[i]);
			else out.print(a[i] + " ");
		}
		out.println();
		out.close();
	}
	
}
