import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCMigrantMascot {
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
	public static class Node {
		int pref = Integer.MIN_VALUE;
		ArrayList<Edge> adj = new ArrayList<>();
		int id;
		public Node(int i){
			id = i;
		}
	}
	public static class Edge {
		int pref;
		Node v;
		public Edge(Node x, int p){
			v = x;
			pref = p;
		}
	}
	public static class State implements Comparable<State>{
		int pref;
		Node n;
		public State(Node x, int p){
			n = x;
			pref = p;
		}
		@Override
		public int compareTo(State o) {
			if(this.pref > o.pref)return -1;
			else if(this.pref < o.pref)return 1;
			return 0;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int m = s.nextInt();
		Node[] nodes = new Node[n];
		for(int i = 0; i < n; i++){
			nodes[i] = new Node(i);
		}
		for(int i = 0; i < m; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			int p = s.nextInt();
			nodes[x].adj.add(new Edge(nodes[y], p));
			nodes[y].adj.add(new Edge(nodes[x], p));
		}
		nodes[0].pref = 0;
		dijkstra(nodes);
	}
	public static void dijkstra(Node[] nodes){
		PriorityQueue<State> q = new PriorityQueue<>();
		State start = new State(nodes[0], Integer.MAX_VALUE);
		q.add(start);
		while(!q.isEmpty()){
			State y = q.poll();
			Node z = y.n;
			for(Edge e : z.adj){
				if(e.v.id == 0)continue;
				int lowest = Math.min(y.pref, e.pref);
				if(lowest > e.v.pref){
					e.v.pref = lowest;
					q.add(new State(e.v, lowest));
				}
			}
		}
		for(int i = 0; i < nodes.length; i++){
			if(nodes[i].pref == Integer.MIN_VALUE)System.out.println(-1);
			else System.out.println(nodes[i].pref);
		}
	}
}
