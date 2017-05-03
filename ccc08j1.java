import java.util.*;
import java.math.*;
public class ccc08j1 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		double x = s.nextDouble();
		double y = s.nextDouble();
		double result = x / (y*y);
		if(result > 25.0){
			System.out.println("Overweight");
		} else if (result < 18.5){
			System.out.println("Underweight");
		} else {
			System.out.println("Normal weight");
		}
	}
	
}
