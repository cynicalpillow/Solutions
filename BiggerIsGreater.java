
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class BiggerIsGreater {
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s;
		try {
			s = new Scanner((Readable) new FileReader(new File("C:/Users/Rui/Desktop/Developing/input.txt")));
	        int q = s.nextInt();
	        for(int i = 0; i < q; i++){
	            String y = s.next();
	            StringBuilder x = new StringBuilder();
	            ArrayList<Character> a = new ArrayList<>();
	            int left = y.length()-1;
	            while(left > 0 && y.charAt(left) < y.charAt(left-1)){
	                left--;
	            }
	            left--;
	            int j = y.length()-1;
	            if(left == -1 || left >= j){
	                System.out.println("no answer");
	                continue;
	            }
	            while(j > 0 && y.charAt(j) <= y.charAt(left)){
	                j--;
	            }
	            if(j == -1 || j <= left){
	                System.out.println("no answer");
	                continue;
	            }
	            for(int z = 0; z < left; z++){
	                a.add(y.charAt(z));
	            }
	            if(j != left){
	                a.add(y.charAt(j));
	            }
	            for(int z = left+1; z <= j; z++){
	                if(z!=j)a.add(y.charAt(z));
	                else a.add(y.charAt(left));
	            }
	            for(int z = j+1; z < y.length(); z++){
	                a.add(y.charAt(z));
	            }
	            Collections.sort(a.subList(left+1, a.size()));
	            for(Character c : a){
	                x.append(c);
	            }
	            System.out.println(x.toString());
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
