import java.util.*;
import java.math.*;
import java.io.*;
public class CCCConvexHull {
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
	private static class Edge{
		int d;
		int w;
		int v;
		public Edge(int u, int z, int j){
			v= u;
			d= z;
			w= j;
		}
	}
	private static class State implements Comparable<State>{
		int dist;
		int wear;
		int v;
		public State(int v, int d, int w){
			this.v = v;
			wear = w;
			dist = d;
		}
		@Override
		public int compareTo(State arg0) {
			if(this.dist < arg0.dist)return -1;
			else if(this.dist > arg0.dist)return 1;
			return 0;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int k = s.nextInt();
		int n = s.nextInt();
		int m = s.nextInt();
		ArrayList<ArrayList<Edge>> a = new ArrayList<>();
		for(int i = 0; i < n; i++)a.add(new ArrayList<Edge>());
		for(int i = 0; i < m; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			int d = s.nextInt();
			int w = s.nextInt();
			a.get(x).add(new Edge(y, d, w));
			a.get(y).add(new Edge(x, d, w));
		}
		int start = s.nextInt()-1;
		int end = s.nextInt()-1;
		dijkstra(start, end, a, k, n);
	}
	public static void dijkstra(int s, int e, ArrayList<ArrayList<Edge>> a, int k, int n){
		int[][] dists = new int[n][k];
		for(int i = 0; i < n;i++){
			for(int j = 0; j < k; j++){
				dists[i][j] = Integer.MAX_VALUE;
			}
		}
		PriorityQueue<State> q = new PriorityQueue<>();
		dists[s][0] = 0;
		q.add(new State(s,0,0));
		while(!q.isEmpty()){
			State id = q.poll();
			dists[id.v][id.wear] = Math.min(id.dist, dists[id.v][id.wear]);
			if(id.v == e)break;
			for(Edge edge : a.get(id.v)){
				if(edge.w + id.wear >= k)continue;
				if(dists[edge.v][edge.w+id.wear] > id.dist + edge.d) {
					q.add(new State(edge.v, id.dist + edge.d, id.wear+edge.w));
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < k; i++){
			min = Math.min(dists[e][i], min);
		}
		if(min == Integer.MAX_VALUE)System.out.println(-1);
		else System.out.println(min);
	}
}
