import java.util.*;
import java.math.*;
import java.io.*;
public class UTSOpenSecretCode {
	public static class Letter {
		int id;
		ArrayList<Letter> adj = new ArrayList<>();
		Letter mapping;
		public Letter(int i){
			id = i;
		}
	}
	public static boolean possible = false;
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(s.readLine());
		Letter[] letters = new Letter[a];
		for(int i = 0; i < a; i++){
			letters[i] = new Letter(i);
		}
		for(int i = 0; i < a; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			String ai = st.nextToken();
			String bi = st.nextToken();
			int az = ai.charAt(0)-'a';
			int bz = bi.charAt(0)-'a';
			letters[i].adj.add(letters[az]);
			letters[i].adj.add(letters[bz]);
		}
		int q = Integer.parseInt(s.readLine());
		for(int i = 0; i < q; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			String x = st.nextToken();
			String y = st.nextToken();
			boolean[] visitedFrom = new boolean[a];
			boolean[] visitedTo = new boolean[a];
			boolean check = true;
			for(int j = 0; j < x.length(); j++){
				if(letters[x.charAt(j)-'a'].mapping != null && letters[x.charAt(j)-'a'].mapping.equals(letters[y.charAt(j)-'a'])){
					continue;
				} else if(letters[x.charAt(j)-'a'].adj.contains(letters[y.charAt(j)-'a']) && !visitedFrom[x.charAt(j)-'a'] && !visitedTo[y.charAt(j)-'a']){
					visitedFrom[x.charAt(j)-'a'] = true;
					visitedTo[y.charAt(j)-'a'] = true;
					letters[x.charAt(j)-'a'].mapping = letters[y.charAt(j)-'a'];
				} else {
					check = false;
					break;
				}
			}
			if(!check)System.out.println("NO");
			else {
				boolean[] visited = new boolean[a];
				for(Letter l : letters){
					if(l.mapping != null){
						visited[l.mapping.id] = true;
					}
				}
				dfs(letters[0], visited, letters, 0);
				if(possible){
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
			for(int j = 0; j < a; j++){
				letters[j].mapping = null;
			}
			possible = false;
		}
	}
	public static void dfs(Letter a, boolean[] visitedTo, Letter[] letters, int idx){
		if(idx == visitedTo.length-1 && a.mapping == null){
			boolean found = false;
			for(Letter l : a.adj){
				if(!visitedTo[l.id]){
					found = true;
					break;
				}
			}
			if(found)possible = true;
			return;
		} else if(idx == visitedTo.length-1 && a.mapping != null){
			possible = true;
			return;
		}
		boolean mapped = false;
		if(a.mapping != null)mapped = true;
		if(!mapped){
			for(Letter l : a.adj){
				if(!visitedTo[l.id]){
					visitedTo[l.id] = true;
					dfs(letters[idx+1], visitedTo, letters, idx+1);
					visitedTo[l.id] = false;
				}
			}
		} else {
			dfs(letters[idx+1], visitedTo, letters, idx+1);
		}
	}
}
