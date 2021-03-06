import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCP2EbolaOutbreak {
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
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		Reader s = new Reader();
		int n = s.nextInt();
		int m = s.nextInt();
		boolean[] infected = new boolean[n+1];
		infected[1] = true;
		int[] a = new int[n+1];
		for(int i = 1; i < n+1; i++){
			a[i] = i;
		}
		boolean[] visited = new boolean[n+1];
		for(int i = 0; i < m; i++){
			int k = s.nextInt();
			int[] z = new int[k];
			for(int j = 0; j < k; j++){
				if(j == 0)z[j] = s.nextInt();
				else{
					z[j] = s.nextInt();
					union(a, infected, visited, z[j-1], z[j]);
				}
			}
		}
		for(int j =2; j < n+1; j++){
			if(find(a, j, 1)){
				infected[j] = true;
				visited[j] = true;
			}
		}
		int count = 0;
		StringBuilder x = new StringBuilder();
		for(int i = 1; i < n+1; i++){
			if(infected[i]){
				count++;
				x.append(i + " ");
			}
		}
		System.out.println(count);
		System.out.println(x.toString());
	}
	public static void union(int[] z, boolean[] infected, boolean[] visited, int x, int y){
		int rootx = root(z, x);
		int rooty = root(z, y);
		if(rooty!=rootx){
			z[rooty] = rootx;
			if(infected[rootx] || infected[rooty]){
				infected[rootx] = true;
				infected[rooty] = true;
				visited[rootx] = true;
				visited[rooty] = true;
			}
		}
	}
	public static int root(int[] z, int x){
		while(z[x] != x){
			x = z[z[z[z[x]]]];
		}
		return x;
	}
	public static boolean find(int[] z, int x, int y){
		return root(z, x) == root(z, y);
	}
}
