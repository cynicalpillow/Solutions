import java.util.*;
import java.math.*;
import java.io.*;
public class MostlyTalking {
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
	public static class State implements Comparable<State>{
		int time;
		Node curr;
		boolean secretUsed;
		public State(Node c, int t, boolean b){
			time = t;
			curr = c;
			secretUsed = b;
		}
		@Override
		public int compareTo(State a) {
			if(this.time < a.time)return -1;
			else if(this.time > a.time)return 1;
			return 0;
		}
	}
	public static class Node {
		int id;
		ArrayList<Edge> adj = new ArrayList<>();
		ArrayList<Edge> secret = new ArrayList<>();
		int dist = Integer.MAX_VALUE;
		int distSecret = Integer.MAX_VALUE;
		public Node(int i){
			id = i;
		}
	}
	public static class Edge {
		Node v;
		int w;
		public Edge(Node u, int we){
			v = u;
			w = we;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int m = s.nextInt();
		Node[] nodes = new Node[n];
		for(int i = 0; i < n; i++)nodes[i] = new Node(i);
		for(int i = 0; i < m; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			int w = s.nextInt();
			nodes[x].adj.add(new Edge(nodes[y], w));
		}
		int d = s.nextInt();
		for(int i = 0; i < d; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			int w = s.nextInt();
			nodes[x].secret.add(new Edge(nodes[y], w));
		}
		dijkstra(nodes, n);
	}
	public static void dijkstra(Node[] nodes, int n){
		PriorityQueue<State> q = new PriorityQueue<>();
		q.add(new State(nodes[0], 0, false));
		nodes[0].dist = 0;
		nodes[0].distSecret = 0;
		while(!q.isEmpty()){
			State x = q.poll();
			Node curr = x.curr;
			if(curr.id == n-1)break;
			for(Edge e : curr.adj){
				if(!x.secretUsed && x.time + e.w < e.v.dist){
					e.v.dist = x.time+e.w;
					q.add(new State(e.v, x.time+e.w, x.secretUsed));
				} else if(x.secretUsed && x.time + e.w < e.v.distSecret){
					e.v.distSecret = x.time+e.w;
					q.add(new State(e.v, x.time+e.w, x.secretUsed));
				}
			}
			if(!x.secretUsed){
				for(Edge e : curr.secret){
					if(x.time + e.w < e.v.distSecret){
						e.v.distSecret = x.time+e.w;
						q.add(new State(e.v, x.time+e.w, true));
					}
				}
			}
		}
		if(nodes[n-1].dist != Integer.MAX_VALUE || nodes[n-1].distSecret != Integer.MAX_VALUE)System.out.println(Math.min(nodes[n-1].dist, nodes[n-1].distSecret));
		else System.out.println(-1);
	}
}
