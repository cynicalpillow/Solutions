import java.util.*;
import java.math.*;
import java.io.*;
public class JumpingBall {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(s.readLine());
		char[] st = s.readLine().toCharArray();
		if(st.length == 1){
			System.out.println(1);
			return;
		}
		int i = n-1;
		int j = 0;
		int total = 0;
		boolean checki = true;
		boolean checkj = true;
		while(j <= n-1){
			if(!checki && !checkj)break;
			if(st[i] == '<')checki = false;
			if(st[j] == '>')checkj = false;
			if(st[i] == '>' && checki)total++;
			if(st[j] == '<' && checkj)total++;
			i--;
			j++;
		}
		System.out.println(total);
	}
}
