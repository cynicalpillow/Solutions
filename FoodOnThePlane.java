import java.util.*;
import java.math.*;
import java.io.*;
public class FoodOnThePlane {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		String y = s.readLine();
		long row = Long.parseLong(y.substring(0, y.length()-1));
		int col = y.charAt(y.length()-1) - 'a';
		long secs = 0;
		if(row == 1)secs = 0;
		else if (row == 2)secs = 7;
		else {
			long remainder = row % 4;
			if(remainder == 1){
				secs = ((row - 1) / 4) * 16;
			} else if (remainder == 3){
				secs = ((row - 3)/4) * 16;
			} else if (remainder == 2){
				secs = ((((row + 2) / 4)-1) * 16)+7;
			} else {
				secs = (((row / 4)-1) * 16)+7;
			}
		}
		if(col == 0){
			secs += 4;
		} else if(col == 1){
			secs += 5;
		} else if(col == 2){
			secs += 6;
		} else if(col == 3){
			secs += 3;
		} else if(col == 4){
			secs += 2;
		} else {
			secs+=1;
		}
		System.out.println(secs);
	}
}
