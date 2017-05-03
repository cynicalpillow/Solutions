import java.util.*;
import java.math.*;
public class ioi0921 {

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int m = s.nextInt();
		int[] r = new int[n];
		int[] c = new int[m];
		for(int i = 0; i < n; i++){
			r[i] = s.nextInt();
		}
		for(int i = 0; i < m; i++){
			c[i] = s.nextInt();
		}
		long total = 0;
		int[] p = new int[n];
		ArrayDeque<Integer> cq = new ArrayDeque<>();
		int availableSpaces = n;
		for(int i = 0; i < 2*m; i++){
			int car = s.nextInt();
			if(car < 0){
				int count = 0;
				while(p[count] != Math.abs(car)){
					if(count + 1 >= n)break;
					count++;
				}
				p[count] = 0;
				if(cq.size() > 0){
					car = cq.poll();
					p[count] = car;
					total += r[count] * c[car-1];
				} else {
					availableSpaces++;
				}
			} else {
				if(availableSpaces == 0){
					cq.add(car);
				} else {
					int count = 0;
					while(count < n && p[count] != 0){
						count++;
					}
					p[count] = car;
					total += r[count] * c[car-1];
					availableSpaces--;
				}
			}
		}
		System.out.println(total);
	}
	
}
