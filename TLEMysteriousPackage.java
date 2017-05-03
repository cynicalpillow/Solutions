import java.util.*;
import java.math.*;
import java.io.*;
public class TLEMysteriousPackage {
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
	private static class Classroom implements Comparable<Classroom>{
		ArrayList<Integer> students = new ArrayList<>();
		ArrayList<Classroom> adj = new ArrayList<>();
		int period;
		int dist = 1;
		int id;
		public Classroom(int i, int x){
			period = i;
			id = x;
		}
		@Override
		public int compareTo(Classroom x) {
			if(this.period < x.period)return -1;
			else if(this.period > x.period)return 1;
			return 0;
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int idx = s.nextInt();
		int idy = s.nextInt();
		Classroom[] classes = new Classroom[n];
		for(int i = 0; i < n; i++){
			int p = s.nextInt();
			int si = s.nextInt();
			classes[i] = new Classroom(p, i);
			for(int j = 0; j < si; j++){
				classes[i].students.add(s.nextInt());
			}
		}
		Arrays.sort(classes);
		for(Classroom c : classes){
			HashSet<Integer> a = new HashSet<>(c.students);
			for(Classroom cx : classes){
				if(cx.period > c.period){
					boolean check = false;
					for(int j : cx.students){
						if(a.contains(j)){
							check = true;
							break;
						}
					}
					if(check){
						c.adj.add(cx);
					}
				}
			}
		}
		bfs(idx, idy, classes, n);
	}
	public static void bfs(int idx, int idy, Classroom[] classes, int n){
		ArrayDeque<Classroom> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n];
		for(Classroom c : classes){
			if(c.students.contains(idx)){
				visited[c.id] = true;
				q.add(c);
			}
		}
		int min = 1;
		Classroom period = new Classroom(-1, -1);
		while(!q.isEmpty()){
			Classroom c = q.poll();
			if(c.students.contains(idy)){
				min = c.dist;
				period = c;
				break;
			}
			for(Classroom cx : c.adj){
				if(!visited[cx.id]){
					visited[cx.id] = true;
					cx.dist = c.dist+1;
					q.add(cx);
				}
			}
		}
		if(period.period == -1)System.out.println("delivery failure");
		else {
			System.out.println(min);
			System.out.println(period.period);
		}
	}
}
