import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCP5HappyTeachers {
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
		int[] happy = new int[n+1];
		int[] decrease = new int[n+1];
		int[] prep = new int[n+1];
		for(int i= 1; i <= n; i++){
			happy[i] = s.nextInt();
			decrease[i] = s.nextInt();
			prep[i] = s.nextInt();
		}
		int st = s.nextInt();
		int[][] precomp = new int[n+1][st+1];
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= st; j++){
				if(j - prep[i] >= 0) precomp[i][j] = precomp[i][j-prep[i]] + (happy[i] - decrease[i]*(j/prep[i]-1));
			}
		}
		int max = Integer.MIN_VALUE;
		int minDuration = Integer.MAX_VALUE;
		int[][] dp = new int[n+1][st+1];
		int[][] min = new int[n+1][st+1];
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= st; j++){
				dp[i][j] = dp[i-1][j];
				int div = j/prep[i];
				if(dp[i][j] > precomp[i][j]) min[i][j] = min[i-1][j];
				else if(dp[i][j] == precomp[i][j])min[i][j] = Math.min(min[i-1][j], div);
				else {
					dp[i][j] = precomp[i][j];
					min[i][j] = div;
				}
				int de = 0;
				for(int z = 0; z < div; z++){
					de += z * decrease[i];
					int happyx = Math.max((happy[i]*(z+1) - de), 0);
					if(dp[i-1][j-(prep[i]*(z+1))]+happyx > dp[i][j]){
						dp[i][j] = dp[i-1][j-(prep[i]*(z+1))]+happyx;
						min[i][j] = min[i-1][j-(prep[i]*(z+1))]+z+1;
					} else if (dp[i-1][j-(prep[i]*(z+1))]+happyx == dp[i][j]) min[i][j] = Math.min(min[i-1][j-(prep[i]*(z+1))]+z+1, min[i][j]);
				}
			}
		}
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= st; j++){
				if(dp[i][j] > max){
					max = dp[i][j];
					minDuration = min[i][j];
				} else if(dp[i][j] == max) minDuration = Math.min(min[i][j], minDuration);
			}
		}
		System.out.println(max + "\n" + minDuration);
	}
}
