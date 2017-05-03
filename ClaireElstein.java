import java.util.*;
import java.math.*;
import java.io.*;
public class ClaireElstein {
	/*
	 * 
	 5 6
	 1 2
	 2 4
	 2 5
	 4 5
	 5 3
	 2 3
	 * 
	 */
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
	private static class Node {
		int id;
		int indegree = 0;
		int outdegree = 0;
		ArrayList<Node> adj = new ArrayList<>();
		public Node(int i){
			id = i;
		}
	}
	public static long[][] dp;
	public static ArrayList<Node> visited = new ArrayList<>();
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int m = s.nextInt();
		Node[] nodes = new Node[n];
		dp = new long[n][n];
		for(int i = 0; i < n; i++){
			nodes[i] = new Node(i);
		}
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				dp[i][j] = -1;
			}
		}
		for(int i = 0; i < m; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			nodes[x].adj.add(nodes[y]);
			nodes[x].outdegree++;
			nodes[y].indegree++;
		}
		long sum = 0;
		for(int i = 0; i < n; i++){
			if(nodes[i].indegree == 0){
				sum += dfs(nodes[i], n) % 1000000007;
			}
			sum %= 1000000007;
		}
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(sum);
	}
	public static long dfs(Node n, int c){
		visited.add(n);
		if(n.outdegree == 0){
			dp[n.id][n.id] = 0;
			return 0;
		}
		long sum = 0;
		for(Node x : n.adj){
			dp[n.id][x.id] = 0;
			for(int i = 0; i < c; i++){
				if(dp[x.id][i] != -1){
					System.out.println(n.id + " " + i + " ");
					sum+=dp[x.id][i]+1;
					dp[n.id][x.id] += dp[x.id][i]+1;
				}
			}
			if(!visited.contains(x)){
				long z = dfs(x, c);
				sum+=z+1;
				dp[n.id][x.id] += z+1;
			}
		}
		return sum;
	}
}