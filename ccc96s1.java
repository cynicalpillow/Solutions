import java.util.*;
import java.math.*;

public class ccc96s1 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		for(int i = 0; i < n; i++){
			int x = s.nextInt();
			int count = 0;
			for(int j = 1; j < x; j++){
				if(x % j == 0)count+=j;
				if(count > x)break;
			}
			if(count > x)System.out.println(x + " is an abundant number.");
			else if(count == x)System.out.println(x + " is a perfect number.");
			else System.out.println(x + " is a deficient number.");
		}
	}
	
}
