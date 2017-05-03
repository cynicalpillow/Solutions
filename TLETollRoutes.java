import java.util.*;
import java.math.*;
import java.io.*;
public class TLETollRoutes {
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
		long dist = Long.MAX_VALUE;
		ArrayList<Edge> adj = new ArrayList<>();
		public Node(int i){
			id = i;
		}
	}
	private static class Edge {
		long weight;
		Node v;
		public Edge(Node u, long w){
			v = u;
			weight = w;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int m = s.nextInt();
		int d = s.nextInt();
		Node[] nodes = new Node[n];
		for(int i = 0; i < n; i++){
			nodes[i] = new Node(i);
		}
		Edge[] edges = new Edge[m];
		for(int i = 0; i < m; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			int w = s.nextInt();
			Edge e = new Edge(nodes[y], w);
			nodes[x].adj.add(e);
			edges[i] = e;
		}
		for(int i = 0; i < d; i++){
			int c = s.nextInt();
			int dest = s.nextInt()-1;
			if(c != 0){
				for(Edge e : edges){
					e.weight += c;
				}
			}
			bellman(nodes, dest);
			for(Node z : nodes){
				z.dist = Long.MAX_VALUE;
			}
		}
	}
	public static void bellman(Node[] nodes, int dest){
		nodes[0].dist = 0;
		for(Node n : nodes){
			for(Edge e : n.adj){
				if(n.dist != Long.MAX_VALUE && e.v.dist > e.weight + n.dist){
					e.v.dist = e.weight + n.dist;
				}
			}
		}
		if(nodes[dest].dist == Long.MAX_VALUE)System.out.println("Cannot Deliver");
		else System.out.println(nodes[dest].dist);
	}
}
