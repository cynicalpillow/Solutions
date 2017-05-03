import java.util.*;
import java.math.*;
import java.io.*;
public class Main {
	static class Reader {
			final private int BUFFER_SIZE = 1 << 16;
			private DataInputStream din;
			private byte[] buffer;
			private int bufferPointer, bytesRead;
			public Reader() {
				din = new DataInputStream(System.in);
				buffer = new byte[BUFFER_SIZE];
				bufferPointer = bytesRead = 0;
			}
			public Reader(String file_name) throws IOException {
				din = new DataInputStream(new FileInputStream(file_name));
				buffer = new byte[BUFFER_SIZE];
				bufferPointer = bytesRead = 0;
			}
			public String readLine() throws IOException {
				byte[] buf = new byte[64]; // line length
				int cnt = 0, c;
				while ((c = read()) != -1) {
					if (c == '\n')
						break;
					buf[cnt++] = (byte) c;
				}
				return new String(buf, 0, cnt);
			}
			public int nextInt() throws IOException {
				int ret = 0;
				byte c = read();
				while (c <= ' ')
					c = read();
				boolean neg = (c == '-');
				if (neg)
					c = read();
				do {
					ret = ret * 10 + c - '0';
				} while ((c = read()) >= '0' && c <= '9');
				if (neg)
					return -ret;
				return ret;
			}
			public long nextLong() throws IOException {
				long ret = 0;
				byte c = read();
				while (c <= ' ')
					c = read();
				boolean neg = (c == '-');
				if (neg)
					c = read();
				do {
					ret = ret * 10 + c - '0';
				} while ((c = read()) >= '0' && c <= '9');
				if (neg)
					return -ret;
				return ret;
			}
			public double nextDouble() throws IOException {
				double ret = 0, div = 1;
				byte c = read();
				while (c <= ' ')
					c = read();
				boolean neg = (c == '-');
				if (neg)
					c = read();
				do {
					ret = ret * 10 + c - '0';
				} while ((c = read()) >= '0' && c <= '9');
				if (c == '.') {
					while ((c = read()) >= '0' && c <= '9') {
						ret += (c - '0') / (div *= 10);
					}
				}
				if (neg)
					return -ret;
				return ret;
			}
			private void fillBuffer() throws IOException {
				bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
				if (bytesRead == -1)
					buffer[0] = -1;
			}
			private byte read() throws IOException {
				if (bufferPointer == bytesRead)
					fillBuffer();
				return buffer[bufferPointer++];
			}
			public void close() throws IOException {
				if (din == null)
					return;
				din.close();
			}
		}
	private static class Node {
		ArrayList<Edge> adj = new ArrayList<>();
		int id;
		boolean restaurant = false;
		public Node(int id){
			this.id = id;
		}
	}
	private static class Edge {
		int u;
		int v;
		int id;
		public Edge(int u, int v, int i){
			this.u = u;
			this.v = v;
			id = i;
		}
	}
	private static class State {
		ArrayList<Edge> curr = new ArrayList<>();
		int id;
		public State(int i){
			id = i;
		}
	}
	public static Edge[] edges;
	public static HashSet<Edge> finalEdges = new HashSet<>();
	public static boolean[] fe;
	public static Node far = new Node(-1);
	public static int max = 0;
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int m = s.nextInt();
		Node[] nodes = new Node[n];
		int[] rest = new int[m];
		for(int i = 0; i < n; i++)nodes[i] = new Node(i);
		for(int i = 0; i < m; i++){
			rest[i] = s.nextInt();
			nodes[rest[i]].restaurant = true;
		}
		edges = new Edge[n-1];
		for(int i = 0; i < n-1; i++){
			int x = s.nextInt();
			int y = s.nextInt();
			Edge e = new Edge(x, y, i);
			edges[i] = e;
			nodes[x].adj.add(e);
			nodes[y].adj.add(e);
		}
		dfs(rest[0], nodes, new boolean[n], 0, new HashSet<>());
		fe = new boolean[n-1];
		for(Edge e : finalEdges) fe[e.id] = true;
		max = 0;
		dfs2(far.id, nodes, new boolean[n], 0);
		System.out.println((finalEdges.size() - max)*2 + max);
	}
	public static void dfs(int curr, Node[] nodes, boolean[] visited, int dist, HashSet<Edge> es){
		visited[curr] = true;
		if(dist > max && nodes[curr].restaurant){
			max = dist;
			far = nodes[curr];
		}
		if(nodes[curr].restaurant){
			finalEdges.addAll(es);
			es.clear();
		}
		for(Edge e : nodes[curr].adj){
			if(e.v != curr && !visited[e.v]){
				es.add(e);
				dfs(e.v, nodes, visited, dist+1, es);
				es.remove(e);
			} else if(e.u != curr && !visited[e.u]){
				es.add(e);
				dfs(e.u, nodes, visited, dist+1, es);
				es.remove(e);
			}
		}
	}
	public static void dfs2(int curr, Node[] nodes, boolean[] visited, int dist){
		visited[curr] = true;
		if(dist > max){
			max = dist;
		}
		for(Edge e : nodes[curr].adj){
			if(fe[e.id]){
				if(e.v != curr && !visited[e.v]){
					dfs2(e.v, nodes, visited, dist+1);
				} else if(e.u != curr && !visited[e.u]){
					dfs2(e.u, nodes, visited, dist+1);
				}
			}
		}
	}
}