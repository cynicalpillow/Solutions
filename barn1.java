/*
ID: cynical
LANG: JAVA
TASK: barn1
*/
import java.io.*;
import java.util.*;
import java.math.*;

public class barn1 {
	public static void main(String args[]) throws Exception{
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader s = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int stalls = Integer.parseInt(st.nextToken());
		int o = Integer.parseInt(st.nextToken());
		int[] diffarray = new int[o];
		int[] sta = new int[o];
		for(int i = 0; i < o; i++){
			sta[i] = Integer.parseInt(s.readLine());
		}
		Arrays.sort(sta);
		for(int i = 0; i < o; i++){
			if(i == 0){
				diffarray[i] = sta[i];
				continue;
			}
			diffarray[i] = (sta[i] - sta[i-1]) - 1;
		}
		if(n == 1){
			out.println(sta[o-1] - sta[0] + 1);
			out.close();
		}
		stalls -= (stalls - sta[o-1]);
		stalls -= diffarray[0] - 1;
		diffarray[0] = -1;
		Arrays.sort(diffarray);
		for(int i = o-1; i >= o-n+1; i--){
			if(diffarray[i] == -1)break;
			stalls-=diffarray[i];
		}
		out.println(stalls);
		out.close();
	}
}
