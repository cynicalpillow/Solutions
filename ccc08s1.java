import java.util.*;
import java.math.*;
public class ccc08s1 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int min = Integer.MAX_VALUE;
		String city = "Waterloo";
		while(s.hasNext()){
			String y = s.next();
			int x = s.nextInt();
			if(x < min){
				city = y;
				min = x;
			}
		}
		System.out.println(city);
	}
	
}
