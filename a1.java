import java.util.*;
import java.math.*;

public class a1 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		for(int i = 1; i <= n; i++){
			int x = s.nextInt();
			String y = s.nextLine();
			y = y.trim();
			if(x < y.length()){
				System.out.println(i + " " + y.substring(0, x-1) + y.substring(x, y.length()));
			} else {
				System.out.println(i + " " + y.substring(0, y.length()-1));
			}
		}
	}
	
}
