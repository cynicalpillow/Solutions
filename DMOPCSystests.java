import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCSystests {
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
	public static class Batch{
		int start;
		int end;
		int points;
		public Batch(int s, int e, int p){
			start = s;
			end = e;
			points = p;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int b = s.nextInt();
		ArrayList<Batch> batches = new ArrayList<>();
		for(int i = 0; i < b; i++){
			batches.add(new Batch(s.nextInt(), s.nextInt(), s.nextInt()));
		}
		int n = s.nextInt();
		Integer[] vals = new Integer[n];
		for(int i = 0; i < n; i++){
			int x = s.nextInt();
			vals[i] = x;
		}
		Arrays.sort(vals);
		int t = 0;
		for(int i = 0; i < batches.size(); i++){
			Batch z = batches.get(i);
			boolean check = binarySearch(vals, z.start, z.end);
			if(!check)t+=z.points;
		}
		System.out.println(t);
	}
	public static boolean binarySearch(Integer[] a, int l, int u){
		int low = 0;
	    int high = a.length-1;
	    while (low <= high) {
	        int mid = (low + high) / 2;
	        if(a[mid] > u) {
	            high = mid - 1;
	        } else if(a[mid] < l) {
	            low = mid + 1;
	        } else {
	            return true;
	        }
	    }
	    return false;
	}
}
