import java.util.*;
import java.math.*;
import java.io.*;
public class CCCACoinGame {
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
	public static class State {
		ArrayList<ArrayList<Integer>> loc = new ArrayList<>();
		int moves = 0;
		public State(ArrayList<ArrayList<Integer>> l, int m){
			loc = l;
			moves = m;
		}
	}
	static int n;
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		n = s.nextInt();
		while(n != 0){
			int[] initial = new int[n];
			for(int i = 0; i < n; i++){
				initial[i] = s.nextInt();
			}
			bfs(initial);
			n = s.nextInt();
		}
	}
	public static void bfs(int[] initial){
		ArrayDeque<State> q = new ArrayDeque<>();
		ArrayList<ArrayList<Integer>> a = new ArrayList<>();
		ArrayList<ArrayList<ArrayList<Integer>>> visited = new ArrayList<>();
		for(int i = 0; i < initial.length; i++){
			a.add(new ArrayList<>());
			a.get(i).add(initial[i]);
		}
		visited.add(a);
		State init = new State(a, 0);
		q.add(init);
		int smallest = Integer.MAX_VALUE;
		while(!q.isEmpty()){
			State x = q.poll();
			boolean check = true;
			for(int i = 0; i < n; i++){
				if(x.loc.get(i).size() == 0){
					check = false;
					break;
				}
				if(x.loc.get(i).get(0) != i+1){
					check = false;
					break;
				}
			}
			if(check){
				smallest = x.moves;
				break;
			}
			for(int i = 0; i < n; i++){
				if(x.loc.get(i).size() == 0)continue;
				if(i -1 >= 0){
					if(x.loc.get(i-1).size() == 0){
						ArrayList<ArrayList<Integer>> curr = new ArrayList<>();
						for(int j = 0; j < n; j++){
							curr.add(new ArrayList<>(x.loc.get(j)));
						}
						int t = curr.get(i).get(0);
						curr.get(i).remove(0);
						curr.get(i-1).add(0, t);
						boolean visit = false;
						for(int j = 0; j < visited.size(); j++){
							boolean checker = true;
							for(int z = 0; z < visited.get(j).size(); z++){
								if(visited.get(j).get(z).size() != curr.get(z).size()){
									checker = false;
									break;
								}
								for(int y = 0; y < visited.get(j).get(z).size(); y++){
									if(visited.get(j).get(z).get(y) != curr.get(z).get(y)){
										checker = false;
										break;
									}
								}
								if(!checker)break;
							}
							visit = checker;
							if(visit)break;
						}
						if(!visit){
							State add = new State(curr, x.moves+1);
							q.add(add);
						}
					} else if (x.loc.get(i-1).get(0) > x.loc.get(i).get(0)){
						ArrayList<ArrayList<Integer>> curr = new ArrayList<>();
						for(int j = 0; j < n; j++){
							curr.add(new ArrayList<>(x.loc.get(j)));
						}
						int t = curr.get(i).get(0);
						curr.get(i).remove(0);
						curr.get(i-1).add(0, t);
						boolean visit = false;
						for(int j = 0; j < visited.size(); j++){
							boolean checker = true;
							for(int z = 0; z < visited.get(j).size(); z++){
								if(visited.get(j).get(z).size() != curr.get(z).size()){
									checker = false;
									break;
								}
								for(int y = 0; y < visited.get(j).get(z).size(); y++){
									if(visited.get(j).get(z).get(y) != curr.get(z).get(y)){
										checker = false;
										break;
									}
								}
								if(!checker)break;
							}
							visit = checker;
							if(visit)break;
						}
						if(!visit){
							State add = new State(curr, x.moves+1);
							q.add(add);
						}
					}
				}
				if(i +1 < n){
					if(x.loc.get(i+1).size() == 0){
						ArrayList<ArrayList<Integer>> curr = new ArrayList<>();
						for(int j = 0; j < n; j++){
							curr.add(new ArrayList<>(x.loc.get(j)));
						}
						int t = curr.get(i).get(0);
						curr.get(i).remove(0);
						curr.get(i+1).add(0, t);
						boolean visit = false;
						for(int j = 0; j < visited.size(); j++){
							boolean checker = true;
							for(int z = 0; z < visited.get(j).size(); z++){
								if(visited.get(j).get(z).size() != curr.get(z).size()){
									checker = false;
									break;
								}
								for(int y = 0; y < visited.get(j).get(z).size(); y++){
									if(visited.get(j).get(z).get(y) != curr.get(z).get(y)){
										checker = false;
										break;
									}
								}
								if(!checker)break;
							}
							visit = checker;
							if(visit)break;
						}
						if(!visit){
							State add = new State(curr, x.moves+1);
							q.add(add);
						}
					} else if (x.loc.get(i+1).get(0) > x.loc.get(i).get(0)){
						ArrayList<ArrayList<Integer>> curr = new ArrayList<>();
						for(int j = 0; j < n; j++){
							curr.add(new ArrayList<>(x.loc.get(j)));
						}
						int t = curr.get(i).get(0);
						curr.get(i).remove(0);
						curr.get(i+1).add(0, t);
						boolean visit = false;
						for(int j = 0; j < visited.size(); j++){
							boolean checker = true;
							for(int z = 0; z < visited.get(j).size(); z++){
								if(visited.get(j).get(z).size() != curr.get(z).size()){
									checker = false;
									break;
								}
								for(int y = 0; y < visited.get(j).get(z).size(); y++){
									if(visited.get(j).get(z).get(y) != curr.get(z).get(y)){
										checker = false;
										break;
									}
								}
								if(!checker)break;
							}
							visit = checker;
							if(visit)break;
						}
						if(!visit){
							State add = new State(curr, x.moves+1);
							q.add(add);
						}
					}
				}
			}
		}
		if(smallest == Integer.MAX_VALUE)System.out.println("IMPOSSIBLE");
		else System.out.println(smallest);
	}
}
