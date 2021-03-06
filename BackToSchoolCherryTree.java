import java.util.*;
import java.math.*;
import java.io.*;
public class BackToSchoolCherryTree {
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
	private static class Node{
		int cherries = 0;
		int weight = 0;
		int id;
		ArrayList<Edge> adj = new ArrayList<Edge>();
		public Node(int i){
			id = i;
		}
	}
	private static class Edge{
		int weight;
		Node u;
		Node v;
		public Edge(Node x, Node y, int w){
			u = x;
			v = y;
			weight = w;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int c = s.nextInt();
		int k = s.nextInt();
		Edge[] edges = new Edge[n-1];
		Node[] nodes = new Node[n];
		for(int i= 0; i < n; i++){
			nodes[i] = new Node(i);
			nodes[i].cherries = s.nextInt();
		}
		for(int i = 0; i < n-1; i++){
			int u = s.nextInt()-1;
			int v = s.nextInt()-1;
			int w = s.nextInt();
			Edge e = new Edge(nodes[u], nodes[v], w);
			edges[i] = e;
			nodes[u].adj.add(e);
		}
		for(int i = 0; i < n; i++){
			bfs(nodes[i]);
		}
		int total = 0;
		for(int i = 0; i < n-1; i++){
			Edge e = edges[i];
			if(e.v.weight+e.weight <= k && e.v.cherries >= c)total++;
		}
		System.out.println(total);
	}
	public static void bfs(Node n){
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(n);
		while(!q.isEmpty()){
			Node x = q.poll();
			if(x != n){
				n.cherries += x.cherries;
			}
			for(Edge e : x.adj){
				q.add(e.v);
				n.weight+=e.weight;
			}
		}
	}
}
