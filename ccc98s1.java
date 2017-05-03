import java.util.*;
import java.math.*;
public class ccc98s1 {
	
	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int q = s.nextInt();
		for(int i = 0; i < q+1; i++){
			String st = s.nextLine();
			StringTokenizer t = new StringTokenizer(st);
			while(t.hasMoreTokens()){
				String y = t.nextToken();
				if(y.length() == 4){
					System.out.print("**** ");
				} else {
					System.out.print(y + " ");
				}
			}
			System.out.println();
		}
	}
	
}
