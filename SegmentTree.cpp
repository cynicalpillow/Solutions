#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int tree[20001];
int val[5000];
int N, M;

void build(int node, int start, int end){
	if(start == end)tree[node] = val[start];
	else {
		int mid = (start + end)/2;
		build(2*node, start, mid);
		build(2*node+1, mid+1, end);
		tree[node] = min(tree[2*node], tree[2*node+1]);
	}
}
int update(int node, int start, int end, int idx, int value){
	if(start == end){
		val[start] = value;
		tree[node] = value;
	} else {
		int mid = (start + end)/2;
		if(idx >= start && idx <= mid)update(2*node, start, mid, idx, value);
		else update(2*node+1, mid+1, end, idx, value);
		tree[node] = min(tree[2*node], tree[2*node+1]);
	}
}
int queryMin(int node, int start, int end, int l, int r){
	if(end < l || start > r)return INFINITE;
	else if(end <= r && start >= l)return tree[node];
	else {
		int mid = (start + end)/2;
		int p1 = queryMin(2*node, start, mid, l, r);
		int p2 = queryMin(2*node+1, mid+1, end, l, r);
		return min(p1, p2);
	}
}
int main(){
	freopen("input.txt", "r", stdin);
	memset(tree, INFINITE, sizeof(tree));
	scanf("%i%i", &N, &M);
	for(int i = 0; i < N; i++){
		int x;
		scanf("%i", &x);
		val[i] = x;
	}
	build(1, 0, N-1);
	for(int i = 0; i < M; i++){
		int queryType;
		int l;
		int r;
		int idx;
		int value;
		scanf("%i", &queryType);
		if(queryType == 0){
			scanf("%i%i", &l, &r);
			int result = queryMin(1, 0, N-1, l, r);
			printf("%i\n", result);
		} else {
			scanf("%i%i", &idx, &value);
			update(1, 0, N-1, idx, value);
		}
	}
	return 0;
}