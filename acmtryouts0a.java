import java.util.*;
import java.math.*;
public class acmtryouts0a {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int q = s.nextInt();
		for(int  i = 0; i < q; i++){
			int max = 1;
			int n = s.nextInt();
			for(int j = 0; j < n; j++){
				max = Math.max(max, s.nextInt());
			}
			System.out.println(max);
		}
	}
	
}
