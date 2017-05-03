import java.util.*;
import java.math.*;

public class ccc13s2p1 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		long n = s.nextLong();
		long i;
		for(i = 2; i <= n/2; i++){
			ArrayList<Long> y = convert(n, i);
			boolean check = true;
			int c = y.size()-1;
			for(int j = 0; j < y.size(); j++){
				if(c <= j)break;
				if(y.get(c) == y.get(j))c--;
				else {
					check = false;
					break;
				}
			}
			if(check){
				System.out.println(i);
			}
		}
		if(n > 2)System.out.println(n-1);
	}
	public static ArrayList<Long> convert(long n, long b){
		ArrayList<Long> x = new ArrayList<>();
		while(n != 0){
			x.add(n % b);
			n/=b;
		}
		return x;
	}
}
