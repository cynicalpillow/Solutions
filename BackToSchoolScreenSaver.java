import java.util.*;
import java.math.*;
import java.io.*;
public class BackToSchoolScreenSaver {
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
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		char[][] a = new char[m][n];
		int startr = 0;
		int startc = 0;
		for(int i = 0; i < m; i++){
			String y = s.readLine();
			for(int j = 0; j < y.length(); j++){
				a[i][j] = y.charAt(j);
				if(y.charAt(j) == 'O'){
					startr = i;
					startc = j;
				}
			}
		}
		int x = startc;
		int y = startr;
		int hor = 1;
		int ver = 0;
		int tx = 0;
		while(tx <= t+1){
			tx++;
			x+=hor;
			y+=ver;
			if(x < 0 || x > n-1 || y < 0 || y > m-1){
				break;
			}
			if(a[y][x] == '/' && hor > 0){
				hor = 0;
				ver = -1;
				a[y][x] = '\\';
			} else if (a[y][x] == '/' && hor < 0){
				hor = 0;
				ver = 1;
				a[y][x] = '\\';
			} else if (a[y][x] == '/' && ver < 0){
				hor = 1;
				ver = 0;
				a[y][x] = '\\';
			} else if (a[y][x] == '/' && ver > 0){
				hor = -1;
				ver = 0;
				a[y][x] = '\\';
			} else if(a[y][x] == '\\' && hor > 0){
				hor = 0;
				ver = 1;
				a[y][x] = '/';
			} else if (a[y][x] == '\\' && hor < 0){
				hor = 0;
				ver = -1;
				a[y][x] = '/';
			} else if (a[y][x] == '\\' && ver < 0){
				hor = -1;
				ver = 0;
				a[y][x] = '/';
			} else if (a[y][x] == '\\' && ver > 0){
				hor = 1;
				ver = 0;
				a[y][x] = '/';
			} else if(a[y][x] == '|' && hor > 0){
				hor = -1;
				a[y][x] = '-';
			} else if (a[y][x] == '|' && hor < 0){
				hor = 1;
				a[y][x] = '-';
			} else if (a[y][x] == '-' && ver < 0){
				ver = 1;
				a[y][x] = '|';
			} else if (a[y][x] == '-' && ver > 0){
				ver = -1;
				a[y][x] = '|';
			}
		}
		if(tx > t)System.out.println("The observer stays within the grid.");
		else System.out.println("The observer leaves the grid after " + tx + " tick(s).");
	}
}
