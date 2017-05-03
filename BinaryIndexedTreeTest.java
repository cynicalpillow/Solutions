import java.util.*;
import java.math.*;
import java.io.*;
public class BinaryIndexedTreeTest {
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
	public static long[] a;
	public static long[] b;
	public static int n;
	public static long[] count;
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		a = new long[n+1];
		b = new long[n+1];
		count = new long[100001];
		n = n+1;
		st = new StringTokenizer(s.readLine());
		for(int i = 1; i < n; i++){
			a[i] = Long.parseLong(st.nextToken());
			updateCount((int)a[i], 1);
			update(i, a[i]);
		}
		for(int i = 0; i < m; i++){
			st = new StringTokenizer(s.readLine());
			String y = st.nextToken();
			if(y.equals("C")){
				int z = Integer.parseInt(st.nextToken());
				long j = Long.parseLong(st.nextToken());
				updateCount((int)a[z], -1);
				updateCount((int)j, 1);
				update(z, j-a[z]);
				a[z] = j;
			} else if (y.equals("S")){
				int z = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				long sumz = query(z-1);
				long sumj = query(j);
				System.out.println(sumj - sumz);
			} else {
				int z = Integer.parseInt(st.nextToken());
				long total = queryCount(z);
				System.out.println(total);
			}
		}
	}
	public static void update(int x, long d){
		for(; x < n; x+= (x&(-x))){
			b[x] += d;
		}
	}
	public static void updateCount(int x, int d){
		for(; x < 100001; x+= (x&(-x))){
			count[x] += d;
		}
	}
	public static long queryCount(int x){
		long sum = 0;
		for(; x > 0; x-=(x&(-x))){
			sum+=count[x];
		}
		return sum;
	}
	public static long query(int x){
		long sum = 0;
		for(; x > 0; x-=(x&(-x))){
			sum+=b[x];
		}
		return sum;
	}
}
