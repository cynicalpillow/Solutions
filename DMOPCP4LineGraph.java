import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCP4LineGraph {
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
	private static class Edge implements Comparable<Edge>{
		int u;
		int v;
		int w;
		public Edge(int x, int y, int z){
			u = x;
			v = y;
			w = z;
		}
		@Override
		public int compareTo(Edge a) {
			if(this.w < a.w)return -1;
			else if(this.w > a.w)return 1;
			return 0;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int k = s.nextInt();
		ArrayList<Edge> edges = new ArrayList<>();
		for(int i = 0; i <n-1; i++){
			int w = s.nextInt();
			edges.add(new Edge(i, i+1, w));
		}
		for(int i = 0; i < n; i++){
			if(i + k < n){
				edges.add(new Edge(i, i+k, 0));
			}
		}
		Collections.sort(edges);
		System.out.println(kruskals(edges, n));
	}
	public static int kruskals(ArrayList<Edge> edges, int n){
		int[] a = new int[n];
		for(int i = 0; i < n; i++)a[i] = i;
		int total = 0;
		for(Edge e : edges){
			if(!find(a, e.u, e.v)){
				total += e.w;
				union(a, e.u, e.v);
			}
		}
		return total;
	}
	public static void union(int[] a, int u, int v){
		int rootu = root(a, u);
		int rootv = root(a, v);
		if(rootu != rootv)a[rootu] = rootv;
	}
	public static int root(int[] a, int x){
		while(a[x] != x){
			x = a[x] = a[a[x]];
		}
		return x;
	}
	public static boolean find(int[] a, int u, int v){
		return (root(a, u) == root(a, v));
	}
}
