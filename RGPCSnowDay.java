import java.util.*;
import java.math.*;
import java.io.*;
public class RGPCSnowDay {
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
		int max = Integer.MIN_VALUE;
		int maxPoints = Integer.MIN_VALUE;
		int id;
		ArrayList<Edge> adj = new ArrayList<>();
		public Node(int i){
			id = i;
		}
	}
	private static class Edge {
		Node v;
		int d;
		public Edge(Node v, int d){
			this.v = v;
			this.d = d;
		}
	}
	static Node[] nodes;
	static int n;
	static boolean[] visited;
	static boolean[] onStack;
	static ArrayDeque<Node> a = new ArrayDeque<>();
	static boolean loop = false;
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		n = s.nextInt();
		int m = s.nextInt();
		nodes = new Node[n];
		visited = new boolean[n];
		onStack = new boolean[n];
		for(int i = 0; i < n; i++){
			nodes[i] = new Node(i);
		}
		for(int i = 0; i < m; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			int d = s.nextInt();
			nodes[x].adj.add(new Edge(nodes[y], d));
		}
		topologicalSort(nodes[0]);
		if(loop)System.out.println(-1);
		else {
			nodes[0].max = 0;
			nodes[0].maxPoints = 1;
			for(Node x : a){
				for(Edge e : x.adj){
					if(e.v.max < x.max + e.d){
						e.v.max = x.max+e.d;
						e.v.maxPoints = x.maxPoints+1;
					} else if(e.v.max == x.max + e.d){
						e.v.maxPoints = Math.max(e.v.maxPoints, x.maxPoints);
					}
				}
			}
			if(nodes[n-1].max == Integer.MIN_VALUE)System.out.println(-1);
			else System.out.println(nodes[n-1].max + " " + nodes[n-1].maxPoints);
		}
	}
	public static void topologicalSort(Node focus){
		visited[focus.id] = true;
		onStack[focus.id] = true;
		for(Edge e : focus.adj){
			if(!visited[e.v.id]){
				topologicalSort(e.v);
			} else if(onStack[e.v.id]){
				loop = true;
				return;
			}
		}
		onStack[focus.id] = false;
		a.push(focus);
	}
}
