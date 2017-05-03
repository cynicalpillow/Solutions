import java.util.*;
import java.math.*;
import java.io.*;
public class VMSSJeffreyAndFrankAndALackOfRoads {
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
	private static class State {
		int[] apples;
		int maxVal = 0;
		public State(int n){
			apples = new int[n];
		}
	}
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n= Integer.parseInt(st.nextToken());
		int budget = Integer.parseInt(st.nextToken());
		int size = Integer.parseInt(st.nextToken());
		String[] name = new String[n];
		int[] val = new int[n];
		int[] si = new int[n];
		int[] cost = new int[n];
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(s.readLine());
			name[i] = st.nextToken();
			val[i] = Integer.parseInt(st.nextToken());
			cost[i] = Integer.parseInt(st.nextToken());
			si[i] = Integer.parseInt(st.nextToken());
		}
		State[][] dp = new State[budget+1][size+1];
		for(int i = 0; i < budget+1; i++){
			for(int j = 0; j < size+1; j++){
				if(i == 0 || j == 0){
					dp[i][j] = new State(n);
					continue;
				}
				dp[i][j] = new State(n);
				dp[i][j].maxVal = dp[i-1][j].maxVal;
				for(int x = 0; x < n; x++){
					dp[i][j].apples[x] = dp[i-1][j].apples[x];
				}
				for(int z = 0; z < n; z++){
					if(i - cost[z] >= 0 && j - si[z] >= 0){
						if(dp[i-cost[z]][j-si[z]].maxVal + val[z] > dp[i][j].maxVal){
							for(int x = 0; x < n; x++){
								dp[i][j].apples[x] = dp[i-cost[z]][j-si[z]].apples[x];
							}
							dp[i][j].apples[z] += 1;
							dp[i][j].maxVal = dp[i-cost[z]][j-si[z]].maxVal + val[z];
						}
					}
				}
				if(dp[i][j-1].maxVal > dp[i][j].maxVal){
					dp[i][j].maxVal = dp[i][j-1].maxVal;
					for(int x = 0; x < n; x++){
						dp[i][j].apples[x] = dp[i][j-1].apples[x];
					}
				}
			}
		}
		System.out.println(dp[budget][size].maxVal);
		for(int i = 0; i < n; i++){
			System.out.println(name[i] + " " + dp[budget][size].apples[i]);
		}
	}
}
