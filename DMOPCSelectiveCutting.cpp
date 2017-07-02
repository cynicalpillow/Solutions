#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, Q;
int val[100005];
map<long long, vector<int>> vo;
map<tuple<long long, int, int>, vector<int>> qo;
long long result[100005];
long long tree[100005];
bool visited[100005];

void update(int idx, long long val){
	while(idx <= N){
		tree[idx] += val;
		idx += (idx & (-idx));
	}
}
long long query(int idx){
	long long sum = 0;
	while(idx > 0){
		sum += tree[idx];
		idx -= (idx & (-idx));
	}
	return sum;
}

int main(){
	//freopen("input.txt", "r", stdin);
	cin.sync_with_stdio(0);
    cin.tie(0);
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		int x;
		scanf("%i", &x);
		val[i] = x;
		vo[x].pb(i);
	}
	scanf("%i", &Q);
	for(int i = 0; i < Q; i++){
		int a, b;
		long long q;
		scanf("%i%i%lld", &a, &b, &q);
		auto tup = make_tuple(q, a, b);
		qo[tup].pb(i);
	}
	sort(val, val+N, greater<long long>());
	int curridx = 0;
	for (auto iter = qo.rbegin(); iter != qo.rend(); iter++) {
	    for(int x : iter->second){
	    	while(val[curridx] >= get<0>(iter->first)){
	    		if(!visited[val[curridx]])for(int y : vo[val[curridx]])update(y+1, val[curridx]);
	    		visited[val[curridx]] = true;
	    		curridx++;
	    	}
	    	result[x] = query(get<2>(iter->first)+1) - query(get<1>(iter->first));
	    }
	}
	for(int i = 0; i < Q; i++)cout << result[i] << "\n";
	return 0;
}