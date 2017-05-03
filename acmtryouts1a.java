import java.util.*;
import java.math.*;

public class acmtryouts1a {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		for(int i = 0; i < n; i++){
			String y = s.next();
			if(y.equals("Scissors"))System.out.println("Rock");
			else if(y.equals("Rock"))System.out.println("Paper");
			else if(y.equals("Paper"))System.out.println("Scissors");
			else if(y.equals("Fox"))System.out.println("Foxen");
			else break;
		}
	}
	
}
