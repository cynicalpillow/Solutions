import java.util.*;
import java.math.*;
import java.io.*;
public class CCCChancesOfWinning {
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
	private static class Game {
		int team1;
		int team2;
		public Game(int t1, int t2){
			team1 = t1;
			team2 = t2;
		}
	}
	private static class State {
		Game g;
		int[] scores = new int[4];
		public State(Game g, int w, int[] scores){
			this.g=g;
			for(int i = 0; i < scores.length; i++){
				this.scores[i] = scores[i];
			}
			if(w == 0){
				this.scores[g.team1]+=3;
			} else if(w == 1){
				this.scores[g.team2]+=3;
			} else {
				this.scores[g.team1]+=1;
				this.scores[g.team2]+=1;
			}
		}
	}
	static int count = 0;
	static int t;
	static int n;
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		t = s.nextInt()-1;
		n = s.nextInt();
		int[] teamScores = new int[4];
		boolean[][] visited = new boolean[4][4];
		for(int i = 0; i < n; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			visited[x][y] = visited[y][x] = true;
			int scorex = s.nextInt();
			int scorey = s.nextInt();
			if(scorex > scorey)teamScores[x]+=3;
			else if(scorey > scorex)teamScores[y]+=3;
			else {
				teamScores[y]+=1;
				teamScores[x]+=1;
			}
		}
		Game[] games = new Game[6-n];
		int idx = 0;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(i != j && !visited[i][j]){
					games[idx] = new Game(i, j);
					visited[i][j] = true;
					visited[j][i] = true;
					idx++;
				}
			}
		}
		recurse(games, new State(games[0], 0, teamScores), 1);
		recurse(games, new State(games[0], 1, teamScores), 1);
		recurse(games, new State(games[0], 2, teamScores), 1);
		System.out.println(count);
	}
	public static void recurse(Game[] games, State s, int curr){
		if(curr == 6-n){
			int add = 1;
			for(int i = 0; i < s.scores.length; i++){
				if(t == i)continue;
				if(s.scores[t] <= s.scores[i]){
					add = 0;
					break;
				}
			}
			count+=add;
			return;
		}
		State win = new State(games[curr], 0, s.scores);
		State lose = new State(games[curr], 1, s.scores);
		State tie = new State(games[curr], 2, s.scores);
		if(curr+1 <= 6-n){
			recurse(games, win, curr+1);
			recurse(games, lose, curr+1);
			recurse(games, tie, curr+1);
		}
	}
}