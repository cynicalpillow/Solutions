import java.util.*;
import java.math.*;
import java.io.*;
public class ANoisyClass {
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
	private static class Student {
		int id;
		boolean visited = false;
		boolean quiet = true;
		ArrayList<Student> adj = new ArrayList<>();
		public Student(int i){
			id = i;
		}
	}
	public static int count = 0;
	public static boolean loop = false;
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int n= s.nextInt();
		Student[] students = new Student[n];
		for(int i = 0; i < n; i++)students[i] = new Student(i+1);
		int m = s.nextInt();
		for(int i = 0; i < m; i++){
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			students[x].adj.add(students[y]);
			students[x].quiet = false;
		}
		for(int i= 0; i < n; i++){
			if(!students[i].visited){
				dfs(students[i]);
			}
		}
		if(count == n && !loop)System.out.println("Y");
		else System.out.println("N");
	}
	public static void dfs(Student s){
		s.visited = true;
		count++;
		for(Student st : s.adj){
			if(st.visited && !st.quiet){
				loop=true;
				return;
			}
			if(!st.visited)dfs(st);
		}
		s.quiet = true;
	}
}
