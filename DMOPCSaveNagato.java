import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCSaveNagato {
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
		int max = 0;
		ArrayList<Node> adj = new ArrayList<>();
		public Node(int id){
			this.id = id;
		}
	}
	static int maxDepth = 0;
	static Node first = new Node(0);
	static Node second = new Node(0);
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		Node[] nodes = new Node[n];
		for(int i = 0; i < n; i++){
			nodes[i] =new Node(i);
		}
		for(int i = 0; i < n-1; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			nodes[x].adj.add(nodes[y]);
			nodes[y].adj.add(nodes[x]);
		}
		dfs(nodes[0], 1, new boolean[n]);
		maxDepth = 0;
		dfsSecond(first, 1, new boolean[n]);
		first.max = second.max = maxDepth;
		dfsThird(second, 1, new boolean[n]);
		for(int i = 0; i < n; i++){
			System.out.println(nodes[i].max);
		}
	}
	public static void dfsThird(Node x, int depth, boolean[] visited){
		visited[x.id] = true;
		x.max = Math.max(x.max, depth);
		for(Node y : x.adj){
			if(!visited[y.id]){
				dfsThird(y, depth+1, visited);
			}
		}
	}
	public static void dfsSecond(Node x, int depth, boolean[] visited){
		visited[x.id] = true;
		x.max = Math.max(x.max, depth);
		for(Node y : x.adj){
			if(!visited[y.id]){
				dfsSecond(y, depth+1, visited);
			}
		}
		if(depth > maxDepth){
			maxDepth = depth;
			second = x;
		}
	}
	public static void dfs(Node x, int depth, boolean[] visited){
		visited[x.id] = true;
		x.max = Math.max(x.max, depth);
		for(Node y : x.adj){
			if(!visited[y.id]){
				dfs(y, depth+1, visited);
			}
		}
		if(depth > maxDepth){
			maxDepth = depth;
			first = x;
		}
	}
}