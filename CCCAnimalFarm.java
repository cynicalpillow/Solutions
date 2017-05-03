import java.util.*;
import java.math.*;
import java.io.*;
public class CCCAnimalFarm {
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
	private static class Pen {
		ArrayList<Edge> edges = new ArrayList<>();
		boolean[] visited;
		int id;
		public Pen(int i){
			id = i;
		}
	}
	private static class Fence implements Comparable<Fence>{
		Pen one;
		Pen two;
		int w;
		public Fence(Pen o, Pen t, int we){
			w = we;
			one = o;
			two = t;
		}
		@Override
		public int compareTo(Fence a) {
			if(this.w < a.w)return -1;
			else if(this.w > a.w)return 1;
			return 0;
		}
	}
	private static class Edge implements Comparable<Edge>{
		int[] v = new int[2];
		int w;
		public Edge(int one, int two, int we){
			w = we;
			v[0] = one;
			v[1] = two;
			Arrays.sort(v);
		}
		@Override
		public int compareTo(Edge o) {
			if(this.w < o.w)return -1;
			else if(this.w > o.w)return 1;
			return 0;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		Pen[] pens = new Pen[n+1];
		for(int i = 0; i < n+1; i++){
			pens[i] = new Pen(i);
		}
		for(int i = 0; i < n; i++){
			int e = s.nextInt();
			ArrayList<Integer> vs = new ArrayList<>();
			ArrayList<Integer> we = new ArrayList<>();
			for(int j = 0; j < e; j++) vs.add(s.nextInt());
			for(int j = 0; j < e; j++) we.add(s.nextInt());
			for(int j = 0; j < e; j++){
				if(j == e-1){
					Edge x = new Edge(vs.get(j), vs.get(0), we.get(j));
					pens[i].edges.add(x);
				} else {
					Edge x = new Edge(vs.get(j), vs.get(j+1), we.get(j));
					pens[i].edges.add(x);
				}
			}
			pens[i].visited = new boolean[e];
		}
		ArrayList<Fence> fences = new ArrayList<>();
		for(int i = 0; i < n; i++){
			for(int j = i+1; j < n; j++){
				int c = 0;
				for(Edge e : pens[i].edges){
					int z = 0;
					for(Edge x : pens[j].edges){
						if(e.v[0] == x.v[0] && e.v[1] == x.v[1]){
							Fence f = new Fence(pens[i], pens[j], e.w);
							fences.add(f);
							pens[i].visited[c] = true;
							pens[j].visited[z] = true;
						}
						z++;
					}
					c++;
				}
			}
			int c = 0;
			for(Edge e : pens[i].edges){
				if(!pens[i].visited[c]){
					Fence f = new Fence(pens[n], pens[i], e.w);
					fences.add(f);
				}
				c++;
			}
		}
		System.out.println(kruskals(fences, pens));
	}
	private static int kruskals(ArrayList<Fence> fences, Pen[] pens) {
		Collections.sort(fences);
		int[] a = new int[pens.length];
		for(int i = 0; i < a.length; i++) a[i] = i;
		int total = 0;
		for(Fence f : fences){
			if(!find(a, f.one.id, f.two.id)){
				total += f.w;
				union(a, f.one.id, f.two.id);
			}
			boolean check = true;
			for(int i = 0; i < a.length-1; i++){
				for(int j = i+1; j < a.length-1; j++){
					if(!find(a, i, j)){
						check = false;
						break;
					}
				}
				if(!check)break;
			}
			if(!check){
				check = true;
				for(int j = 0; j < a.length-1; j++){
					if(!find(a, a.length-1, j)){
						check = false;
						break;
					}
				}
			}
			if(check)break;
		}
		return total;
	}
	public static void union(int[] a, int x, int y){
		int rootx = root(a, x);
		int rooty = root(a, y);
		a[rootx] = rooty;
	}
	private static int root(int[] a, int x) {
		while(x != a[x]){
			x = a[x] = a[a[x]];
		}
		return x;
	}
	private static boolean find(int[] a, int x, int y){
		return root(a, x) == root(a, y);
	}
}
