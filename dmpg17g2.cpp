#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

const int MAXN = 100005;
int INFINITE = 0x3f3f3f3f;

struct node{
	int l;
	int r;
	long long sum;
	long long maxP;
	long long maxS;
	long long maxC;
} tree[4*MAXN+1];
long long arr[MAXN];

void calc(int idx){
	tree[idx].sum = tree[2*idx].sum + tree[2*idx+1].sum; //total sum
	tree[idx].maxS = max(tree[2*idx+1].maxS, tree[2*idx+1].sum+tree[2*idx].maxS); //max suffix sum
	tree[idx].maxP = max(tree[2*idx].maxP, tree[2*idx].sum+tree[2*idx+1].maxP); //max prefix sum
	//max contiguous
	//either its the max contiguous of two children
	//or its in between the two children
	//or its the current max prefix/suffix
	tree[idx].maxC = tree[2*idx].maxC;
	tree[idx].maxC = max(tree[idx].maxC, tree[2*idx+1].maxC);
	tree[idx].maxC = max(tree[idx].maxC, tree[2*idx].maxS+tree[2*idx+1].maxP);
	tree[idx].maxC = max(tree[idx].maxC, tree[idx].maxP);
	tree[idx].maxC = max(tree[idx].maxC, tree[idx].maxS);
	tree[idx].maxC = max(tree[idx].maxC, tree[idx].sum);
}

void upd(int idx, int l, int r, int ci, int val){
	if(l == r){
		arr[l] = val;
		tree[idx].sum = val;
		tree[idx].maxS = val;
		tree[idx].maxP = val;
		tree[idx].maxC = val;
	} else {
		int mid = (l + r)/2;
		if(ci >= l && ci <= mid)upd(2*idx, l, mid, ci, val);
		else upd(2*idx+1, mid+1, r, ci, val);
		calc(idx);
	}
}

//query
node qry(int idx, int l, int r){
	if(r < tree[idx].l || l > tree[idx].r)return {0, 0, -INFINITE, -INFINITE, -INFINITE, -INFINITE};
	if(tree[idx].l >= l && tree[idx].r <= r)return tree[idx];
	node mL = qry(2*idx, l, r);
	node mR = qry(2*idx+1, l, r);
	calc(idx);
	node ret = {l, r, mL.sum+mR.sum, -INFINITE, -INFINITE, -INFINITE};
	ret.maxS = max(mR.maxS, mR.sum+mL.maxS); //max suffix sum
	ret.maxP = max(mL.maxP, mL.sum+mR.maxP); //max prefix sum
	ret.maxC = mL.maxC;
	ret.maxC = max(ret.maxC, mR.maxC);
	ret.maxC = max(ret.maxC, mL.maxS+mR.maxP);
	ret.maxC = max(ret.maxC, ret.maxP);
	ret.maxC = max(ret.maxC, ret.maxS);
	ret.maxC = max(ret.maxC, ret.sum);
	return ret;
}

//Initialize segment tree
void build(int idx, int l, int r){
	tree[idx].l = l;
	tree[idx].r = r;
	tree[idx].sum = -INFINITE;
	tree[idx].maxS = -INFINITE;
	tree[idx].maxP = -INFINITE;
	tree[idx].maxC = -INFINITE;
	if(l == r){
		tree[idx].sum = arr[l];
		tree[idx].maxS = arr[l];
		tree[idx].maxP = arr[l];
		tree[idx].maxC = arr[l];
		return;
	}
	int mid = (l+r)/2;
	build(2*idx, l, mid);
	build(2*idx+1, mid+1, r);
	calc(idx);
}

int N, Q, di, l, r;
long long x;
string ti;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> Q;
	for(int i = 1; i <= N; i++){
		cin >> x;
		arr[i] = x;
	}
	build(1, 1, N);
	for(int i = 0; i < Q; i++){
		cin >> ti;
		if(ti == "S"){
			cin >> di >> x;
			upd(1, 1, N, di, x);
		} else {
			cin >> l >> r;
			node result = qry(1, l, r);
			cout << result.maxC << "\n";
		}
	}
	return 0;
}