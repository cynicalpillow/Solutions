import java.util.*;
import java.math.*;
import java.io.*;
public class CompleteTheWord {
	//ABABABBABAAAAAAAAAAAAABABABABAAAAAAAAAAAAABABABABADEFGHIMNOKLCSJBPQRTUVWXYZ
	//ABABABBABAAAAAAAAAAAAABABABABAAAAAAAAAAAAABABABABADEFGHIMNOKLCSJBPQRTUVWXYZ
	//QWERTYUIOPASDFGHJKLBCMNVXZ
	//QWERTYUIOPASDFGHJKLBCMNVXZ
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		String org = s.readLine();
		char[] a = org.toCharArray();
		int[] one = new int[26];
		int question = 0;
		int count = 0;
		if(a.length < 26){
			System.out.println(-1);
			return;
		}
		for(int i = 0; i < 26; i++){
			if(a[i] == '?'){
				question++;
			} else {
				if(one[a[i] - 'A'] == 0)count++;
				one[a[i] - 'A']++;
			}
		}
		if(count + question == 26){
			boolean[] visited = new boolean[26];
			StringBuilder x = new StringBuilder();
			boolean possible = true;
			for(int i = 0; i < 26; i++){
				if(a[i] == '?'){
					boolean z = false;
					for(int j = 0; j < 26; j++){
						if(one[j] == 0 && !visited[j]){
							x.append((char)(j+'A'));
							visited[j] = true;
							z = true;
							break;
						}
					}
					if(!z){
						possible = false;
						break;
					}
				} else {
					if(visited[a[i] - 'A']){
						possible = false;
						break;
					}
					x.append(a[i]);
					visited[a[i] - 'A'] = true;
				}
			}
			if(possible){
				x.append(org.substring(26, org.length()).replace('?', 'A'));
				System.out.println(x.toString());
				return;
			}
		}
		int low = 0;
		for(int b = 26; b < a.length; b++){
			if(a[low] == '?'){
				question--;
			} else {
				if(one[a[low] - 'A'] == 1)count--;
				one[a[low] - 'A']--;
			}
			if(a[b] == '?'){
				question++;
			} else {
				if(one[a[b] - 'A'] == 0){
					count++;
				}
				one[a[b] - 'A']++;
			}
			if(count + question == 26){
				boolean[] visited = new boolean[26];
				StringBuilder x = new StringBuilder();
				x.append(org.substring(0, low+1).replace('?', 'A'));
				boolean possible = true;
				for(int i = low+1; i <= b; i++){
					if(a[i] == '?'){
						boolean z = false;
						for(int j = 0; j < 26; j++){
							if(one[j] == 0 && !visited[j]){
								x.append((char)(j+'A'));
								visited[j] = true;
								z = true;
								break;
							}
						}
						if(!z){
							possible = false;
							break;
						}
					} else {
						if(visited[a[i] - 'A']){
							possible = false;
							break;
						}
						x.append(a[i]);
						visited[a[i] - 'A'] = true;
					}
				}
				if(possible){
					x.append(org.substring(b+1, org.length()).replace('?', 'A'));
					System.out.println(x.toString());
					return;
				}
			}
			low++;
		}
		System.out.println(-1);
	}
}
