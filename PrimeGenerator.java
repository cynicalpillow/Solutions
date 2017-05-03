import java.math.BigInteger;
import java.util.Scanner;

public class PrimeGenerator {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int q = s.nextInt();
		for(int qs = 0; qs < q; qs++){
			int x = 1;
			int y = 15;
			for(int i = x; i < y; i++){
				if(new BigInteger(String.valueOf(i)).isProbablePrime(1) && i != 1 && i != 0)System.out.println(i);
			}
			System.out.println();
		}
	}
	
}
