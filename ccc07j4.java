import java.util.*;
import java.math.*;
public class ccc07j4 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		String y = s.nextLine();
		String x = s.nextLine();
		int[] ya = new int[26];
		int[] xa = new int[26];
		for(int i = 0; i < y.length(); i++){
			if(y.charAt(i) != ' '){
				ya[(int)y.charAt(i) - ((int)'A')]++;
			}
		}
		for(int i = 0; i < x.length(); i++){
			if(x.charAt(i) != ' '){
				xa[(int)x.charAt(i) - ((int)'A')]++;
			}
		}
		boolean check = true;
		for(int i = 0; i < 26; i++){
			if(ya[i] != xa[i]){
				check = false;
				break;
			}
		}
		if(check)System.out.println("Is an anagram.");
		else System.out.println("Is not an anagram.");
	}
	
}
