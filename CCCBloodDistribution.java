import java.util.*;
import java.math.*;
import java.io.*;
public class CCCBloodDistribution {
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
	public static void main(String args[]) throws Exception {
		Reader s = new Reader();
		int[] blood = new int[8];
		int[] peeps = new int[8];
		for(int i = 0; i < 8; i++)blood[i] = s.nextInt();
		for(int i = 0; i < 8; i++)peeps[i] = s.nextInt();
		int total = 0;
		for(int i = 0; i < 8; i+=2){
			int remove = Math.min(blood[i], peeps[i]);
			total += remove;
			blood[i] -= remove;
			peeps[i] -= remove;
			if(i == 2){
				remove = Math.min(blood[0], peeps[i]);
				total += remove;
				blood[0] -= remove;
				peeps[i] -= remove;
			} else if(i == 4){
				remove = Math.min(blood[0], peeps[i]);
				total += remove;
				blood[0] -= remove;
				peeps[i] -= remove;
			} else {
				remove = Math.min(blood[4], peeps[i]);
				total += remove;
				blood[0] -= remove;
				peeps[i] -= remove;
				
				remove = Math.min(blood[2], peeps[i]);
				total += remove;
				blood[0] -= remove;
				peeps[i] -= remove;
				
				remove = Math.min(blood[0], peeps[i]);
				total += remove;
				blood[0] -= remove;
				peeps[i] -= remove;
			}
		}
		for(int i = 1; i < 8; i+=2){
			int remove = Math.min(peeps[i], blood[i]);
			total += remove;
			blood[i] -= remove;
			peeps[i] -= remove;
			if(i == 1){
				remove = Math.min(peeps[i], blood[0]);
				total += remove;
				blood[0] -= remove;
				peeps[i] -= remove;
			} else if(i == 3){
				remove = Math.min(peeps[i], blood[2]);
				total += remove;
				blood[2] -= remove;
				peeps[i] -= remove;
				
				remove = Math.min(peeps[i], blood[1]);
				total += remove;
				blood[1] -= remove;
				peeps[i] -= remove;
				
				remove = Math.min(peeps[i], blood[0]);
				total += remove;
				blood[0] -= remove;
				peeps[i] -= remove;
			} else if(i == 5){
				remove = Math.min(peeps[i], blood[4]);
				total += remove;
				blood[4] -= remove;
				peeps[i] -= remove;
				
				remove = Math.min(peeps[i], blood[1]);
				total += remove;
				blood[1] -= remove;
				peeps[i] -= remove;
				
				remove = Math.min(peeps[i], blood[0]);
				total += remove;
				blood[0] -= remove;
				peeps[i] -= remove;
			} else {
				for(int j = 6; j >= 0; j--){
					remove = Math.min(peeps[i], blood[j]);
					total += remove;
					blood[j] -= remove;
					peeps[i] -= remove;
				}
			}
		}
		System.out.println(total);
	}
}
