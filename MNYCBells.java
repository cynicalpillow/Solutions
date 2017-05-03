import java.util.*;
import java.math.*;
import java.io.*;
public class MNYCBells {
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
	public static class Bell {
		HashSet<Integer> freq = new HashSet<>();
	}
	public static int[] a;
	public static Bell[] tree;
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int q = s.nextInt();
		a = new int[n];
		tree = new Bell[4*n+1];
		for(int i= 0; i < n; i++){
			a[i] = s.nextInt();
		}
		build(1, 0, n-1);
		for(int i = 0; i < q; i++){
			int type = s.nextInt();
			if(type == 1){
				update(1, 0, n-1, s.nextInt()-1, s.nextInt());
			} else {
				System.out.println(query(1, 0, n-1, s.nextInt()-1, s.nextInt()-1));
			}
		}
	}
	public static void build(int node, int start, int end){
		if(start == end){
			tree[node] = new Bell();
			tree[node].freq.add(a[start]);
		} else {
			int mid = (start + end)/2;
			build(2*node, start, mid);
			build(2*node+1, mid+1, end);
			tree[node] = new Bell();
			tree[node].freq.addAll(tree[2*node+1].freq);
			tree[node].freq.addAll(tree[2*node].freq);
		}
	}
	public static void update(int node, int start, int end, int idx, int val){
		if(start == end){
			tree[node] = new Bell();
			tree[node].freq.add(val);
			a[idx] = val;
		} else {
			int mid = (start + end)/2;
			if(idx >= start && idx <= mid){
				update(2*node, start, mid, idx, val);
			} else {
				update(2*node+1, mid+1, end, idx, val);
			}
			tree[node] = new Bell();
			tree[node].freq.addAll(tree[2*node+1].freq);
			tree[node].freq.addAll(tree[2*node].freq);
		}
	}
	public static int query(int node, int start, int end, int l, int r){
		if(r < start || end < l){
			return 0;
		} else if (l <= start && end <= r){
			return tree[node].freq.size();
		} else {
			int mid = (start + end)/2;
			int o = query(2*node, start, mid, l, r);
			int p = query(2*node+1, mid+1, end, l, r);
			return o + p;
		}
	}
}
