import java.util.*;
import java.math.*;
import java.io.*;
public class CCCShopandShip {
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
	private static class Shop{
		int id;
		int cost;
		public Shop(int i, int c){
			id = i;
			cost = c;
		}
	}
	public static void main(String args[]) throws Exception {
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		Reader s = new Reader();
		int n = s.nextInt();
		int[][] matrix = new int[n][n];
		int t = s.nextInt();
		for(int i = 0; i < t; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			int c = s.nextInt();
			matrix[x][y] = c;
			matrix[y][x] = c;
		}
		int k = s.nextInt();
		Shop[] prices = new Shop[k];
		for(int i = 0; i < k; i++){
			int z = s.nextInt()-1;
			int c = s.nextInt();
			prices[i] = new Shop(z, c);
		}
		int d = s.nextInt()-1;
		int[] dists = dijkstra(d, matrix);
		int min = Integer.MAX_VALUE;
		for(Shop sh : prices){
			int curr = sh.cost;
			if(sh.id == d)min = Math.min(min, curr);
			else {
				int x = dists[sh.id];
				curr+=x;
				min = Math.min(min, curr);
			}
		}
		System.out.println(min);
	}
	public static int[] dijkstra(int i, int[][] matrix){
		int[] dists = new int[matrix.length];
		boolean[] visited = new boolean[matrix.length];
		Arrays.fill(dists, Integer.MAX_VALUE);
		dists[i] = 0;
		for(int j = 0; j < matrix.length; j++){
			int id = findMinimum(dists, visited);
			if(id == -1)continue;
			for(int x = 0; x < matrix[id].length; x++){
				if(matrix[id][x] != 0 && dists[x] > dists[id] + matrix[id][x]){
					dists[x] = dists[id] + matrix[id][x];
				}
			}
			visited[id] = true;
		}
		return dists;
	}
	public static int findMinimum(int[] dists, boolean[] visited){
		int id = -1;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < dists.length; i++){
			if(dists[i] < min && !visited[i]){
				id = i;
				min = dists[i];
			}
		}
		return id;
	}
}
