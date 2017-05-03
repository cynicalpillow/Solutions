import java.util.*;
import java.math.*;
import java.io.*;
public class PursuitOfKnowledge {
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
	private static class Node{
		int id;
		ArrayList<Node> adj = new ArrayList<>();
		int[] dists;
		public Node(int i){
			id = i;
		}
	}
	public static void main(String args[]) throws Exception {
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		Reader s = new Reader();
		int n = s.nextInt();
		int m = s.nextInt();
		int t = s.nextInt();
		Node[] nodes = new Node[n];
		for(int i = 0; i < n; i++){
			nodes[i] = new Node(i);
		}
		for(int i= 0; i < m; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			nodes[x].adj.add(nodes[y]);
		}
		for(int i = 0; i < n; i++){
			bfs(nodes[i], nodes, n, t);
		}
		int q = s.nextInt();
		for(int i = 0; i < q; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			if(nodes[x].dists[y] == Integer.MAX_VALUE)System.out.println("Not enough hallways!");
			else System.out.println(nodes[x].dists[y]);
		}
	}
	public static void bfs(Node x, Node[] nodes, int n, int t){
		ArrayDeque<Node> q = new ArrayDeque<>();
		int[] dists = new int[n];
		Arrays.fill(dists, Integer.MAX_VALUE);
		dists[x.id]=0;
		q.add(x);
		boolean[] visited = new boolean[n];
		visited[x.id]=true;
		while(!q.isEmpty()){
			Node no = q.poll();
			for(Node z : no.adj){
				if(dists[z.id] > dists[no.id]+t){
					dists[z.id]=dists[no.id]+t;
					if(!visited[z.id]){
						visited[z.id]=true;
						q.add(z);
					}
				}
			}
		}
		x.dists = dists;
	}
}
