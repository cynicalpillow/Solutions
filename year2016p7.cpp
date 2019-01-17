#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

const int MAXN = 100005;
long long INFINITE = 0x3f3f3f3f3f3f;
long long K;

struct node{
	int l;
	int r;
	//Over whole range
	long long miw;
	long long maw;
	long long wl;
	int lw;
	int rw;
	//over prefix
	long long mip;
	long long map;
	long long pl;
	int lp;
	int rp;
	//over suffix
	long long mis;
	long long mas;
	long long sl;
	int ls;
	int rs;
	//total
	long long tl; //length
	int lt;
	int rt;
} tree[4*MAXN+1];
int arr[MAXN];

void merge(node &x, int idx){
	//over whole range
	x.miw = min(tree[2*idx].miw, tree[2*idx+1].miw); //min over whole range
	x.maw = max(tree[2*idx].maw, tree[2*idx+1].maw); //max over whole range
	if(x.maw - x.miw <= K){
		x.wl = abs(tree[2*idx].l - tree[2*idx+1].r+1);
		x.lw = tree[2*idx].l;
		x.rw = tree[2*idx+1].r;
	} else {
		x.lw = x.l;
		x.rw = x.r;
		x.wl = 0;
	}
	//over prefix
	if(max(tree[2*idx].maw, tree[2*idx+1].map) - min(tree[2*idx].miw, tree[2*idx+1].mip) <= K){
		x.pl = abs((tree[2*idx].l - tree[2*idx].r+1) + tree[2*idx+1].pl); //whole left + right prefix
		x.mip = min(tree[2*idx].miw, tree[2*idx+1].mip);
		x.map = max(tree[2*idx].maw, tree[2*idx+1].map);
		x.lp = tree[2*idx].l;
		x.rp = tree[2*idx+1].rp;
	} else {
		x.pl = tree[2*idx].pl; //left prefix
		x.mip = tree[2*idx].mip;
		x.map = tree[2*idx].map;
		x.lp = tree[2*idx].l;
		x.rp = tree[2*idx].rp;
	} 
	//over suffix
	if(max(tree[2*idx+1].maw, tree[2*idx].mas) - min(tree[2*idx+1].miw, tree[2*idx].mis) <= K){
		x.sl = abs((tree[2*idx+1].l - tree[2*idx+1].r+1) + tree[2*idx].sl); //whole right + left suffix
		x.mis = min(tree[2*idx+1].miw, tree[2*idx].mis);
		x.mas = max(tree[2*idx+1].maw, tree[2*idx].mas);
		x.ls = tree[2*idx].ls;
		x.rs = tree[2*idx+1].r;
	} else {
		x.sl = tree[2*idx+1].sl; //right suffix
		x.mis = tree[2*idx+1].mis;
		x.mas = tree[2*idx+1].mas;
		x.ls = tree[2*idx+1].ls;
		x.rs = tree[2*idx+1].r;
	} 
	//in between
	x.tl = tree[2*idx].tl;
	x.lt = tree[2*idx].lt;
	x.rt = tree[2*idx].rt;
	if(tree[2*idx+1].tl > x.tl){
		x.tl = tree[2*idx+1].tl;
		x.lt = tree[2*idx+1].lt;
		x.rt = tree[2*idx+1].rt;
	}
	if(max(tree[2*idx].mas, tree[2*idx+1].map) - min(tree[2*idx].mis, tree[2*idx+1].mip) <= K && tree[2*idx].sl + tree[2*idx+1].pl > x.tl){
		x.tl = tree[2*idx].sl + tree[2*idx+1].pl;
		x.lt = tree[2*idx].ls;
		x.rt = tree[2*idx+1].rp;
	} else if (max(tree[2*idx].mas, tree[2*idx+1].map) - min(tree[2*idx].mis, tree[2*idx+1].mip) <= K && tree[2*idx].sl + tree[2*idx+1].pl == x.tl && tree[2*idx].ls < x.lt){
		x.lt = tree[2*idx].ls;
		x.rt = tree[2*idx+1].rp;
	}
	if(x.pl > x.tl){
		x.tl = x.pl;
		x.lt = x.lp;
		x.rt = x.rp;
	} else if(x.pl == x.tl && x.lp < x.lt){
		x.lt = x.lp;
		x.rt = x.rp;
	}
	if(x.sl > x.tl){
		x.tl = x.sl;
		x.lt = x.ls;
		x.rt = x.rs;
	} else if(x.sl == x.tl && x.ls < x.lt){
		x.lt = x.ls;
		x.rt = x.rs;
	}
	if(x.wl > x.tl){
		x.tl = x.wl;
		x.lt = x.lw;
		x.rt = x.rw;
	} else if(x.wl == x.tl && x.lw < x.lt){
		x.lt = x.lw;
		x.rt = x.rw;
	}
}

void calc(int idx){
	merge(tree[idx], idx);
}

//query
node qry(int idx, int l, int r){
	if(r < tree[idx].l || l > tree[idx].r)
		return {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
	if(tree[idx].l >= l && tree[idx].r <= r)
		return tree[idx];
	node mL = qry(2*idx, l, r);
	node mR = qry(2*idx+1, l, r);
	calc(idx);
	node ret;
	if(mL.wl == -1)return mR;
	else if(mR.wl == -1)return mL;
	merge(ret, idx);
	//cout << ret.wl << " " << ret.rt << endl;
	return ret;
}

//Initialize segment tree
void build(int idx, int l, int r){
	tree[idx].l = l; tree[idx].r = r;
	tree[idx].miw = -INFINITE; tree[idx].maw = INFINITE; tree[idx].wl = tree[idx].lw = tree[idx].rw = 0;
	tree[idx].mip = -INFINITE; tree[idx].map = INFINITE; tree[idx].pl = tree[idx].lp = tree[idx].rp = 0;
	tree[idx].mis = -INFINITE; tree[idx].mas = INFINITE; tree[idx].sl = tree[idx].ls = tree[idx].rs = 0;
	tree[idx].tl = tree[idx].lt = tree[idx].rt = 0;
	if(l == r){
		tree[idx].miw = tree[idx].maw = tree[idx].mip = tree[idx].map = tree[idx].mis = tree[idx].mas = arr[l];
		tree[idx].tl = tree[idx].sl = tree[idx].pl = tree[idx].wl = 1;
		tree[idx].lw = tree[idx].rw = tree[idx].lp = tree[idx].rp = tree[idx].ls = tree[idx].rs = tree[idx].lt = tree[idx].rt = l;
		return;
	}
	int mid = (l+r)/2;
	build(2*idx, l, mid);
	build(2*idx+1, mid+1, r);
	calc(idx);
}

int N, ai, Q, l, r;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> K;
	for(int i = 1; i <= N; i++){
		cin >> ai;
		arr[i] = ai;
	}
	build(1, 1, N);
	cin >> Q;
	for(int i = 0; i < Q; i++){
		cin >> l >> r;
		node result = qry(1, l, r);
		cout << result.lt << " " << result.rt << "\n";
	}
	/*for(int i = 1; i<=4*N; i++){
		cout << tree[i].lt << " " << tree[i].rt << endl;
	}*/
	return 0;
}