import java.util.*;
import java.math.*;
import java.io.*;
public class SSP {
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
		int id;
		int weight = Integer.MAX_VALUE;
		ArrayList<Edge> adj = new ArrayList<>();
		public Node(int i){
			id = i;
		}
		public int compareTo(Node n){
			if(this.weight < n.weight)return -1;
			else if (this.weight > n.weight) return 1;
			return 0;
		}
	}
	public static class Edge {
		Node x;
		Node y;
		int weight;
		public Edge(Node z, Node c, int w){
			x = z;
			y = c;
			weight = w;
		}
	}
	public static void main(String args[]) throws Exception {
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		Reader s = new Reader();
		int n = s.nextInt();
		Node[] nodes = new Node[n];
		for(int i = 0; i < n; i++){
			nodes[i] = new Node(i);
		}
		int m = s.nextInt();
		for(int i = 0; i < m; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			int w = s.nextInt();
			Edge e = new Edge(nodes[x], nodes[y], w);
			Edge e1 = new Edge(nodes[y], nodes[x], w);
			nodes[x].adj.add(e);
			nodes[y].adj.add(e1);
		}
		nodes[0].weight = 0;
		dijkstra(nodes, n);
	}
	public static void dijkstra(Node[] nodes, int n){
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(nodes[0]);
		while(!q.isEmpty()){
			Node x = q.poll();
			//System.out.println(x.id);
			for(Edge e : x.adj){
				//System.out.println("adj " + e.y.id);
				if(e.y.weight > x.weight + e.weight){
					e.y.weight = x.weight + e.weight;
					q.add(e.y);
				}
			}
		}
		for(int i = 0; i < n; i++){
			if(nodes[i].weight == Integer.MAX_VALUE)System.out.println(-1);
			else System.out.println(nodes[i].weight);
		}
	}
}
