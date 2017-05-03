import java.util.*;
import java.math.*;

public class acmtryouts1c {

	static int currposx = 0;
	static int currposy = 0;
	static char[][] m;
	static boolean[][] tr;
	
	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int q = s.nextInt();
		for(int i = 0; i < q; i++){
			currposx = 0;
			currposy = -1;
			int h = s.nextInt();
			int w = s.nextInt();
			int n = s.nextInt();
			m = new char[h][w];
			for(int j = 0; j < h; j++){
				String y = s.next();
				for(int x = 0; x < w; x++){
					m[j][x] = y.charAt(x);
				}
			}
			tr = new boolean[h][w];
			int total = 0;
			for(int j = 0; j < n; j++){
				char y = s.next().charAt(0);
				if(y == 'L' && currposx - 1 >= 0){
					if(currposy == -1){
						currposx--;
						continue;
					}
					if(m[currposy][currposx-1] == 'S'){
						continue;
					}
					currposx = currposx-1;
					if(m[currposy][currposx] == 'T'){
						if(!tr[currposy][currposx]){
							tr[currposy][currposx] = true;
							total++;
						}
					}
				}else if(y == 'R' && currposx + 1 < w){
					if(currposy == -1){
						currposx++;
						continue;
					}
					if(m[currposy][currposx+1] == 'S'){
						continue;
					}
					currposx = currposx+1;
					if(m[currposy][currposx] == 'T'){
						if(!tr[currposy][currposx]){
							tr[currposy][currposx] = true;
							total++;
						}
					}
				} else if(y == 'D' && currposy + 1 < m.length){
					if(m[currposy+1][currposx] == 'S'){
						continue;
					}
					currposy = currposy+1;
					if(m[currposy][currposx] == 'T'){
						if(!tr[currposy][currposx]){
							tr[currposy][currposx] = true;
							total++;
						}
					}
				}
				total += checkEmpty();
			}
			System.out.println(total);
		}
	}
	public static int checkEmpty(){
		int total = 0;
		while(currposy+1 < m.length && m[currposy+1][currposx] == 'E'){
			currposy++;
		}
		return total;
	}
}
