import java.util.*;
import java.math.*;
import java.io.*;
public class SegmentTreeTest {
	public static class Node{
		int min = Integer.MAX_VALUE;
		int gcd = 0;
		int count = 0;
		public Node(int m, int gc, int c){
			min = m;
			gcd = gc;
			count = c;
		}
		public Node(){}
	}
	public static Node[] tree;
	public static int[] a;
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(s.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		a = new int[n];
		tree = new Node[4*n+1];
		st = new StringTokenizer(s.readLine());
		for(int i = 0; i < n; i++)a[i] = Integer.parseInt(st.nextToken());
		build(1, 0, n-1);
		for(int i = 0; i < m; i++){
			st = new StringTokenizer(s.readLine());
			String q = st.nextToken();
			if(q.equals("C")) update(1, 0, n-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
			else if (q.equals("M")) System.out.println(queryMin(1, 0, n-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1));
			else if(q.equals("G")) System.out.println(queryGCD(1, 0, n-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1));
			else {
				int l = Integer.parseInt(st.nextToken())-1;
				int r = Integer.parseInt(st.nextToken())-1;
				int gcd = queryGCD(1, 0, n-1, l, r);
				System.out.println(queryCount(1, 0, n-1, l, r, gcd));
			}
		}
	}
	public static void cunt(int node){
		tree[node].min = Math.min(tree[node*2].min, tree[node*2+1].min);
		int c = 0;
		int gcd = gcd(tree[2*node].gcd, tree[2*node+1].gcd);
		if(tree[2*node].gcd == gcd)c+=tree[2*node].count;
		if(tree[2*node+1].gcd == gcd)c+=tree[2*node+1].count;
		tree[node].gcd = gcd;
		tree[node].count = c;
	}
	public static void build(int node, int start, int end){
		if(start == end)tree[node] = new Node(a[start], a[start], 1);
		else {
			int mid = (start + end)/2;
			build(2*node, start, mid);
			build(2*node+1, mid+1, end);
			tree[node] = new Node();
			cunt(node);
		}
	}
	public static void update(int node, int start, int end, int idx, int val){
		if(start == end){
			a[start] = val;
			tree[node].min = val;
			tree[node].gcd = val;
		} else {
			int mid = (start + end)/2;
			if(idx >= start && idx <= mid) update(2*node, start, mid, idx, val);
			else update(2*node+1, mid+1, end, idx, val);
			cunt(node);
		}
	}
	public static int queryMin(int node, int start, int end, int l, int r){
		if(end < l || start > r)return Integer.MAX_VALUE;
		else if(end <= r && start >= l)return tree[node].min;
		else {
			int mid = (start + end)/2;
			int p1 = queryMin(2*node, start, mid, l, r);
			int p2 = queryMin(2*node+1, mid+1, end, l, r);
			return Math.min(p1, p2);
		}
	}
	public static int queryGCD(int node, int start, int end, int l, int r){
		if(end < l || start > r)return 0;
		else if(end <= r && start >= l)return tree[node].gcd;
		else {
			int mid = (start + end)/2;
			int p1 = queryGCD(2*node, start, mid, l, r);
			int p2 = queryGCD(2*node+1, mid+1, end, l, r);
			return gcd(p1, p2);
		}
	}
	public static int queryCount(int node, int start, int end, int l, int r, int gcd){
		if(end < l || start > r)return 0;
		else if(end <= r && start >= l){
			if(tree[node].gcd == gcd)return tree[node].count;
			else return 0;
		} else {
			int mid = (start + end)/2;
			int p1 = queryCount(2*node, start, mid, l, r, gcd);
			int p2 = queryCount(2*node+1, mid+1, end, l, r, gcd);
			return p1 + p2;
		}
	}
	public static int gcd(int a, int b){
		if(b == 0) return a;
		else return gcd(b, a%b);
	}
}
