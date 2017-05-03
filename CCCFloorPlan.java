import java.util.*;
import java.math.*;
import java.io.*;
public class CCCFloorPlan {
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
	private static ArrayList<Integer> rooms = new ArrayList<>();
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(s.readLine());
		int r = Integer.parseInt(s.readLine());
		int c = Integer.parseInt(s.readLine());
		int[][] a = new int[r][c];
		for(int i = 0; i < r; i++){
			String y = s.readLine();
			for(int j = 0; j < c; j++){
				if(y.charAt(j) == 'I')a[i][j] = -1;
			}
		}
		boolean[][] visited = new boolean[r][c];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(!visited[i][j] && a[i][j] != -1){
					int size = dfs(i, j, a, visited);
					rooms.add(size);
				}
			}
		}
		Collections.sort(rooms);
		int total = 0;
		for(int i = rooms.size()-1; i >= 0; i--){
			int x = rooms.get(i);
			if(n - x >= 0){
				n-=x;
				total++;
			} else {
				break;
			}
		}
		if(total != 1)System.out.println(total + " rooms, " + n + " square metre(s) left over");
		else System.out.println(total + " room, " + n + " square metre(s) left over");
	}
	public static int dfs(int row, int col, int[][] a, boolean[][] visited){
		if(row < 0 || col < 0 || row > a.length-1 || col > a[0].length-1)return 0;
		if(visited[row][col])return 0;
		if(a[row][col] == -1)return 0;
		visited[row][col] = true;
		return dfs(row+1, col, a, visited) + dfs(row, col+1, a, visited) + dfs(row-1, col, a, visited) + dfs(row, col-1, a, visited)+1;
	}
}
