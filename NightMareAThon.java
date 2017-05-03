import java.util.*;
import java.math.*;
import java.io.*;
public class NightMareAThon {
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
		int count;
		int max;
		public Node(int m, int c){
			max = m;
			count = c;
		}
	}
	public static Node[] tree;
	public static int[] a;
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int q = s.nextInt();
		a = new int[n];
		tree = new Node[(int) (2 * Math.pow(2, Math.floor(Math.log((double)n) / Math.log(2)) + 1))];
		for(int i = 0; i < n; i++){
			a[i] = s.nextInt();
		}
		build(1, 0, n-1);
		StringBuilder xxx = new StringBuilder();
		for(int i = 0; i < q; i++){
			int l = s.nextInt()-1;
			int r = s.nextInt()-1;
			int maxi = 0;
			int count = 0;
			if(l == 0){
				maxi =query(1, 0, n-1, r+1, n-1);
				count = queryCount(1, 0, n-1, r+1, n-1, maxi);
			} else if(r == n-1){
				maxi =query(1, 0, n-1, 0, l-1);
				count = queryCount(1, 0, n-1, 0, l-1, maxi);
			} else {
				int x = query(1, 0, n-1, r+1, n-1);
				int y = query(1, 0, n-1, 0, l-1);
				maxi = Math.max(x, y);
				count = queryCount(1, 0, n-1, r+1, n-1, maxi) + queryCount(1, 0, n-1, 0, l-1, maxi);
			}
			xxx.append(maxi + " " + count + "\n");
		}
		System.out.println(xxx.toString());
	}
	public static void build(int node, int start, int end){
		if(start == end){
			tree[node] = new Node(a[start], 1);
		} else {
			int mid = (start + end)/2;
			build(2*node, start, mid);
			build(2*node+1, mid+1, end);
			int c = 1;
			if(tree[2*node+1].max == tree[2*node].max)c = tree[2*node+1].count + tree[2*node].count;
			else if(tree[2*node+1].max > tree[2*node].max)c = tree[2*node+1].count;
			else c = tree[2*node].count;
			tree[node] = new Node(Math.max(tree[2*node].max, tree[2*node+1].max), c);
		}
	}
	public static int query(int node, int start, int end, int l, int r){
		if(r < start || end < l)return 0;
		else if(l <= start && end <= r)return tree[node].max;
		else {
			int mid = (start + end)/2;
			int p1 = query(2*node, start, mid, l, r);
			int p2 = query(2*node+1, mid+1, end, l, r);
			return Math.max(p1, p2);
		}
	}
	public static int queryCount(int node, int start, int end, int l, int r, int max){
		if(r < start || end < l)return 0;
		else if(l <= start && end <= r){
			if(tree[node].max == max) return tree[node].count;
			else return 0;
		} else {
			int mid = (start + end)/2;
			int p1 = queryCount(2*node, start, mid, l, r, max);
			int p2 = queryCount(2*node+1, mid+1, end, l, r, max);
			return p1 + p2;
		}
	}
}
