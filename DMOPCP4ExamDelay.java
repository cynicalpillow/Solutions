import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCP4ExamDelay {
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
		int id;
		ArrayList<Edge> adj = new ArrayList<>();
		public Node(int i){
			id = i;
		}
	}
	private static class Edge {
		Node v;
		double w;
		int s;
		int km;
		public Edge (Node u, int speed, int kem){
			v = u;
			s = speed;
			km = kem;
			w = (double)((double)kem/(double)s)*60.0;
		}
	}
	private static class State implements Comparable<State>{
		ArrayList<Edge> edges = new ArrayList<>();
		double dist;
		int es;
		Node n;
		public State(double d, int e, Node curr){
			dist = d;
			es = e;
			n = curr;
		}
		@Override
		public int compareTo(State a) {
			if(this.dist < a.dist)return -1;
			else if(this.dist > a.dist)return 1;
			return 0;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int e = s.nextInt();
		Node[] nodes = new Node[n];
		for(int i= 0; i < n; i++)nodes[i] = new Node(i);
		for(int i = 0; i < e; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			int km = s.nextInt();
			int speed = s.nextInt();
			nodes[x].adj.add(new Edge(nodes[y], speed, km));
			nodes[y].adj.add(new Edge(nodes[x], speed, km));
		}
		State x = dijkstra(nodes, n);
		System.out.println(x.es);
		double totaldelay = 0;
		for(Edge y : x.edges){
			totaldelay += y.km/(y.s*0.75)*60.0;
		}
		System.out.println(Math.round(totaldelay - x.dist));
	}
	public static State dijkstra(Node[] nodes, int n){
		PriorityQueue<State> q = new PriorityQueue<>();
		double[] dists = new double[n];
		for(int i = 0; i < n; i++)dists[i] = Integer.MAX_VALUE;
		dists[0] = 0;
		State x = new State(Integer.MAX_VALUE, Integer.MAX_VALUE, nodes[n-1]);
		q.add(new State(0, 0, nodes[0]));
		while(!q.isEmpty()){
			State z = q.poll();
			if(z.n.id == n-1){
				if(z.dist < x.dist)x = z;
				else if(z.dist == x.dist){
					if(z.es < x.es)x = z;
				}
			}
			for(Edge e : z.n.adj){
				if(dists[e.v.id] > z.dist + e.w){
					dists[e.v.id] = z.dist + e.w;
					State y = new State(dists[e.v.id], z.es+1, e.v);
					for(Edge c : z.edges)y.edges.add(c);
					y.edges.add(e);
					q.add(y);
				}
			}
		}
		return x;
	}
}
