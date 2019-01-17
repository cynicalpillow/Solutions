#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, T, a, b, k;

//Max size
const int MAXN = 1000005;

//Tree node and tree array
struct node{
	int l;
	int r;
	long long sum;
	long long lazy;
} tree[4*MAXN+1];
int arr[MAXN];

//Main change for various problems, as we're storing different values
void calc(int idx){
	tree[idx].sum = tree[2*idx].sum + tree[2*idx+1].sum;
}

//Apply all deltas with respect to the other values stored in the nodes
void apply(int idx, long long lazy){
	tree[idx].lazy += lazy;
	tree[idx].sum += lazy * (tree[idx].r+1 - tree[idx].l);
}

//Add the deltas (lazy prop)
void prop(int idx){
	apply(2*idx, tree[idx].lazy);
	apply(2*idx+1, tree[idx].lazy);
	tree[idx].lazy = 0;
}

//update range / node
void upd(int idx, int l, int r, int val){
	if(r < tree[idx].l || l > tree[idx].r)return; //outside range
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

//query
long long qry(int idx, int l, int r){
	if(r < tree[idx].l || l > tree[idx].r)return 0;
	if(tree[idx].l >= l && tree[idx].r <= r)return tree[idx].sum;
	prop(idx);
	long long mL = qry(2*idx, l, r);
	long long mR = qry(2*idx+1, l, r);
	calc(idx);
	return (mL + mR);
}

//Initialize segment tree
void build(int idx, int l, int r){
	tree[idx].l = l; //left, right, lazy
	tree[idx].r = r;
	tree[idx].sum = 0;
	tree[idx].lazy = 0;
	if(l == r){//leaf
		tree[idx].sum = arr[l];
		return;
	}
	int mid = (l+r)/2;
	build(2*idx, l, mid);
	build(2*idx+1, mid+1, r);
	//process some values here ahead of time (e.g store mins, store sums)
	calc(idx);
}

bool is_prime[1000005];

void seive(){
	memset(is_prime, true, sizeof(is_prime));
	is_prime[1] = 0;
	for(int i = 2; i <= 1000001/2; i++){
		if(is_prime){
			for(int j = i*2; j <= 1000001; j += i){
				is_prime[j] = false;
			}
		}
	}
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> T;
	for(int i = 1; i <= N; i++){
		cin >> arr[i];
	}
	seive();
	build(1, 1, N);
	long long minCost = 1e15;
	for(int i = 1; i < T; i++){
		cin >> a >> b >> k;
		if(is_prime[i]){
			long long sum = qry(1, a, b) + k;
			minCost = min(minCost, i*sum);
		} else {
			upd(1, a, b, k);
		}
	}
	cout << minCost;
	return 0;
}