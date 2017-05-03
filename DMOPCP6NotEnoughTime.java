import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCP6NotEnoughTime {
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
		int n = s.nextInt();
		int t = s.nextInt();
		int[] goodp = new int[n];
		int[] averagep = new int[n];
		int[] poorp = new int[n];
		int[] goodv = new int[n];
		int[] averagev = new int[n];
		int[] poorv = new int[n];
		for(int i= 0; i < n; i++){
			poorp[i] = s.nextInt();
			poorv[i] = s.nextInt();
			averagep[i] = s.nextInt();
			averagev[i] = s.nextInt();
			goodp[i] = s.nextInt();
			goodv[i] = s.nextInt();
		}
		int[][] dp = new int[2][t+1];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < t+1; j++){
				if(j == 0)dp[0][j] = 0;
				else if(i == 0){
					if(j - goodp[i] >= 0)dp[1][j] = Math.max(dp[1][j], goodv[i]);
					if(j - averagep[i] >= 0)dp[1][j] = Math.max(dp[1][j], averagev[i]);
					if(j - poorp[i] >= 0)dp[1][j] = Math.max(dp[1][j], poorv[i]);
					dp[1][j] = Math.max(dp[1][j-1], dp[1][j]);
				} else {
					dp[1][j] = Math.max(dp[1][j-1], dp[0][j]);
					if(j - goodp[i] >= 0)dp[1][j] = Math.max(dp[1][j], dp[0][j-goodp[i]] + goodv[i]);
					if(j - averagep[i] >= 0)dp[1][j] = Math.max(dp[1][j], dp[0][j-averagep[i]] + averagev[i]);
					if(j - poorp[i] >= 0)dp[1][j] = Math.max(dp[1][j], dp[0][j-poorp[i]] + poorv[i]);
				}
			}
			if(i >= 0)for(int j = 0; j < t+1; j++)dp[0][j] = dp[1][j];
		}
		System.out.println(dp[0][t]);
	}
}
