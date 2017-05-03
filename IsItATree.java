import java.util.*;
import java.math.*;
import java.io.*;
public class IsItATree {
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
	static boolean isTree = true;
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int[][] matrix = new int[4][4];
		boolean[][] check = new boolean[4][4];
		for(int i= 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				matrix[i][j] = s.nextInt();
			}
		}
		int edges = 0;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(matrix[i][j] == 1 && !check[i][j]){
					check[j][i] = true;
					edges++;
				}
			}
		}
		if(edges < 3 || edges > 3)System.out.println("No");
		else {
			for(int i = 0; i < 4; i++)dfs(i, matrix, new boolean[4]);
			if(isTree)System.out.println("Yes");
			else System.out.println("No");
		}
	}
	public static void dfs(int x, int[][] matrix, boolean[] visited){
		visited[x] = true;
		for(int i = 0; i < 4; i++){
			if(matrix[x][i] == 1 && visited[i]){
				isTree = false;
				return;
			} else if(matrix[x][i] == 1 && !visited[i]){
				matrix[i][x] = 0;
				dfs(i, matrix, visited);
				matrix[i][x] = 1;
			}
		}
	}
}
