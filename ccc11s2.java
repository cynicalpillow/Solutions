import java.util.*;
import java.math.*;
public class ccc11s2 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		char[] c = new char[n];
		char[] a = new char[n];
		for(int i = 0; i < n; i++){
			a[i] = s.next().charAt(0);
		}
		for(int i = 0; i < n; i++){
			c[i] = s.next().charAt(0);
		}
		int total = 0;
		for(int i = 0; i < n; i++){
			if(a[i] == c[i])total++;
		}
		System.out.println(total);
	}
	
}
