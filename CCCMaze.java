import java.util.*;
import java.math.*;
import java.io.*;
public class CCCMaze {
	/*static class Reader {
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
		}*/
	static int[][] grid;
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		int q = Integer.parseInt(s.readLine());
		for(int i = 0; i < q; i++){
			int r = Integer.parseInt(s.readLine());
			int c = Integer.parseInt(s.readLine());
			grid = new int[r][c];
			for(int j = 0; j < r; j++){
				for(int z = 0; z < c; z++){
					grid[j][z] = Integer.MAX_VALUE;
				}
			}
			char[][] a = new char[r][c];
			for(int j = 0; j < r; j++){
				String y = s.readLine();
				for(int z = 0; z < y.length(); z++){
					a[j][z] = y.charAt(z);
				}
			}
			dfs(0, 0, a, 1);
			if(grid[r-1][c-1] == Integer.MAX_VALUE)System.out.println(-1);
			else System.out.println(grid[r-1][c-1]);
		}
	}
	public static void dfs(int r, int c, char[][] a, int count){
		if(r < 0 || r > a.length-1)return;
		if(c < 0 || c > a[r].length-1)return;
		if(a[r][c] == '*')return;
		if(r == a.length-1 && c == a[0].length-1){
			if(count < grid[r][c]){
				grid[r][c] = count;
				return;
			}
		}
		int n = Integer.MAX_VALUE;
		if(a[r][c] == '+' && grid[r][c] > count){
			grid[r][c] = count;
			dfs(r+1, c, a, count+1);
			dfs(r, c+1, a, count+1);
			dfs(r, c-1, a, count+1);
			dfs(r-1, c, a, count+1);
		} else if (a[r][c] == '-' && grid[r][c] > count){
			grid[r][c] = count;
			dfs(r, c+1, a, count+1);
			dfs(r, c-1, a, count+1);
		} else if (a[r][c] == '|' && grid[r][c] > count){
			grid[r][c] = count;
			dfs(r+1, c, a, count+1);
			dfs(r-1, c, a, count+1);
		}
	}
}
