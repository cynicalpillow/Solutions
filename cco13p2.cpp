#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

const int MAXN = 1050000;
int INFINITE = 0x3f3f3f3f;

struct node{
	int l;
	int r;
	int idx;
	int skill;
} tree[4*MAXN+1];
int treeWins[4*MAXN+1];
int arr[MAXN];

void calc(int idx){
	treeWins[tree[idx].idx]--;
	if(tree[2*idx].skill > tree[2*idx+1].skill) {
		tree[idx].skill = tree[2*idx].skill;
		tree[idx].idx = tree[2*idx].idx;
		treeWins[tree[2*idx].idx]++;
	} else {
		tree[idx].skill = tree[2*idx+1].skill;
		tree[idx].idx = tree[2*idx+1].idx;
		treeWins[tree[2*idx+1].idx]++;
	}
	
}

void upd(int idx, int l, int r, int ci, int val){
	if(l == r){
		arr[l] = val;
		tree[idx].skill = val;
	} else {
		int mid = (l + r)/2;
		if(ci >= l && ci <= mid)upd(2*idx, l, mid, ci, val);
		else upd(2*idx+1, mid+1, r, ci, val);
		calc(idx);
	}
}

//Initialize segment tree
void build(int idx, int l, int r){
	tree[idx].l = l;
	tree[idx].r = r;
	tree[idx].skill = 0;
	tree[idx].idx = -1;
	if(l == r){
		tree[idx].skill = arr[l];
		tree[idx].idx = l;
		return;
	}
	int mid = (l+r)/2;
	build(2*idx, l, mid);
	build(2*idx+1, mid+1, r);
	calc(idx);
}

int N, M;
string q;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> M;
	for(int i = 1; i <= 1<<N; i++){
		int x;
		cin >> x;
		arr[i] = x;
	}
	build(1, 1, 1<<N);
	for(int i = 0; i < M; i++){
		cin >> q;
		if(q == "R"){
			int id, x;
			cin >> id >> x;
			upd(1, 1, 1<<N, id, x);
		} else if(q == "W"){
			cout << tree[1].idx << "\n";
		} else {
			int id;
			cin >> id;
			cout << treeWins[id] << "\n";
		}
	}
	return 0;
}