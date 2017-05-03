import java.util.*;
import java.math.*;
import java.io.*;
public class CCCFriends {
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
	private static class Friend{
		int id;
		ArrayList<Friend> adj = new ArrayList<>();
		public Friend(int i){
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
		Friend[] a = new Friend[10001];
		for(int i = 0; i <= 10000; i++){
			a[i] = new Friend(i);
		}
		for(int i = 0; i < n; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			a[x].adj.add(a[y]);
		}
		int x = s.nextInt();
		int y = s.nextInt();
		while(!(x== 0 && y == 0)){
			x--;
			y--;
			boolean[] visited = new boolean[10001];
			if(dfs(x, y, a, visited)){
				visited = new boolean[10001];
				System.out.println("Yes " + dfsSeperation(x, y, a, visited, 0));
			}
			else System.out.println("No");
			x = s.nextInt();
			y = s.nextInt();
		}
	}
	public static boolean dfs(int x, int y, Friend[] a, boolean[] visited){
		visited[x] = true;
		for(Friend f : a[x].adj){
			if(visited[f.id] && visited[y])return true;
			if(!visited[f.id])if(dfs(f.id, y, a, visited))return true;
		}
		return false;
	}
	public static int dfsSeperation(int x, int y, Friend[] a, boolean[] visited, int c){
		visited[x] = true;
		int total = 0;
		for(Friend f : a[x].adj){
			if(f.id == y)return c;
			if(!visited[f.id])total = dfsSeperation(f.id, y, a, visited, c+1);
		}
		return total;
	}
}
