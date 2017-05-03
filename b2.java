import java.util.*;
import java.math.*;
public class b2 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int q = s.nextInt();
		for(int i = 0; i < q; i++){
			String y = s.next();
			String x = s.next();
			StringBuilder yb = new StringBuilder(y);
			y = yb.reverse().toString();
			StringBuilder xb = new StringBuilder(x);
			x = xb.reverse().toString();
			x.replaceFirst("^0+(?!$)", "");
			y.replaceFirst("^0+(?!$)", "");
			int yi = Integer.parseInt(y);
			int xi = Integer.parseInt(x);
			int total = yi+xi;
			StringBuilder tb = new StringBuilder(String.valueOf(total));
			System.out.println(tb.reverse().toString().replaceFirst("^0+(?!$)", ""));
		}
	}
}
