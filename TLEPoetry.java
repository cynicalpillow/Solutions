import java.util.*;
import java.math.*;
import java.io.*;
public class TLEPoetry {
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
		
		int n = Integer.parseInt(s.readLine());
		int[] sizes = new int[n];
		for(int i = 0; i < n; i++){
			sizes[i] = Integer.parseInt(s.readLine());
		}
		StringTokenizer st = new StringTokenizer(s.readLine());
		int size = 0;
		String x = "";
		StringBuilder z = new StringBuilder();
		int currCycle = 0;
		boolean first = true;
		while(st.hasMoreTokens()){
			x = st.nextToken();
			if((size + x.length()) > sizes[currCycle] && !first){
				System.out.println(z.toString());
				size = x.length() + 1;
				currCycle = (currCycle + 1) % n;
				z.delete(0, z.length());
				z.append(x);
				if(x.length() - 1> sizes[currCycle]){
					while(z.length() > sizes[currCycle]){
						System.out.println(z.substring(0, sizes[currCycle]));
						z.delete(0, sizes[currCycle]);
						currCycle = (currCycle + 1) % n;
					}
					size = z.length()+1;
				}
				z.append(" ");
			} else if ((size + x.length()) > sizes[currCycle] && first){
				z.append(x);
				while(z.length() > sizes[currCycle]){
					System.out.println(z.substring(0, sizes[currCycle]));
					z.delete(0, sizes[currCycle]);
					currCycle = (currCycle + 1) % n;
				}
				size = z.length()+1;
				z.append(" ");
				first = false;
			} else {
				z.append(x + " ");
				size += x.length() + 1;
				first = false;
			}
		}
		if(z.length() > 0){
			while(z.length() > sizes[currCycle]){
				System.out.println(z.substring(0, sizes[currCycle]));
				z.delete(0, sizes[currCycle]);
				currCycle = (currCycle + 1) % n;
			}
			if(z.length() > 0){
				System.out.println(z.toString());
			}
		}
	}
}
