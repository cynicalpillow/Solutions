import java.util.*;
import java.math.*;
import java.io.*;
public class MNYCHurontario {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		String x = st.nextToken();
		String y = st.nextToken();
		String[] suffixX = new String[x.length()];
		String[] prefixY = new String[y.length()];
		for(int i = 0; i < x.length(); i++) suffixX[i] = x.substring(i, x.length());
		for(int i = 1; i <= y.length(); i++) prefixY[i-1] = y.substring(0, i);
		int splitY = -1;
		boolean found = false;
		for(int i = 0; i < x.length(); i++){
			for(int j = 0; j < y.length(); j++){
				if(suffixX[i].equals(prefixY[j])){
					splitY = j;
					found = true;
				}
			}
			if(found)break;
		}
		System.out.println(x + y.substring(splitY+1, y.length()));
	}
}
