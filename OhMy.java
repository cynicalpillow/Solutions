import java.util.*;
import java.math.*;
import java.io.*;
public class OhMy {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		String y = s.readLine();
		int deletes = 0;
		int[] a = new int[y.length()];
		for(int i = 0; i < y.length(); i++){
			if(i == 0)a[i] = 1;
			else {
				if(y.charAt(i) == 'o')a[i]=1;
				else if(y.charAt(i) == 'h'){
					if(a[i-1] == 1)a[i]=a[i-1]+1;
					else if(a[i-1] == 2)a[i] = a[i-1];
					else a[i] = 1;
				}
				else if(y.charAt(i) == 'm'){
					if(a[i-1] == 2)a[i]=a[i-1]+1;
					else if(a[i-1] == 3)a[i] = a[i-1];
					else a[i] = 1;
				}
				else if(y.charAt(i) == 'y'){
					if(a[i-1] == 3)a[i]=a[i-1]+1;
					else if(a[i-1] == 4)a[i] = a[i-1];
					else a[i] = 1;
				}
				else a[i] = a[i-1];
			}
		}
		ArrayList<Integer> breaks = new ArrayList<>();
		breaks.add(0);
		int[] dp = new int[y.length()];
		for(int i = 0; i < y.length(); i++){
			if(i == 0)dp[i] = 0;
			else {
				if(a[i] == 4 && a[i-1] == 3){
					dp[i] = dp[i-1]+1;
					breaks.add(i+1);
				}
				dp[i] = Math.max(dp[i], dp[i-1]);
			}
		}
		if(breaks.size() > dp[y.length()-1]){
			breaks.remove(breaks.size()-1);
			breaks.add(y.length());
		}
		boolean[] deleted = new boolean[y.length()];
		for(int i = 0; i < breaks.size()-1; i++){
			String st = y.substring(breaks.get(i), breaks.get(i+1));
			for(int j = 0; j < st.length(); j++){
				if(st.charAt(j) != 'y' && st.charAt(j) != 'o' && st.charAt(j) != 'h' && st.charAt(j) != 'm'){
					deletes++;
					deleted[j] = true;
				} else {
					if(st.charAt(j) != 'o' && st.charAt(j) != 'h' && st.charAt(j) != 'y'  && j-1 >= 0 && (st.charAt(j-1) == 'y' || st.charAt(j-1) == 'o')){
						deletes++;
						deleted[j] = true;
						System.out.println(breaks.get(i) + j);
					} else if(st.charAt(j) != 'h' && st.charAt(j) != 'm' && st.charAt(j) != 'o' && j-1 >= 0 && (st.charAt(j-1) == 'o' || st.charAt(j-1) == 'h')){
						deletes++;
						deleted[j] = true;
						System.out.println(j);
					}else if(st.charAt(j) != 'm' && st.charAt(j) != 'y' && st.charAt(j) != 'h' && j-1 >= 0 && (st.charAt(j-1) == 'h'|| st.charAt(j-1) == 'm')){
						deletes++;
						deleted[j] = true;
						System.out.println(j);
					} else if(st.charAt(j) != 'y' && st.charAt(j) != 'o' && st.charAt(j) != 'm' && j-1 >= 0 && (st.charAt(j-1) == 'm'|| st.charAt(j-1) == 'y')){
						deletes++;
						deleted[j] = true;
						System.out.println(j);
					}
				}
			}
		}
		System.out.println(deletes);
	}
}
