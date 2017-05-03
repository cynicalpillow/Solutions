import java.util.*;
import java.math.*;
import java.io.*;
public class VMSSSets {
	private static class Set implements Comparable<Set>{
		ArrayList<Set> adj = new ArrayList<>();
		TreeSet<Character> items = new TreeSet<>();
		String id;
		boolean visited = false;
		boolean looped = false;
		public Set(String s){
			id = s;
		}
		@Override
		public int compareTo(Set a) {
			if(this.id.charAt(0) < a.id.charAt(0))return -1;
			else if(this.id.charAt(0) > a.id.charAt(0))return 1;
			return 0;
		}
	}
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(s.readLine());
		ArrayList<Set> sets = new ArrayList<>();
		HashMap<String, Set> map = new HashMap<>();
		for(int i = 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(s.readLine());
			String x = st.nextToken();
			st.nextToken();
			String y = st.nextToken();
			String temp = y;
			if(!map.containsKey(x)){
				Set set = new Set(x);
				map.put(x, set);
				sets.add(set);
			}
			if(temp.toUpperCase().equals(y)){
				if(!map.containsKey(y)){
					Set set = new Set(y);
					map.put(y, set);
					sets.add(set);
				}
				map.get(x).adj.add(map.get(y));
			} else {
				map.get(x).items.add(y.charAt(0));
			}
		}
		Collections.sort(sets);
		for(Set st : sets){
			if(!st.visited){
				TreeSet<Character> items = dfs(st);
				for(Character c : items)st.items.add(c);
			}
		}
		for(Set st : sets){
			if(st.looped){
				TreeSet<Character> items = dfs(st);
				for(Character c : items)st.items.add(c);
			}
		}
		for(Set st : sets){
			System.out.print(st.id + " = {");
			int count = 0;
			int size = st.items.size();
			for(Character c : st.items){
				if(count < size-1)System.out.print(c + ",");
				else System.out.print(c);
				count++;
			}
			System.out.println("}");
		}
	}
	public static TreeSet<Character> dfs(Set s){
		TreeSet<Character> set = new TreeSet<>();
		s.visited = true;
		for(Set st : s.adj){
			if(!st.visited){
				TreeSet<Character> items = dfs(st);
				for(Character c : items)set.add(c);
			} else {
				for(Character c : st.items)set.add(c);
				s.looped = true;
			}
		}
		for(Character c : set)s.items.add(c);
		return s.items;
	}
}
