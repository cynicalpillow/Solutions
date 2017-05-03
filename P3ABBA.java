import java.util.*;
import java.math.*;
import java.io.*;
public class P3ABBA {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		for(int i = 0; i < n; i++){
			StringBuilder x = new StringBuilder(s.readLine());
			String y = s.readLine();
			bfs(x, y);
		}
	}
	public static void bfs(StringBuilder x, String y){
		ArrayDeque<String> q = new ArrayDeque<>();
		q.add(x.toString());
		boolean check = false;
		while(!q.isEmpty()){
			StringBuilder z = new StringBuilder(q.poll());
			if(z.toString().equals(y)){
				check = true;
				break;
			}
			if(z.length() >= y.length())continue;
			StringBuilder temp = new StringBuilder(z.toString());
			if(!q.contains(temp.reverse().append("B").toString())){
				q.add(temp.toString());
			}
			temp = new StringBuilder(z.toString());
			if(!q.contains(temp.append("A").toString())){
				q.add(temp.toString());
			}
		}
		if(check)System.out.println("YES");
		else System.out.println("NO");
	}
}
