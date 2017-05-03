import java.util.*;
import java.math.*;
import java.io.*;
public class CCCBowlingForNumbers {
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
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int t = s.nextInt();
		for(int q = 0; q < t; q++){
			int n = s.nextInt();
			int k = s.nextInt();
			int w = s.nextInt();
			int[] a = new int[n+1];
			int[] sums = new int[n+1];
			for(int i = 1; i < n+1; i++){
				a[i] =s.nextInt();
				sums[i] =sums[i-1] + a[i];
			}
			int[][] dp = new int[k+1][n+1];
			int max = 0;
			for(int i = 1; i < k+1; i++){
				for(int j = 1; j < n+1; j++){
					int totalThis = sums[j] - sums[(Math.max(j-w, 0))];
					dp[i][j] = totalThis;
					if(i == 1)dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);
					else {
						if(j - w>= 0)dp[i][j] = Math.max(dp[i][j], dp[i-1][j-w] + totalThis);
						dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);
					}
					max = Math.max(dp[i][j], max);
				}
			}
			System.out.println(max);
		}
	}
}
