import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCP3HarbourMaster {
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
	public static class Crew {
		double c;
		double a;
		double p;
		public Crew(double cc, double aa, double pp){
			c = cc;
			a = aa;
			p = pp;
		}
	}
	static double c;
	static double a;
	static double p;
	static int n;
	static Crew[] crew;
	static double maxPercentage = 0.0;
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		c = s.nextDouble();
		a = s.nextDouble();
		p = s.nextDouble();
		n = s.nextInt();
		crew = new Crew[n];
		for(int i = 0; i < n; i++){
			crew[i] = new Crew(s.nextDouble(), s.nextDouble(), s.nextDouble());
		}
		for(int i = 0; i < n; i++){
			ArrayList<Crew> visited = new ArrayList<>();
			visited.add(crew[i]);
			recurse(1, crew[i].c, crew[i].a, crew[i].p, visited);
		}
		System.out.printf("%.1f", maxPercentage);
	}
	public static void recurse(int count, double currc, double curra, double currp, ArrayList<Crew> visited){
		double cMax = Math.min(currc/c*100.0, 100.0);
		double aMax = Math.min(curra/a*100.0, 100.0);
		double pMax = Math.min(currp/p*100.0, 100.0);
		double min = Math.min(cMax, Math.min(aMax, pMax));
		maxPercentage = Math.max(maxPercentage, min);
		if(count == 5 || count == n){
			return;
		}
		for(int i = 0; i < n; i++){
			if(!visited.contains(crew[i])){
				ArrayList<Crew> vv = new ArrayList<>(visited);
				vv.add(crew[i]);
				recurse(count+1, currc+crew[i].c, curra+crew[i].a, currp+crew[i].p, vv);
			}
		}
	}
}
