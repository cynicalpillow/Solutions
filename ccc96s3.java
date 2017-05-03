import java.util.*;
import java.math.*;
public class ccc96s3 {
	
	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int q = s.nextInt();
		for(int i = 0; i < q; i++){
			int n = s.nextInt();
			int k = s.nextInt();
			System.out.println("The bit patterns are");
			recurse("", n, k);
			System.out.println();
		}
	}
	public static void recurse(String y, int n, int k){
		if(n == 0){
			if(k == 0) System.out.println(y);
			return;
		}
		if(k == 0){
			recurse(y+"0", n-1, k);
		} else {
			recurse(y+"1", n-1, k-1);
			recurse(y+"0", n-1, k);
		}
	}
}
