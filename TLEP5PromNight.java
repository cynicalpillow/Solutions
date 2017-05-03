import java.util.*;
import java.math.*;
import java.io.*;
public class TLEP5PromNight {
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
	private static class Male implements Comparable<Male>{
		int nums;
		Female[] ids;
		boolean[] visitedFemales;
		int id;
		public Male(int nums, int id){
			this.nums = nums;
			this.id = id;
			ids = new Female[nums];
		}
		public Male(int nums, int id, int m){
			this.nums = nums;
			this.id = id;
			ids = new Female[nums];
			visitedFemales = new boolean[m];
		}
		public int compareTo(Male m){
			if(m.nums < this.nums)return 1;
			else if(m.nums > this.nums)return -1;
			else return 0;
		}
	}
	private static class Female implements Comparable<Female>{
		int chosen = 0;
		int chooses = 0;
		int id;
		public Female(int id){
			this.id = id;
		}
		public int compareTo(Female o) {
			if(o.chosen > this.chosen)return 1;
			else if(o.chosen < this.chosen)return -1;
			else if(o.chosen == 1 && this.chosen == 1){
				if(o.chooses < this.chooses)return -1;
				else if(o.chooses > this.chooses)return 1;
				return 0;
			} else {
				return 0;
			}
		}
	}
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n = s.nextInt();
		int m = s.nextInt();
		Male[] males = new Male[n];
		Female[] females = new Female[m];
		for(int i = 0; i < m; i++){
			females[i] = new Female(i);
		}
		for(int i = 0; i < n; i++){
			int nums = s.nextInt();
			if(i > 0)males[i] = new Male(nums, i);
			else males[i] = new Male(nums, i, m);
			for(int j = 0; j < nums; j++){
				int id = s.nextInt()-1;
				males[i].ids[j] = females[id];
				if(i > 0){
					if(males[0].visitedFemales[id])females[id].chosen = 1;
					females[id].chooses++;
				} else {
					males[i].visitedFemales[id] = true;
				}
			}
		}
		Male firstMale = males[0];
		Arrays.sort(males);
		for(Male z : males){
			Arrays.sort(z.ids);
		}
		boolean[] visited = new boolean[m];
		for(int i = 0; i < n; i++){
			if(males[i].id != 0){
				for(int j = 0; j < males[i].nums; j++){
					if(!visited[males[i].ids[j].id]){
						visited[males[i].ids[j].id] = true;
						break;
					}
				}
			}
		}
		int count = 0;
		for(int i = 0; i < firstMale.nums; i++){
			if(!visited[firstMale.ids[i].id])count++;
		}
		System.out.println(count);
	}
}
