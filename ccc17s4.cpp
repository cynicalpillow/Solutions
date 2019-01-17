#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M, D;
struct edge {
	int ai;
	int bi;
	long long ci;
	bool old;
};
vector<edge> edges;
int a[100005];

bool compare_edge(edge e1, edge e2){
	if(e1.ci == e2.ci){
		return e1.old > e2.old;
	} else {
		return e1.ci < e2.ci;
	}
}

int root(int u){
	while(a[u] != u){
		u = a[u] = a[a[u]];
	}
	return u;
}

void combine(int u, int v){
	int rootu = root(u);
	int rootv = root(v);
	a[rootu] = rootv;
}

bool find(int u, int v){
	return root(u) == root(v);
}

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i%i", &N, &M, &D);
	for(int i = 1; i <= N; i++)
		a[i] = i;
	for(int i = 1; i <= M; i++){
		int ai, bi;
		long long ci;
		scanf("%i%i%lld", &ai, &bi, &ci);
		bool curr = (i < N);
		edge e = {ai, bi, ci, curr};
		edges.pb(e);
	}
	sort(edges.begin(), edges.end(), compare_edge);
	long long maxe = 0;
	long long days = 0;
	long long ee = 0;
	int c = 0;
	for(edge e : edges){
		if(ee == N-1)break;
		if(!find(e.ai, e.bi)){
			ee++;
			maxe = e.ci;
			combine(e.ai, e.bi);
			if(!e.old)days++;
		}
		c++;
	}
	for(int i = 1; i <= N; i++)
		a[i] = i;
	for(edge e : edges){
		if(!find(e.ai, e.bi)){
			if(e.ci < maxe || e.ci == maxe && e.old){
				combine(e.ai, e.bi);
			} else if(e.old && e.ci <= D){
				cout << (days-1);
				return 0;
			}
		}
	}
	cout << days;
	return 0;
}