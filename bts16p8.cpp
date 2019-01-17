#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

//Max size
const int MAXN = 500005;

int INFINITE = 0x3f3f3f3f;
//Tree node and tree array
struct node{
	int l;
	int r;
	long long h;
	long long sum;
	long long lazy;
	bool flag = false;
} tree[6*MAXN+1];

int N, wn;
long long sn, ln;
/*As sn and ln can go up to 10^9, we need to store the unique x indices, this way we can update
with respect to the original positions and lengths */
unordered_map<long long, int> mpp;
long long locs[2*MAXN+1];
pair<long long, pair<long long, int>> qs[MAXN];

//Main change for various problems, as we're storing different values
void calc(int idx){
	tree[idx].h = max(tree[2*idx].h, tree[2*idx+1].h);
	tree[idx].sum = tree[2*idx].sum + tree[2*idx+1].sum;
}

//Apply all deltas with respect to the other values stored in the locs
void apply(int idx, long long lazy){
	tree[idx].sum = lazy * (locs[tree[idx].r] - locs[tree[idx].l]);
	tree[idx].h = lazy;
	tree[idx].lazy = lazy;
	tree[idx].flag = true;
}

//Add the deltas (lazy prop)
void prop(int idx){
	if(!tree[idx].flag)return;
	apply(2*idx, tree[idx].lazy);
	apply(2*idx+1, tree[idx].lazy);
	tree[idx].flag = false;
}

//update range / node
void upd(int idx, int l, int r, long long val){
	if(r <= tree[idx].l || l >= tree[idx].r)return; //outside range
	if(tree[idx].l >= l && tree[idx].r <= r){ //lazy update
		apply(idx, val);
		return;
	}
	//Partial coverage
	prop(idx); //push changes down to children when needed ALWAYS
	upd(2*idx, l, r, val);
	upd(2*idx+1, l, r, val);
	calc(idx);
}

//query max height
long long qryH(int idx, int l, int r){
	if(r <= tree[idx].l || l >= tree[idx].r)return 0;
	if(tree[idx].l >= l && tree[idx].r <= r)return tree[idx].h;
	prop(idx);
	int mL = qryH(2*idx, l, r);
	int mR = qryH(2*idx+1, l, r);
	return max(mL, mR);
}

//query sum of blocks
long long qry(int idx, int l, int r){
	if(r <= tree[idx].l || l >= tree[idx].r)return 0;
	if(tree[idx].l >= l && tree[idx].r <= r)return tree[idx].sum;
	prop(idx);
	long long mL = qry(2*idx, l, r);
	long long mR = qry(2*idx+1, l, r);
	return mL + mR;
}

//Initialize segment tree
void build(int idx, int l, int r){
	tree[idx].l = l;
	tree[idx].r = r;
	if(l == r-1)return;
	int mid = (l+r)/2;
	build(2*idx, l, mid);
	build(2*idx+1, mid, r);
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		cin >> sn >> ln >> wn;
		locs[i<<1] = sn;
		locs[(i<<1)+1] = sn+ln;
		qs[i] = mp(sn, mp(sn+ln, wn));
	}
	sort(locs, locs + N*2);
	int end = unique(locs, locs + N*2) - locs;
	for(int i = 0; i < end; i++)mpp[locs[i]] = i;
	build(1, 0, end);
	long long total = 0;
	for(int i = 0; i < N; i++){
		sn = qs[i].first;
		ln = qs[i].second.first;
		wn = qs[i].second.second;
		int l = mpp[sn];
		int r = mpp[ln];
		int mxh = qryH(1, l, r);
		long long blcks = qry(1, l, r);
		total += ((mxh*(ln-sn))-blcks);
		upd(1, l, r, mxh+wn);
	}
	cout << total%1000000007;
	return 0;
}