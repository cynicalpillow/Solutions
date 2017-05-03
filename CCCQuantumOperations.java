import java.util.*;
import java.math.*;
import java.io.*;
public class CCCQuantumOperations {
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
		Reader s = new Reader();
		int n = s.nextInt();
		ArrayList<long[][]> matrixes = new ArrayList<>();
		for(int i = 0; i < n; i++){
			int r = s.nextInt();
			int c = s.nextInt();
			long[][] curr = new long[r][c];
			for(int j = 0; j < r; j++){
				for(int z = 0; z < c; z++){
					curr[j][z] = s.nextLong();
				}
			}
			matrixes.add(curr);
		}
		long[][] a = matrixes.get(0);
		for(int i = 1; i < matrixes.size(); i++){
			long[][] b = matrixes.get(i);
			long[][] results = new long[a.length * b.length][b[0].length * a[0].length];
			int currr = 0;
			int currc = 0;
			int lowerR = 0;
			int lowerC = 0;
			for(int j = 0; j < a.length; j++){
				for(int z = 0; z < a[j].length; z++){
					long temp = a[j][z];
					for(int x = 0; x < b.length; x++){
						for(int y = 0; y < b[x].length; y++){
							results[currr][currc] = temp * b[x][y];
							currc++;
						}
						currr++;
						currc = lowerC;
					}
					currc += b[0].length;
					lowerC = currc;
					currr = lowerR;
					if(currc >= results[0].length-1){
						currc = 0;
						lowerC = 0;
						currr = currr + b.length;
						lowerR = currr;
					}
				}
			}
			a = results;
		}
		long maxrowSum = Long.MIN_VALUE;
		long maxcolSum = Long.MIN_VALUE;
		long minrowSum = Long.MAX_VALUE;
		long mincolSum = Long.MAX_VALUE;
		long maxItem = Long.MIN_VALUE;
		long minItem = Long.MAX_VALUE;
		long currRow = 0;
		long currCol = 0;
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a[i].length; j++){
				currRow += a[i][j];
				maxItem = Math.max(a[i][j], maxItem);
				minItem = Math.min(a[i][j], minItem);
			}
			maxrowSum = Math.max(currRow, maxrowSum);
			minrowSum = Math.min(currRow, minrowSum);
			currRow = 0;
		}
		for(int i = 0; i < a[0].length; i++){
			for(int j = 0; j < a.length; j++){
				currCol += a[j][i];
			}
			maxcolSum = Math.max(currCol, maxcolSum);
			mincolSum = Math.min(currCol, mincolSum);
			currCol = 0;
		}
		System.out.println(maxItem);
		System.out.println(minItem);
		System.out.println(maxrowSum);
		System.out.println(minrowSum);
		System.out.println(maxcolSum);
		System.out.println(mincolSum);
	}
}
