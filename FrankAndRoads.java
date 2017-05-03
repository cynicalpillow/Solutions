import java.util.*;
import java.math.*;
import java.io.*;
public class FrankAndRoads {
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
	private static class Node implements Comparable<Node>{
		boolean foodBasic = false;
		int id;
		ArrayList<Edge> adj = new ArrayList<>();
		int weight= Integer.MAX_VALUE;
		public Node(int n){
			id = n;
		}
		@Override
		public int compareTo(Node arg0) {
			if(this.weight < arg0.weight)return -1;
			else if(this.weight > arg0.weight)return 1;
			return 0;
		}
	}
	private static class Edge{
		Node v;
		int weight;
		public Edge(Node i, int w){
			v= i;
			weight=w;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int b = s.nextInt();
		int r = s.nextInt();
		int g = s.nextInt();
		Node[] nodes = new Node[b+1];
		for(int i = 0; i < b+1; i++){
			nodes[i] = new Node(i);
		}
		for(int i= 0; i < g; i++){
			nodes[s.nextInt()].foodBasic = true;
		}
		for(int i = 0; i < r; i++){
			int x = s.nextInt();
			int y = s.nextInt();
			nodes[x].adj.add(new Edge(nodes[y], s.nextInt()));
		}
		dijkstra(nodes, n);
	}
	public static void dijkstra(Node[] nodes, int max){
		nodes[0].weight = 0;
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(nodes[0]);
		while(!q.isEmpty()){
			Node n = q.poll();
			for(Edge e : n.adj){
				if(e.v.weight > n.weight + e.weight){
					e.v.weight = n.weight + e.weight;
					if(q.contains(e.v))q.remove(e.v);
					q.add(e.v);
				}
			}
		}
		int total = 0;
		for(Node x : nodes){
			if(x.foodBasic && x.weight <= max)total++;
		}
		System.out.println(total);
	}
}
