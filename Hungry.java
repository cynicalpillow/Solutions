import java.util.*;
import java.math.*;
import java.io.*;
public class Hungry {
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
	private static class Food implements Comparable<Food>{
		int id;
		int tasty;
		public Food(int i){
			id = i;
		}
		@Override
		public int compareTo(Food arg0) {
			if(this.tasty < arg0.tasty)return -1;
			else if(this.tasty > arg0.tasty)return 1;
			return 0;
		}
	}
	private static class FoodFilling implements Comparable<FoodFilling>{
		int id;
		int filling;
		public FoodFilling(int i){
			id = i;
		}
		@Override
		public int compareTo(FoodFilling o) {
			if(this.filling < o.filling)return -1;
			else if(this.filling > o.filling)return 1;
			return 0;
		}
	}
	public static void main(String args[]) throws Exception {
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader s = new BufferedReader(new FileReader("*.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("*.out")));
		//StringTokenizer st = new StringTokenizer(s.readLine());
		Reader s = new Reader();
		int n = s.nextInt();
		Food[] x = new Food[n];
		FoodFilling[] y = new FoodFilling[n];
		for(int i = 0; i < n; i++)x[i] = new Food(i+1);
		for(int i = 0; i < n; i++)y[i] = new FoodFilling(i+1);
		for(int i = 0; i < n; i++){
			int taste = s.nextInt();
			int filling = s.nextInt();
			x[i].tasty = taste;
			y[i].filling = filling;
		}
		Arrays.sort(x);
		Arrays.sort(y);
		for(int i = 0; i < n; i++)System.out.print(x[i].id + " ");
		System.out.println();
		for(int i = 0; i < n; i++)System.out.print(y[i].id + " ");
	}
}
