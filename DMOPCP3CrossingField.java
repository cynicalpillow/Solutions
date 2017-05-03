import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCP3CrossingField {
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
		int h = s.nextInt();
		int[][] a = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				a[i][j] = s.nextInt();
			}
		}
		boolean[][] visited = new boolean[n][n];
		if(dfs(0, 0, a, visited, h))System.out.println("yes");
		else System.out.println("no");
	}
	public static boolean dfs(int r, int c, int[][] a, boolean[][] visited, int h){
		visited[r][c] = true;
		if(r == a.length-1 && c == a.length-1)return true;
		boolean x = false;
		if(r - 1 >= 0 && Math.abs(a[r-1][c] - a[r][c]) <= h && !visited[r-1][c]){
			x = dfs(r-1, c, a, visited, h);
			if(x)return true;
		}
		if(r + 1 <= a.length-1 && Math.abs(a[r+1][c] - a[r][c]) <= h && !visited[r+1][c]){
			x = dfs(r+1, c, a, visited, h);
			if(x)return true;
		}
		if(c - 1 >= 0 && Math.abs(a[r][c-1] - a[r][c]) <= h && !visited[r][c-1]){
			x = dfs(r, c-1, a, visited, h);
			if(x)return true;
		}
		if(c + 1 <= a.length-1 && Math.abs(a[r][c+1] - a[r][c]) <= h && !visited[r][c+1]){
			x = dfs(r, c+1, a, visited, h);
			if(x)return true;
		}
		return x;
	}
}
