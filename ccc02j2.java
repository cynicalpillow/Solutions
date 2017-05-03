import java.util.*;
import java.math.*;

public class ccc02j2 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		while(s.hasNext()){
			String y = s.next();
			if(y.equals("quit!"))break;
			boolean check = true;
			int i = y.length()-2;
			if(y.length() > 4){
					if((y.charAt(i-1) != 'a' && y.charAt(i-1) != 'e' && y.charAt(i-1) != 'i' && y.charAt(i-1) != 'o' && y.charAt(i-1) != 'u' && y.charAt(i-1) != 'y') && y.charAt(i) == 'o' && y.charAt(i+1) == 'r'){
						check = false;
					}
			}
			if(!check)System.out.println(y.substring(0, i) + "our" + y.substring(i+2, y.length()));
			else System.out.println(y);
		}
	}
	
}
