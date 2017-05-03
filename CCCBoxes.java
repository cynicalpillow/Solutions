import java.util.*;
import java.math.*;
import java.io.*;
public class CCCBoxes {
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
	private static class Dimension implements Comparable<Dimension>{
		int[] di = new int[3];
		int volume = 0;
		public Dimension(int x, int y, int z){
			di[0] =x;
			di[1] = y;
			di[2] = z;
			volume = x * y * z;
			Arrays.sort(di);
		}
		@Override
		public int compareTo(Dimension arg0) {
			if(this.volume < arg0.volume)return -1;
			else if (this.volume > arg0.volume)return 1;
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
		Dimension[] boxes = new Dimension[n];
		for(int i = 0; i < n; i++){
			int x = s.nextInt();
			int y = s.nextInt();
			int z = s.nextInt();
			boxes[i] = new Dimension(x, y, z);
		}
		Arrays.sort(boxes);
		int m = s.nextInt();
		for(int i = 0; i < m; i++){
			int x = s.nextInt();
			int y = s.nextInt();
			int z = s.nextInt();
			Dimension item = new Dimension(x, y, z);
			boolean fit = false;
			int vol = 0;
			for(int j = 0; j < n; j++){
				if(item.di[0] <= boxes[j].di[0] && item.di[1] <= boxes[j].di[1] &&item.di[2] <= boxes[j].di[2]){
					fit = true;
					vol = boxes[j].volume;
					break;
				}
			}
			if(fit)System.out.println(vol);
			else System.out.println("Item does not fit.");
		}
	}
}
