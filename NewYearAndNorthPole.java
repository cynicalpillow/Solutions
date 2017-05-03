import java.util.*;
import java.math.*;
import java.io.*;
public class NewYearAndNorthPole {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		int y = 0;
		boolean check = true;
		for(int i = 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			int dist = Integer.parseInt(st.nextToken());
			String x = st.nextToken();
			if(x.equals("North")){
				if(y > 0){
					if(0 > y-dist){
						check = false;
						break;
					} else {
						y-=dist;
					}
				}
			} else if (x.equals("South")){
				if(y < 20000){
					if(20000 < y+dist){
						check = false;
						break;
					} else {
						y+=dist;
					}
				}
			} else {
				if(y == 20000 || y == 0){
					check = false;
					break;
				}
			}
		}
		if(!check)System.out.println("NO");
		else if(y == 0)System.out.println("YES");
		else System.out.println("NO");
	}
}
