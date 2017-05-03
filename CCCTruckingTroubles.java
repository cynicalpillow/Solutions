import java.util.*;
import java.math.*;
import java.io.*;
public class CCCTruckingTroubles {
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
	public static class Edge implements Comparable<Edge>{
		int x;
		int y;
		int w;
		public Edge(int x, int y, int w){
			this.x = x;
			this.y = y;
			this.w = w;
		}
		@Override
		public int compareTo(Edge a) {
			if(this.w > a.w)return -1;
			else if(this.w < a.w)return 1;
			return 0;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int c = s.nextInt();
		int r = s.nextInt();
		int d = s.nextInt();
		Edge[] edges = new Edge[r];
		for(int i = 0; i < r; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			int w = s.nextInt();
			edges[i] = new Edge(x, y, w);
		}
		int[] dests = new int[d];
		for(int i = 0; i < d; i++){
			dests[i] = s.nextInt()-1;
		}
		Arrays.sort(edges);
		kruskal(edges, dests, c);
	}
	public static void kruskal(Edge[] edges, int[] dests, int c){
		int[] a = new int[c];
		boolean[] connected = new boolean[c];
		Edge minMax = edges[0];
		for(int i = 0; i < c; i++)a[i] = i;
		for(Edge e : edges){
			if(!find(e.x, e.y, a) && !visitDests(dests, connected)){
				minMax = e;
				union(e.x, e.y, a, connected);
			}
		}
		System.out.println(minMax.w);
	}
	public static boolean visitDests(int[] d, boolean[] c){
		for(int i = 0; i < d.length; i++){
			if(!c[d[i]])return false;
		}
		return true;
	}
	public static void union(int x, int y, int[] a, boolean[] connected){
		int rootx = root(x, a);
		int rooty = root(y, a);
		a[rootx] = rooty;
		connected[rootx] = true;
		connected[rooty] = true;
	}
	public static int root(int x, int[] a){
		while(x != a[x]){
			x = a[x] = a[a[x]] = a[a[a[x]]];
		}
		return x;
	}
	public static boolean find(int x, int y, int[] a){
		return root(x, a) == root(y, a);
	}
}
