import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCP6ZeppelinGraf {
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
		ArrayList<Node> adj = new ArrayList<>();
		int dist = 0;
		int count = 0;
		public Node(int i){
			id = i;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int m = s.nextInt();
		int k = s.nextInt();
		Node[] nodes = new Node[n];
		for(int i= 0 ;i < n; i++)nodes[i] = new Node(i);
		for(int i= 0; i < m; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			nodes[x].adj.add(nodes[y]);
			nodes[y].adj.add(nodes[x]);
		}
		for(int i = 0; i < n; i++){
			nodes[i].dist = 0;
			bfs(nodes[i], k, n);
			System.out.println(nodes[i].count);
		}
	}
	public static void bfs(Node x, int k, int b){
		ArrayDeque<Node> q = new ArrayDeque<>();
		boolean[] visited = new boolean[b];
		visited[x.id] = true;
		q.add(x);
		int count = 0;
		while(!q.isEmpty()){
			Node z = q.poll();
			count++;
			int currDist = z.dist;
			for(Node n : z.adj){
				if(!visited[n.id] && currDist + 1 <= k){
					visited[n.id] = true;
					n.dist = currDist + 1;
					if(!q.contains(n))q.add(n);
				}
			}
		}
		x.count = count;
	}
}
