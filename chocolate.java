import java.util.*;
import java.math.*;
public class chocolate {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int q = s.nextInt();
		for(int i = 0; i < q; i++){
			int r = s.nextInt();
			int c = s.nextInt();
			System.out.println(r * c - 1);
		}
	}
	
}
