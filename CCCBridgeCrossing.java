import java.util.*;
import java.math.*;
import java.io.*;
public class CCCBridgeCrossing {
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
	
	//Test
	/*	2
		5
		alice
		1
		bob
		3
		charlie
		5
		dobson
		3
		eric
		3
		*/
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		int m = Integer.parseInt(s.readLine());
		int n = Integer.parseInt(s.readLine());
		int[] time = new int[n];
		String[] names = new String[n];
		for(int i = 0; i < n; i++){
			names[i] = s.readLine();
			time[i] = Integer.parseInt(s.readLine());
		}
		int[] best = new int[n+1];
		Arrays.fill(best, Integer.MAX_VALUE);
		best[0] = 0;
		int[] groups = new int[n+1];
		Arrays.fill(groups, -1);
		groups[0] = 0;
		for(int i = 0; i < n+1; i++){
			int thiscurrTime = 0;
			for(int j = 1; j + i -1< n && j <= m; j++){
				thiscurrTime = Math.max(thiscurrTime, time[i+j-1]);
				if(best[i] + thiscurrTime < best[i+j]){
					best[i+j] = best[i] + thiscurrTime;
					groups[i + j] = j;
				}
			}
		}
		System.out.println("Total Time: " + best[n]);
		int[] printGroups = new int[n];
		int y = 0;
		for(int i = n; i >= 0 && y < n-1; i--){
			printGroups[y++]=groups[i];
			i-=groups[i]-1;
		}
		int curr = 0;
		for(int i = n-1; i >= 0; i--){
			int z = printGroups[i];
			while(z > 0 && curr < n){
				System.out.print(names[curr] + " ");
				curr++;
				z--;
			}
			if(printGroups[i] > 0)System.out.println();
			i -= z;
		}
	}
}
