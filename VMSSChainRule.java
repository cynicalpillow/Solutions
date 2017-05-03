import java.util.*;
import java.math.*;
import java.io.*;
public class VMSSChainRule {
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
	public static class Node implements Comparable<Node>{
		int id;
		ArrayList<Edge> adj = new ArrayList<>();
		int dist = Integer.MAX_VALUE;
		public Node(int i){
			id = i;
		}
		@Override
		public int compareTo(Node a) {
			if(this.dist < a.dist)return -1;
			else if(this.dist > a.dist)return 1;
			return 0;
		}
	}
	public static class Edge {
		Node v;
		int w;
		public Edge(Node x, int we){
			v = x;
			w = we;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int m = s.nextInt();
		Node[] nodes = new Node[n];
		for(int i = 0; i < n; i++)nodes[i] = new Node(i);
		for(int j = 0; j < m; j++){
			Node u = nodes[s.nextInt()];
			Node v = nodes[s.nextInt()];
			int w = s.nextInt();
			u.adj.add(new Edge(v, w));
			v.adj.add(new Edge(u, w));
		}
		dijkstraAndSearch(nodes, n);
	}
	public static void dijkstraAndSearch(Node[] nodes, int n){
		int[] distFrom = new int[n];
		nodes[0].dist = 0;
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(nodes[0]);
		while(!q.isEmpty()){
			Node c = q.poll();
			for(Edge e : c.adj){
				if(e.v.dist > c.dist + e.w){
					e.v.dist = c.dist + e.w;
					distFrom[e.v.id] = c.dist + e.w;
					q.add(e.v);
				}
			}
		}
		for(int i = 0; i < n; i++)nodes[i].dist = Integer.MAX_VALUE;
		q = new PriorityQueue<>();
		int[] distTo = new int[n];
		nodes[n-1].dist = 0;
		q.add(nodes[n-1]);
		while(!q.isEmpty()){
			Node c = q.poll();
			for(Edge e : c.adj){
				if(e.v.dist > c.dist + e.w){
					e.v.dist = c.dist + e.w;
					distTo[e.v.id] = c.dist + e.w;
					q.add(e.v);
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++)max = Math.max(max, distFrom[i] + distTo[i]);
		System.out.println(max);
	}
}
