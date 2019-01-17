#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back
#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)

long long BIT1[200005], BIT2[200005];
int INFINITE = 0x3f3f3f3f;
int N, M, Q;

void update(long long* tree, int idx, long long val){
	while(idx <= N){
		tree[idx] += val;
		idx += idx & (-idx);
	}
}

long long query(long long* tree, int idx){
	long long sum = 0;
	while(idx > 0){
		sum += tree[idx];
		idx -= idx & (-idx);
	}
	return sum;
}

long long query(int b){
	return query(BIT1, b)*b - query(BIT2, b);
}

long long query_range(int i, int j){
	return query(j) - query(i-1);
}

void range_update(int i, int j, long long val){
	update(BIT1, i, val);
	update(BIT1, j+1, -val);
	update(BIT2, i, val*(i-1));
	update(BIT2, j+1, -val*j);
}

int main(){
	freopen("input.txt", "r", stdin);
	cin >> M >> N >> Q;
	long long val, prev;
	for(int i = 1; i <= N; i++){
		cin >> val;
		range_update(i, i, val);
	}
	int type, l, r;
	for(int i = 0; i < Q; i++){
		cin >> type >> l >> r;
		if(type == 1){
			cin >> val;
			range_update(l, r, val);
		} else {
			cout << query_range(l, r) % M << "\n";
		}
	}
	return 0;
}