import java.util.*;
import java.math.*;
public class primes1 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		seive(n);
	}
	public static void seive(int n){
		boolean[] c = new boolean[100000];
		for(int i = 0; i < 100000; i++){
			c[i] = true;
		}
		int count = n;
		for(int i = 2; i*i < 100000; i++){
			if(c[i]){
				for(int j = 2; j*i < 100000; j++){
					c[j*i] = false;
				}
			}
		}
		for(int i = 2; i < 100000; i++){
			if(c[i]){
				System.out.println(i);
				count--;
			}
			if(count == 0)break;
		}
	}
}
