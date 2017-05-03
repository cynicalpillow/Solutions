import java.util.*;
import java.math.*;

public class TheatreSquare {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		long n = s.nextLong();
		long m = s.nextLong();
		long a = s.nextLong();
		long bot = m / a;
		if(m % a != 0)bot++;
		long vert = n / a;
		if(n % a != 0)vert++;
		System.out.println(vert * bot);
	}
	
}
