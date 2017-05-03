import java.util.*;
import java.math.*;
import java.io.*;
public class TSOCBebiliths {
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
	public static class Bebilith implements Comparable<Bebilith>{
		int id;
		int speed;
		int dist;
		int sharp;
		public Bebilith(int i, int s, int d, int sh){
			id = i;
			speed = s;
			dist = d;
			sharp = sh;
		}
		@Override
		public int compareTo(Bebilith a) {
			if(this.speed > a.speed)return -1;
			else if (this.speed < a.speed)return 1;
			else {
				if(this.speed >= convictspeed){
					if(this.sharp > a.sharp)return -1;
					else if(this.sharp < a.sharp)return 1;
					else {
						if(this.id < a.id)return -1;
						else return 1;
					}
				} else {
					if(this.dist > a.dist)return -1;
					else if(this.dist < a.dist)return 1;
					else {
						if(this.id < a.id)return -1;
						else return 1;
					}
				}
			}
		}
	}
	public static int convictspeed;
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		convictspeed = s.nextInt();
		int n = s.nextInt();
		Bebilith[] bs = new Bebilith[n];
		for(int i = 0; i < n; i++){
			int speed = s.nextInt();
			int dist = s.nextInt();
			int sharp = s.nextInt();
			bs[i] = new Bebilith(i+1, speed, dist, sharp);
		}
		Arrays.sort(bs);
		int q = s.nextInt();
		for(int i = 0; i < q; i++){
			System.out.println(bs[s.nextInt()-1].id);
		}
	}
}
