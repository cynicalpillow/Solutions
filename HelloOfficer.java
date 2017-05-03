import java.util.*;
import java.math.*;
import java.io.*;
public class HelloOfficer {
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
	private static class House implements Comparable<House>{
		ArrayList<Edge> adj = new ArrayList<>();
		int w = Integer.MAX_VALUE;
		public House(){
		}
		public int compareTo(House arg0) {
			if(this.w < arg0.w)return -1;
			else if(this.w > arg0.w)return 1;
			return 0;
		}
	}
	private static class Edge{
		House v;
		int w;
		public Edge(House h, int s){
			w = s;
			v= h;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int m = s.nextInt();
		int b = s.nextInt()-1;
		int q = s.nextInt();
		House[] houses = new House[n];
		for(int i= 0; i < n; i++){
			houses[i] = new House();
			if(i == b)houses[i].w = 0;
		}
		for(int i = 0; i < m; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			int z = s.nextInt();
			houses[x].adj.add(new Edge(houses[y], z));
			houses[y].adj.add(new Edge(houses[x], z));
		}
		dijkstra(b, houses);
		for(int i = 0; i < q; i++){
			int z = s.nextInt()-1;
			if(houses[z].w == Integer.MAX_VALUE)System.out.println(-1);
			else System.out.println(houses[z].w);
		}
	}
	public static void dijkstra(int b, House[] houses){
		PriorityQueue<House> q = new PriorityQueue<>();
		q.add(houses[b]);
		while(!q.isEmpty()){
			House h = q.poll();
			for(Edge e : h.adj){
				if(e.w + h.w < e.v.w){
					e.v.w = e.w + h.w;
					q.add(e.v);
				}
			}
		}
	}
}
