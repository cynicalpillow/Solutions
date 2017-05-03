/*
ID: cynical
LANG: JAVA
TASK: dualpal
*/
import java.io.*;
import java.util.*;
import java.math.*;
public class dualpal{

	public static void main(String args[])throws Exception {
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader s = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = 0;
		int i = x+1;
		while(y < n){
			int count = 0;
			for(int j = 2; j <= 10; j++){
				boolean check = true;
				String xx = Integer.toString(Integer.parseInt(String.valueOf(i), 10), j).toUpperCase();
				int jx = xx.length()-1;
				for(int z = 0; z < xx.length(); z++){
					if(z == 0 && xx.charAt(z) == 0){
						check = false;
						break;
					}
					if(xx.charAt(z) != xx.charAt(jx)){
						check = false;
						break;
					}
					jx--;
				}
				if(check)count++;
			}
			if(count >= 2){
				out.println(i);
				y++;
			}
			i++;
		}
		out.close();
	}
	
}

