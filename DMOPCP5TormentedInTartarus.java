import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCP5TormentedInTartarus {
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
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		StringTokenizer x = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(x.nextToken());
		int m = Integer.parseInt(x.nextToken());
		int k = Integer.parseInt(x.nextToken());
		BitSet[] a = new BitSet[m];
		PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
		for(int i = 0; i < m; i++){
			BitSet op;
			if(i == 0)op = new BitSet(n);
			else op = (BitSet)a[i-1].clone();
			x = new StringTokenizer(s.readLine());
			String y = x.nextToken();
			int z = i;
			if(!y.equals("X")){
				z = Integer.parseInt(x.nextToken());
			}
			if(y.equals("F")){
				op.set(z);
			} else if (y.equals("D")){
				op.clear(z);
			} else if (y.equals("R")){
				if(z == 0)op = new BitSet(n);
				else op = (BitSet)a[z-1].clone();
			} else {
				op.flip(0, n);
			}
			int sets = op.cardinality();
			q.add(sets);
			if(i >=  k){
				int ss = Integer.valueOf(a[i-k].cardinality());
				//System.out.println(ss);
				if(q.contains(ss))q.remove(Integer.valueOf(ss));
				//System.out.println(q.peek());
			}
			System.out.println(sets + " " + q.peek());
			a[i] = op;
		}
	}
}
