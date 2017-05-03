import java.util.*;
import java.math.*;
import java.io.*;
public class PickIt {
	public static int n;
	public static State[][] dp;
	public static ArrayList<Integer> val;
	private static class State {
		ArrayList<Integer> a = new ArrayList<>();
		int val;
		int lastIndex;
		public State(State s){
			val = s.val;
			a = new ArrayList<Integer>(s.a);
			lastIndex = s.lastIndex;
		}
		public State(){
			val = -1;
		}
	}
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		String x = s.readLine();
		while(!x.equals("0")){
			StringTokenizer st = new StringTokenizer(x);
			n = Integer.parseInt(st.nextToken());
			int[] vals = new int[n];
			for(int i = 0; i < n; i++){
				vals[i] = Integer.parseInt(st.nextToken());
			}
			dp = new State[n][n];
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++)dp[i][j] = new State();
			}
			int max = Integer.MIN_VALUE;
			val = new ArrayList<>();
			for(int j = 0; j < n; j++){
				val.add(vals[j]);
			}
			for(int i = 1; i < n-1; i++){
				dp[1][i].val = vals[i] + vals[i-1] + vals[i+1];
				dp[1][i].a = new ArrayList<Integer>(val);
				dp[1][i].a.remove(i);
				dp[1][i].lastIndex = i;
			}
			for(int i= 0; i < n; i++){
				for(int j = 0; j < n; j++){
					System.out.print(dp[i][j].val + " ");
				}
				System.out.println();
			}
			for(int i = 1; i < n-1; i++){
				State z = dpRecurse(i, n-2, n, vals);
				System.out.println("hi " );
				for(int j = 0; j < z.a.size(); j++){
					System.out.print(z.a.get(j) + " ");
				}
				System.out.println();
				max = Math.max(z.val, max);
			}
			for(int i= 0; i < n; i++){
				for(int j = 0; j < n; j++){
					System.out.print(dp[i][j].val + " ");
				}
				System.out.println();
			}
			for(int i= 0; i < n; i++){
				for(int j = 0; j < n; j++){
					System.out.print(dp[i][j].lastIndex + " ");
				}
				System.out.println();
			}
			System.out.println(max);
			x = s.readLine();
		}
	}
	private static State dpRecurse(int idx, int turn, int n, int[] ovals) {
		State x = new State();
		if(dp[turn][idx].val != -1){
			return dp[turn][idx];
		}
		int currentIndex = idx;
		for(int i = 1; i < n-1; i++){
			if(i != idx){
				State curr = dpRecurse(i, turn-1, n, ovals);
				if(x.val < curr.val){
					x = new State(curr);
				}
			}
		}
		if(x.a.size() > 2){
			if(currentIndex >= x.lastIndex)currentIndex--;
			System.out.println("hi fucker " + x.lastIndex + " " + currentIndex + " " + turn);
			for(int i = 0; i < x.a.size(); i++){
				System.out.print(x.a.get(i) + " ");
			}
			System.out.println();
			if(currentIndex+1 < x.a.size()){
				x.val += x.a.get(currentIndex) + x.a.get(currentIndex-1) + x.a.get(currentIndex+1);
				x.a.remove(currentIndex);
			}
			x.lastIndex = currentIndex;
			dp[turn][idx] = x;
		}
		return x;
	}
}
