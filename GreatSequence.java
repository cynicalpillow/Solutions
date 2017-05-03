import java.util.*;
import java.math.*;
import java.io.*;
public class GreatSequence {
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
		int k = s.nextInt();
		int q = s.nextInt();
		int[] a = new int[n];
		ArrayList<Integer> z = new ArrayList<>();
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		int[] pre = new int[n];
		for(int i = 0; i < n; i++){
			a[i] = s.nextInt();
			if(!map.containsKey(a[i])){
				map.put(a[i], new ArrayList<>());
				map.get(a[i]).add(i);
			} else map.get(a[i]).add(i);
			z.add(a[i]);
			if(i == 0) pre[i] = a[i];
			else pre[i] = pre[i-1]+a[i];
		}
		for(int i = 0; i < q; i++){
			int x = s.nextInt();
			int y = s.nextInt();
			int c = s.nextInt()-1;
			int b = s.nextInt()-1;
			int sum = 0;
			if(c == 0){
				sum = pre[b];
			} else {
				sum = pre[b] - pre[c-1];
			}
			if(sum > k){
				boolean checkx = false;
				boolean checky = false;
				if(map.containsKey(x)){
					if(binarySearch(map.get(x), c, b))checkx = true;
				}
				if(checkx){
					if(map.containsKey(y)){
						if(binarySearch(map.get(y), c, b))checky = true;
					}
				}
				if(checkx && checky)System.out.println("Yes");
				else System.out.println("No");
			} else {
				System.out.println("No");
			}
		}
	}
	public static boolean binarySearch(ArrayList<Integer> a, int l, int u){
		int low = 0;
	    int high = a.size()-1;
	    while (low <= high) {
	        int mid = (low + high) / 2;
	        if(a.get(mid) > u) {
	            high = mid - 1;
	        } else if(a.get(mid) < l) {
	            low = mid + 1;
	        } else {
	            return true;
	        }
	    }
	    return false;
	}
}