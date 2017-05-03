import java.util.*;
import java.math.*;
import java.io.*;
public class CCCKnightHop {
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
		int x = s.nextInt()-1;
		int y = s.nextInt()-1;
		int endx = s.nextInt()-1;
		int endy = s.nextInt()-1;
		bfs(x, y, endx, endy);
	}
	public static void bfs(int x, int y, int endx, int endy){
		ArrayDeque<Integer> xs = new ArrayDeque<>();
		ArrayDeque<Integer> ys = new ArrayDeque<>();
		ArrayDeque<Integer> moves = new ArrayDeque<>();
		xs.add(x);
		ys.add(y);
		moves.add(0);
		boolean[][] visited = new boolean[8][8];
		visited[x][y] = true;
		while(!xs.isEmpty() && !ys.isEmpty()){
			int cx = xs.pop();
			int cy = ys.pop();
			int move = moves.pop();
			if(cx == endx && cy == endy){
				System.out.println(move);
				return;
			}
			if(cx + 1 < 8 && cy+2 < 8 && !visited[cx+1][cy+2]){
				xs.add(cx+1);
				ys.add(cy+2);
				visited[cx+1][cy+2] = true;
				moves.add(move+1);
			}
			if(cx + 1 < 8 && cy-2 >= 0 && !visited[cx+1][cy-2]){
				xs.add(cx+1);
				ys.add(cy-2);
				visited[cx+1][cy-2] = true;
				moves.add(move+1);
			}
			if(cx - 1 >= 0 && cy-2 >= 0 && !visited[cx-1][cy-2]){
				xs.add(cx-1);
				ys.add(cy-2);
				visited[cx-1][cy-2] = true;
				moves.add(move+1);
			}
			if(cx - 1 >= 0 && cy+2 < 8 && !visited[cx-1][cy+2]){
				xs.add(cx-1);
				ys.add(cy+2);
				visited[cx-1][cy+2] = true;
				moves.add(move+1);
			}
			if(cx + 2 < 8 && cy +1 < 8 && !visited[cx+2][cy+1]){
				xs.add(cx+2);
				ys.add(cy+1);
				visited[cx+2][cy+1] = true;
				moves.add(move+1);
			}
			if(cx + 2 < 8 && cy-1 >= 0 && !visited[cx+2][cy-1]){
				xs.add(cx+2);
				ys.add(cy-1);
				visited[cx+2][cy-1] = true;
				moves.add(move+1);
			}
			if(cx - 2 >= 0 && cy-1 >= 0 && !visited[cx-2][cy-1]){
				xs.add(cx-2);
				ys.add(cy-1);
				visited[cx-2][cy-1] = true;
				moves.add(move+1);
			}
			if(cx - 2 >= 0 && cy+1 < 8 && !visited[cx-2][cy+1]){
				xs.add(cx-2);
				ys.add(cy+1);
				visited[cx-2][cy+1] = true;
				moves.add(move+1);
			}
		}
	}
}
