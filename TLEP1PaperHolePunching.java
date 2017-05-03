import java.util.*;
import java.math.*;
import java.io.*;
public class TLEP1PaperHolePunching {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		String template = s.readLine().substring(1);
		int n = Integer.parseInt(s.readLine());
		for(int i = 0; i < n; i++){
			String page = s.readLine().substring(1);
			int count = 0;
			for(int j = 0; j < template.length(); j++){
				boolean check = false;
				for(int z = 0; z < page.length(); z++){
					if(page.charAt(z) == template.charAt(j)){
						check = true;
						break;
					}
				}
				if(check)count++;
			}
			if(count == template.length())System.out.println("yes");
			else System.out.println("no");
		}
	}
}
