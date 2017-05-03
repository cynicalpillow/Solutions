import java.util.*;
import java.math.*;
import java.io.*;
public class PetersEscape {
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
	public static class Position {
		boolean rock = false;
		boolean exit = false;
		int x;
		int y;
		int id;
		ArrayList<Position> adj = new ArrayList<>();
		public Position(int xx, int yy, int id){
			x =xx;
			y = yy;
			this.id = id;
		}
		public Position(int xx, int yy, int i, int id){
			x =xx;
			y = yy;
			if(i == 1)rock = true;
			else exit = true;
			this.id = id;
		}
	}
	public static Position start;
	public static int lowest = Integer.MAX_VALUE;
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int m = s.nextInt();
		int idx = 0;
		start = new Position(s.nextInt(), s.nextInt(), idx);
		ArrayList<Position> positions = new ArrayList<>();
		positions.add(start);
		int o = s.nextInt();
		idx++;
		for(int i = 0; i < o; i++){
			positions.add(new Position(s.nextInt(), s.nextInt(), 1, idx++));
		}
		int e = s.nextInt();
		for(int i = 0; i < e; i++){
			positions.add(new Position(s.nextInt(), s.nextInt(), 0, idx++));
		}
		Position[] xSort = new Position[positions.size()];
		Position[] ySort = new Position[positions.size()];
		for(int z = 0; z < positions.size(); z++)xSort[z] = ySort[z] = positions.get(z);
		Arrays.sort(xSort, new Comparator<Position>(){
			@Override
			public int compare(Position arg0, Position arg1) {
				if(arg0.x < arg1.x)return -1;
				else if(arg0.x > arg1.x)return 1;
				else {
					if(arg0.y < arg1.y)return -1;
					else if(arg0.y > arg1.y)return 1;
					else return 0;
				}
			}
		});
		Arrays.sort(ySort, new Comparator<Position>(){
			@Override
			public int compare(Position arg0, Position arg1) {
				if(arg0.y < arg1.y)return -1;
				else if(arg0.y > arg1.y)return 1;
				else {
					if(arg0.x < arg1.x)return -1;
					else if(arg0.x > arg1.x)return 1;
					else return 0;
				}
			}
		});
		boolean exitAdded = false;
		for(int i = 0; i < xSort.length; i++){
			if(i-1 >= 0){
				if(xSort[i-1].x == xSort[i].x){
					xSort[i].adj.add(xSort[i-1]);
					xSort[i-1].adj.add(xSort[i]);
					if(xSort[i-1].exit || xSort[i].exit)exitAdded = true;
				}
			}
			if(i+1 < xSort.length){
				if(xSort[i+1].x == xSort[i].x){
					xSort[i].adj.add(xSort[i+1]);
					xSort[i+1].adj.add(xSort[i]);
					if(xSort[i+1].exit || xSort[i].exit)exitAdded = true;
				}
			}
		}
		for(int i = 0; i < ySort.length; i++){
			if(i-1 >= 0){
				if(ySort[i-1].y == ySort[i].y){
					ySort[i].adj.add(ySort[i-1]);
					ySort[i-1].adj.add(ySort[i]);
					if(ySort[i-1].exit || ySort[i].exit)exitAdded = true;
				}
			}
			if(i+1 < ySort.length){
				if(ySort[i+1].x == ySort[i].x){
					ySort[i].adj.add(ySort[i+1]);
					ySort[i+1].adj.add(ySort[i]);
					if(ySort[i+1].exit || ySort[i].exit)exitAdded = true;
				}
			}
		}
		if(exitAdded)bfs(start, ySort.length);
		if(lowest == Integer.MAX_VALUE)System.out.println(-1);
		else System.out.println(lowest);
	}
	public static void bfs(Position x, int n){
		ArrayDeque<Position> q = new ArrayDeque<>();
		ArrayDeque<Integer> rock = new ArrayDeque<>();
		boolean[] visited = new boolean[n];
		q.add(x);
		visited[x.id] = true;
		rock.add(0);
		while(!q.isEmpty()){
			Position z = q.poll();
			int rocks = rock.poll();
			if(z.exit){
				lowest = rocks;
				break;
			}
			for(Position p : z.adj){
				if(!visited[p.id]){
					visited[p.id] = true;
					q.add(p);
					if(p.rock)rock.add(rocks+1);
					else rock.add(rocks);
				}
			}
		}
	}
}
