import java.util.*;
import java.math.*;
import java.io.*;
public class COCIACM {
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
		int[][] vals = new int[3][n];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < n; j++)vals[i][j] = s.nextInt();
		}
		int[][] dp = new int[3][n];
		int[][] dp2 = new int[3][n];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < n; j++){
				if(j == 0)dp[i][j] = vals[i][j];
				else dp[i][j] = dp[i][j-1] + vals[i][j];
			}
		}
		for(int i = 0; i < 3; i++){
			for(int j = n-1; j >= 0; j--){
				if(j == n-1)dp2[i][j] = vals[i][j];
				else dp2[i][j] = dp2[i][j+1] + vals[i][j];
			}
		}
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < n; j++)System.out.print(dp[i][j] + " ");
			System.out.println();
		}
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < n; j++)System.out.print(dp2[i][j] + " ");
			System.out.println();
		}
	}
}
