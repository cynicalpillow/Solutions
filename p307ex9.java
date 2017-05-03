import java.util.*;
import java.math.*;

public class p307ex9 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int t =s.nextInt();
		for(int i = 0; i < t; i++){
			int y = s.nextInt();
			if(y % 4 == 0 && y % 100 != 0)System.out.println(1);
			else if(y%400 == 0)System.out.println(1);
			else System.out.println(0);
		}
	}
	
}
