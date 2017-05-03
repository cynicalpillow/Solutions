import java.util.*;
import java.math.*;
import java.io.*;
public class CCCNukit {
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
		int q = s.nextInt();
		boolean[][][][] dp = new boolean[31][31][31][31];
		for(int i = 0; i <= 30; i++){
			for(int j = 0; j <= 30; j++){
				for(int z = 0; z <= 30; z++){
					for(int y = 0; y <= 30; y++){
						boolean check = false;
						//AABDD
						if(i - 2 >= 0 && j - 1 >= 0 && y - 2 >= 0){
							if(dp[i-2][j-1][z][y-2])check |= false;
							else check |= true;
						}
						//ABCD
						if(i - 1 >= 0 && j - 1 >= 0 && z-1 >= 0 && y - 1 >= 0){
							if(dp[i-1][j-1][z-1][y-1])check |= false;
							else check |= true;
						}
						//CCD
						if(z-2 >= 0 && y - 1 >= 0){
							if(dp[i][j][z-2][y-1])check |= false;
							else check |= true;
						}
						//BBB
						if(j - 3 >= 0){
							if(dp[i][j-3][z][y])check |= false;
							else check |= true;
						}
						//AD
						if(i - 1 >= 0 && y - 1 >= 0){
							if(dp[i-1][j][z][y-1])check |= false;
							else check |= true;
						}
						dp[i][j][z][y] = check;
					}
				}
			}
		}
		for(int qq = 0; qq < q; qq++){
			int a = s.nextInt();
			int b = s.nextInt();
			int c = s.nextInt();
			int d = s.nextInt();
			if(dp[a][b][c][d])System.out.println("Patrick");
			else System.out.println("Roland");
		}
	}
}
