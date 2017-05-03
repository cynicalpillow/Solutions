import java.util.*;
import java.math.*;
import java.io.*;
public class ECOONeanderthalNumbers {
	static String[] possible = {"ook", "ookook", "oog", "ooga", "ug", "mook", "mookmook", "oogam", "oogum", "ugug"};
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		for(int t = 0; t < 10; t++){
			String make = s.readLine();
			int[] vals = new int[make.length()+1];
			vals[0] = 1;
			for(int i = 1; i < vals.length; i++){
				for(int j = 0; j < possible.length; j++){
					if(i - possible[j].length() >= 0 && make.substring(i-possible[j].length(), i).equals(possible[j])){
						vals[i] += vals[i-possible[j].length()];
					}
				}
			}
			System.out.println(vals[make.length()]);
		}
	}
}
