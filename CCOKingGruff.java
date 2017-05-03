import java.util.*;
import java.math.*;
import java.io.*;
public class CCOKingGruff {
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
		int id;
		int distToB = Integer.MAX_VALUE;
		ArrayList<Edge> adj = new ArrayList<>();
		public Node(int i){
			id = i;
		}
	}
	private static class Edge {
		int shutdown;
		int length;
		Node v;
		int id;
		public Edge(int i, Node u, int l, int s){
			v = u;
			length = l;
			shutdown = s;
			id = i;
		}
	}
	private static class State implements Comparable<State>{
		int l;
		int c;
		Node v;
		public State(int c, int l, Node u){
			this.c = c;
			this.l = l;
			v = u;
		}
		@Override
		public int compareTo(State o) {
			if(this.l < o.l)return -1;
			else if(this.l > o.l)return 1;
			return 0;
		}
	}
	static boolean[] ev;
	static int toB = 0;
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int m = s.nextInt();
		int a = s.nextInt();
		int b = s.nextInt();
		Node[] nodes = new Node[n+1];
		Edge[] edges = new Edge[m];
		ev = new boolean[m];
		for(int i= 1; i <= n; i++)nodes[i] = new Node(i);
		for(int i = 0; i < m; i++){
			int x = s.nextInt();
			int y = s.nextInt();
			int l = s.nextInt();
			int c = s.nextInt();
			Edge e = new Edge(i, nodes[y], l, c);
			nodes[x].adj.add(e);
			edges[i] = e;
		}
		int q = s.nextInt();
		dijkstra(nodes, a, b);
		for(int i= 0; i < q; i++){
			toB = 0;
			int d = s.nextInt();
			dfs(a, 0, nodes, new ArrayList<Edge>(), new ArrayList<Node>(), b, d);
			for(Edge e : nodes[b].adj){
				if(e.length <= d){
					ArrayList<Edge> ed = new ArrayList<Edge>();
					ed.add(e);
					dfs2(e.v.id, e.length, nodes, ed, new ArrayList<Node>(), b, d);
				}
			}
			System.out.println(toB);
			ev = new boolean[m];
		}
	}
	public static void dfs(int v, int dist, Node[] nodes, ArrayList<Edge> currEdges, ArrayList<Node> curr, int b, int d){
		curr.add(nodes[v]);
		if(v != b){
			for(Edge e : nodes[v].adj){
				if(!curr.contains(e.v) && dist + e.length <= d){
					ArrayList<Edge> ed = new ArrayList<>(currEdges);
					if(!ev[e.id] && !currEdges.contains(e)) ed.add(e);
					dfs(e.v.id, dist + e.length, nodes, ed, new ArrayList<>(curr), b, d);
				}
			}
		} else {
			for(Edge e : currEdges){
				if(!ev[e.id]){
					toB += e.shutdown;
					ev[e.id] = true;
				}
			}
		}
	}
	public static void dijkstra(Node[] nodes, int a, int b){
		PriorityQueue<State> q = new PriorityQueue<>();
		State start = new State(0, 0, nodes[a]);
		nodes[a].distToB = 0;
		q.add(start);
		while(!q.isEmpty()){
			State s = q.poll();
			if(s.v.id == b)break;
			for(Edge e : s.v.adj){
				if(e.v.distToB > s.l + e.length){
					e.v.distToB = s.l + e.length;
					q.add(new State(s.c + e.shutdown, s.l + e.length, e.v));
				}
			}
		}
	}
	public static void dfs2(int v, int dist, Node[] nodes, ArrayList<Edge> currEdges, ArrayList<Node> curr, int b, int d){
		curr.add(nodes[v]);
		if(v != b){
			for(Edge e : nodes[v].adj){
				if(!curr.contains(e.v) && dist + e.length + nodes[b].distToB <= d){
					ArrayList<Edge> ed = new ArrayList<>(currEdges);
					if(!ev[e.id] && !currEdges.contains(e)) ed.add(e);
					dfs(e.v.id, dist + e.length, nodes, ed, new ArrayList<>(curr), b, d);
				}
			}
		} else {
			System.out.println(dist);
			if(dist + nodes[b].distToB <= d){
				for(Edge e : currEdges){
					if(!ev[e.id]){
						toB += e.shutdown;
						ev[e.id] = true;
					}
				}
			}
		}
	}
}
