import java.util.*;
import java.math.*;
import java.io.*;
public class BrunoAndPumpkins {
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
	public static int[][] dp;
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int t = s.nextInt();
		Integer[] points = new Integer[n];
		for(int i = 0; i < n; i++){
			points[i] = s.nextInt();
		}
		Arrays.sort(points, new Comparator<Integer>(){
			@Override
			public int compare(Integer arg0, Integer arg1) {
				if(Math.abs(arg0 - 0) < Math.abs(arg1 - 0))return -1;
				else if(Math.abs(arg0 - 0) > Math.abs(arg1 - 0))return 1;
				return 0;
			}
		});
		dp = new int[t+1][t+1];
		for(int i = 0; i <= t; i++){
			for(int j = 0; j <= t; j++){
				dp[i][j] = -1;
			}
		}
		int[] y = new int[t+1];
		y[0] = 0;
		for(int i = 1; i <= t; i++){
			y[i] = points[i-1];
		}
		boolean[] visited = new boolean[y.length];
		visited[0] = true;
		System.out.println(recurse(y, 0, 0, 0, t, visited));
		for(int i = 0; i <= t; i++){
			for(int j = 0; j <= t; j++){
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static int recurse(int[] p, int id, int point, int c, int max, boolean[] visited){
		if(c == max){
			//System.out.println(point + " " + c);
			return dp[c][id] = 0;
		}
		if(dp[c][id] != -1)return dp[c][id];
		int total = Integer.MAX_VALUE;
		for(int i = 0; i < p.length; i++){
			if(id != i && !visited[i]){
				visited[i] = true;
				total = Math.min(recurse(p, i, p[i] , c+1, max, visited) + Math.abs(point-p[i]), total);
				System.out.println(total);
				visited[i] = false;
			}
		}
		return dp[c][id] = total;
	}
}
