import java.util.*;
import java.math.*;
import java.io.*;
public class DMOPCP4Contagion {
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
	private static class City implements Comparable<City>{
		int id;
		int x;
		int y;
		long dist = Long.MAX_VALUE;
		public City(int i){
			id = i;
		}
		@Override
		public int compareTo(City a) {
			if(this.dist < a.dist)return -1;
			else if(this.dist > a.dist)return 1;
			return 0;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		City[] cities = new City[n];
		for(int i = 0; i < n; i++)cities[i] = new City(i);
		for(int i= 0; i < n; i++){
			int x = s.nextInt();
			int y = s.nextInt();
			cities[i].x = x;
			cities[i].y = y;
		}
		int x = s.nextInt()-1;
		cities[x].dist = 0;
		dijkstra(cities, n, x);
		int q = s.nextInt();
		Arrays.sort(cities);
		long max = cities[n-1].dist;
		StringBuilder z = new StringBuilder();
		for(int i = 0; i < q; i++){
			int qi = s.nextInt();
			if(qi >= max){
				int count = n;
				z.append(count + "\n");
			} else {
				int count = 0;
				int j = 0;
				while(j < n && cities[j].dist <= qi){
					count++;
					j++;
				}
				z.append(count + "\n");
			}
		}
		System.out.println(z.toString());
	}
	public static void dijkstra(City[] cities, int n, int x){
		PriorityQueue<City> q = new PriorityQueue<>();
		q.add(cities[x]);
		while(!q.isEmpty()){
			City y = q.poll();
			for(City c : cities){
				int dist = ((y.x - c.x) * (y.x - c.x)) + ((y.y - c.y) * (y.y - c.y));
				if(c.dist > dist + y.dist){
					c.dist = dist + y.dist;
					if(q.contains(c))q.remove(c);
					q.add(c);
				}
			}
		}
	}
}