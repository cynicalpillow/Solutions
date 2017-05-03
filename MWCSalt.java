import java.util.*;
import java.math.*;
import java.io.*;
public class MWCSalt {
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
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		HashMap<Integer, ArrayList<Integer>> xs = new HashMap<>();
		HashMap<Integer, ArrayList<Integer>> ys = new HashMap<>();
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(s.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(xs.containsKey(x)) xs.get(x).add(y);
			else {
				xs.put(x, new ArrayList<>());
				xs.get(x).add(y);
			}
			if(ys.containsKey(y)) ys.get(y).add(x);
			else {
				ys.put(y, new ArrayList<>());
				ys.get(y).add(x);
			}
		}
		for(int i = 0; i < q; i++){
			st = new StringTokenizer(s.readLine());
			int z = Integer.parseInt(st.nextToken());
			if(z == 1){
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if(xs.containsKey(x) && ys.containsKey(y)){
					if(xs.get(x).contains(y) && ys.get(y).contains(x))System.out.println("salty");
					else System.out.println("bland");
				} else System.out.println("bland");
			} else {
				if(st.nextToken().equals("X")){
					int x = Integer.parseInt(st.nextToken());
					if(xs.containsKey(x)) System.out.println(xs.get(x).size());
					else System.out.println(0);
				} else {
					int y = Integer.parseInt(st.nextToken());
					if(ys.containsKey(y)) System.out.println(ys.get(y).size());
					else System.out.println(0);
				}
			}
		}
	}
}
