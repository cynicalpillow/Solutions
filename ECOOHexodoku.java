import java.util.*;
import java.math.*;
import java.io.*;
public class ECOOHexodoku {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		for(int T = 0; T < 10; T++){
			int[][] vals = new int[16][16];
			for(int y = 0; y < 16; y++){
				String z = s.readLine();
				for(int x = 0; x < 16; x++){
					int curr;
					if(z.charAt(x) >= 'A' && z.charAt(x) <= 'F'){
						curr = z.charAt(x)-'A' + 10;
					} else if (z.charAt(x) == '-'){
						curr = -1;
					} else {
						curr = Integer.parseInt(z.substring(x, x+1));
					}
					vals[y][x] = curr;
				}
			}
			int count = 0;
			for(int i = 0; i < 16; i++){
				for(int j = 0; j < 16; j++){
					if(vals[i][j] == -1){
						for(int z = 0; z < 16; z++){
							boolean check = false;
							for(int y = 0; y < 16; y++){
								if((j != y) && vals[i][y] == z){
									check = true;
									break;
								}
							}
							for(int y = 0; y < 16; y++){
								if((i != y) && vals[y][j] == z){
									check = true;
									break;
								}
							}
							if(!check)check = checkQuad(vals, i, j, z);
							if(!check){
								vals[i][j] = z;
								count++;
								break;
							}
						}
					}
				}
			}
			System.out.println(count);
		}
	}
	public static boolean checkQuad(int[][] vals, int y, int x, int val){
		int xQuad = 4*(x/4);
		int yQuad = 4*(y/4);
		for(int i = yQuad; i < yQuad + 4; i++){
			for(int j = xQuad; j < xQuad + 4; j++){
				if((i != y && x != j) && vals[i][j] == val){
					return true;
				}
			}
		}
		return false;
	}
}
