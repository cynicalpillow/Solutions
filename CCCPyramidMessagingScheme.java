import java.util.*;
import java.math.*;
import java.io.*;
public class CCCPyramidMessagingScheme {
	private static class Node{
		String name;
		ArrayList<Node> adj = new ArrayList<>();
		public Node(String n){
			name = n;
		}
	}
	public static int original;
	public static void main(String args[]) throws Exception {
		Scanner s = new Scanner(System.in);
		int q = s.nextInt();
		for(int qq = 0; qq < q; qq++){
			StringBuilder x = new StringBuilder();
			int n = s.nextInt();
			original = n * 10;
			HashSet<String> names = new HashSet<>();
			for(int i = 0; i < n; i++){
				String y = s.next();
				x.append(y + " ");
				names.add(y);
			}
			ArrayList<Node> nodes = new ArrayList<>();
			HashMap<String, Integer> map = new HashMap<>();
			Node last = null;
			int co = 0;
			for(String st : names){
				nodes.add(new Node(st));
				map.put(st, co);
				co++;
			}
			StringTokenizer st = new StringTokenizer(x.toString());
			while(st.hasMoreTokens()){
				String z = st.nextToken();
				String y = st.nextToken();
				last = nodes.get(map.get(y));
				nodes.get(map.get(z)).adj.add(nodes.get(map.get(y)));
				nodes.get(map.get(y)).adj.add(nodes.get(map.get(z)));
			}
			bfs(nodes, map, last);
		}
	}
	public static void bfs(ArrayList<Node> nodes, HashMap<String, Integer> map, Node start){
		ArrayDeque<Node> q = new ArrayDeque<>();
		boolean[] visited = new boolean[nodes.size()];
		q.add(start);
		visited[nodes.indexOf(start)] = true;
		int deepest = 0;
		int[] dists = new int[nodes.size()];
		dists[nodes.indexOf(start)]=20;
		while(!q.isEmpty()){
			Node n = q.poll();
			boolean check = false;
			for(Node adj : n.adj){
				if(!visited[map.get(adj.name)]){
					visited[map.get(adj.name)] = true;
					dists[map.get(adj.name)] = dists[map.get(n.name)]+20;
					q.add(adj);
					check = true;
				}
			}
			if(check)deepest = Math.max(deepest, dists[map.get(n.name)]);
		}
		System.out.println(Math.abs(original -deepest));
	}
}
