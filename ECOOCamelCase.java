import java.util.*;
import java.math.*;
import java.io.*;
public class ECOOCamelCase {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		String[] words = new String[n];
		for(int i = 0; i < n; i++){
			words[i] = s.readLine();
		}
		for(int T = 0; T < 10; T++){
			String make = s.readLine();
			int[] vals = new int[make.length()+1];
			for(int i = 0; i < vals.length; i++)vals[i] = Integer.MAX_VALUE;
			ArrayList<String> contains = new ArrayList<>();
			for(String str : words){
				if(make.contains(str))contains.add(str);
			}
			vals[0] = -1;
			for(int i = 1; i < vals.length; i++){
				for(int j = 0; j < contains.size(); j++){
					if(i - contains.get(j).length() >= 0){
						if(make.substring(i-contains.get(j).length(), i).equals(contains.get(j))){
							if(vals[i-contains.get(j).length()] != Integer.MAX_VALUE)vals[i] = Math.min(vals[i], vals[i-contains.get(j).length()] + 1);
						}
					}
				}
			}
			System.out.println(vals[vals.length-1]);
		}
	}
}
