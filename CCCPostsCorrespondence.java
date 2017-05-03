import java.util.*;
import java.math.*;
import java.io.*;
public class CCCPostsCorrespondence {
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
		//StringTokenizer st = new StringTokenizer(s.readLine());
		int m = Integer.parseInt(s.readLine());
		int n = Integer.parseInt(s.readLine());
		String[] x = new String[n];
		for(int i = 0; i < n; i++){
			x[i] = s.readLine();
		}
		String[] y = new String[n];
		for(int i = 0; i < n; i++){
			y[i] = s.readLine();
		}
		boolean found = false;
		for(int i = 0; i < n; i++){
			if(equal(x[i], y[i])){
				String x1 = x[i];
				String y1 = y[i];
				ArrayList<Integer> a = new ArrayList<>();
				a.add(i+1);
				if(search(i, a, x, y, x1, y1, m, 1)){
					found = true;
					break;
				}
			}
		}
		if(!found)System.out.println("No solution.");
	}
	public static boolean search(int idx, ArrayList<Integer> a, String[] x, String[] y, String x1, String x2, int max, int c){
		if(c > max){
			return false;
		}
		if(x1.equals(x2)){
			System.out.println(c);
			for(int i : a){
				System.out.println(i);
			}
			return true;
		}
		if(!equal(x1, x2)){
			return false;
		}
		for(int i = 0; i < x.length; i++){
			String y1 = x1+x[i];
			String y2 = x2+y[i];
			ArrayList<Integer> ax = new ArrayList<>(a);
			ax.add(i+1);
			if(search(i, ax, x, y, y1, y2, max, c+1))return true;
		}
		return false;
	}
	public static boolean equal(String y, String x){
		//System.out.println(y + " " + x + " " +  y.startsWith(x) + " " + x.startsWith(y));
		if(y.equals(x))return true;
		else if(y.length() > x.length())return y.startsWith(x);
		else if(x.length() > y.length())return x.startsWith(y);
		else return false;
	}
}
