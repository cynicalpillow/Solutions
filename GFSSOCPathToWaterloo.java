import java.util.*;
import java.math.*;
import java.io.*;
public class GFSSOCPathToWaterloo {
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		HashMap<String, Integer> map = new HashMap<>();
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for(int i = 0; i <= n+1; i++){
			graph.add(new ArrayList<>());
		}
		map.put("home", 0);
		map.put("Waterloo GO", n+1);
		for(int i = 1; i <= n; i++){
			String z = s.readLine();
			map.put(z, i);
		}
		for(int i = 0; i < t; i++){
			st = new StringTokenizer(s.readLine(), "-");
			String x = st.nextToken();
			String y = st.nextToken();
			graph.get(map.get(x)).add(map.get(y));
			graph.get(map.get(y)).add(map.get(x));
		}
		int dist = bfs(graph, n+1);
		if(dist == -1)System.out.println("RIP ACE");
		else System.out.println(dist);
	}
	public static int bfs(ArrayList<ArrayList<Integer>> graph, int n){
		ArrayDeque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n+1];
		q.add(0);
		q.add(0);
		visited[0] = true;
		while(!q.isEmpty()){
			int z = q.poll();
			int curr = q.poll();
			if(z == n)return curr;
			for(Integer i : graph.get(z)){
				if(!visited[i]){
					visited[i] = true;
					q.add(i);
					q.add(curr+1);
				}
			}
		}
		return -1;
	}
}
