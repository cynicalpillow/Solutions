import java.util.*;
import java.math.*;
import java.io.*;
public class VMSSPollos {
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
	public static class Stop {
		int fringy;
		long pos;
		public Stop(long p, int f){
			pos = p;
			fringy = f;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int k = s.nextInt();
		Stop[] restaurants = new Stop[n];
		int c = 0;
		for(int i = 0; i < n; i++){
			long pos = s.nextLong();
			int fringy = s.nextInt();
			restaurants[i] = new Stop(pos, fringy);
			if(fringy == 1)c++;
		}
		if(c > k){
			System.out.println("DEA Bust!");
			return;
		}
		long[] dp = new long[n];
		for(int i= 0; i < n; i++){
			dp[i] = Integer.MAX_VALUE;
		}
		dp[0] = restaurants[0].pos;
		for(int i = 1; i < n; i++){
			if(restaurants[i-1].fringy == 1){
				dp[i] = Math.max(restaurants[i].pos - restaurants[i-1].pos, dp[i-1]);
			} else {
				dp[i] = restaurants[i].pos;
				for(int j = i-1; j >= 0; j--){
					dp[i] = Math.min(Math.max(restaurants[i].pos - restaurants[i-1].pos, dp[i-1]), dp[i]);
					if(restaurants[j].fringy == 1)break;
				}
			}
		}
		if(dp[n-1] == Integer.MAX_VALUE)System.out.println("DEA Bust!");
		else System.out.println(dp[n-1]);
	}
}
