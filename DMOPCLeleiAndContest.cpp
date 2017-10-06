#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
long long tree[1000005];
long long lazy[1000005];
long long val[200005];
int N, M, Q;

void build(int node, int start, int end){
	if(start == end)tree[node] = val[start];
	else {
		int mid = (start + end)/2;
		build(2*node, start, mid);
		build(2*node+1, mid+1, end);
		tree[node] = tree[2*node] + tree[2*node+1];
	}
}
void updateRange(int node, int start, int end, int l, int r, long long value){
	//Update node from lazy array
	if(lazy[node] != 0){
		tree[node] += (end-start+1)*lazy[node];
		if(start != end){
			lazy[2*node] += lazy[node];
			lazy[2*node+1] += lazy[node];
		}
		lazy[node] = 0;
	}
	//Not contained in current segment
	if(start > end || start > r || end < l)return;
	//If contained, update lazy array
	if(start >= l && end <= r){
		tree[node] += (end-start+1)*value;
		if(start != end){
			lazy[2*node] += value;
			lazy[2*node+1] += value;
		}
		return;
	}
	//for overlapping intervals
	int mid = (start+end)/2;
	updateRange(2*node, start, mid, l, r, value);
	updateRange(2*node+1, mid+1, end, l, r, value);
	tree[node] = tree[2*node] + tree[2*node+1];
}
long long queryRange(int node, int start, int end, int l, int r){
	if(start > end || end < l || start > r)return 0;
	//If not updated, update
	if(lazy[node] != 0){
		tree[node] += (end-start+1)*lazy[node];
		if(start != end){
			lazy[2*node] += lazy[node];
			lazy[2*node+1] += lazy[node];
		}
		lazy[node] = 0;
	}
	if(start >= l && end <= r)return tree[node];
	int mid = (start+end)/2;
	long long v1 = queryRange(2*node, start, mid, l, r);
	long long v2 = queryRange(2*node+1, mid+1, end, l, r);
	return (v1+v2);
}
int main(){
	//freopen("input.txt", "r", stdin);
	memset(lazy, 0, sizeof(lazy));
	memset(tree, 0, sizeof(tree));
	scanf("%i%i%i", &M, &N, &Q);
	for(int i = 0; i < N; i++){
		long long x;
		scanf("%lld", &x);
		val[i] = x;
	}
	build(1, 0, N-1);
	for(int i = 0; i < Q; i++){
		int queryType;
		int l;
		int r;
		long long value = 0;
		scanf("%i", &queryType);
		if(queryType == 1){
			scanf("%i%i%lld", &l, &r, &value);
			l--;
			r--;
			updateRange(1, 0, N-1, l, r, value);
		} else {
			scanf("%i%i", &l, &r);
			l--;
			r--;
			long long result = queryRange(1, 0, N-1, l, r);
			result %= M;
			printf("%lld\n", result);
		}
	}
	return 0;
}