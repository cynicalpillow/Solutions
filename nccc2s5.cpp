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
int N, M, Q;
vector<pair<int, int>> adj[1005];
bool visited[1005];
struct edgeInfo{
	int id;
	int from;
	int to;
	int weight;
};
vector<edgeInfo> edges;
int disjoint_set[1005];
int sizes[1005];

int comparea(edgeInfo a, edgeInfo b){
	return a.weight > b.weight;
}

int root(int a){
	while(a != disjoint_set[a])
		a = disjoint_set[a] = disjoint_set[disjoint_set[a]];
	return a;
}

void combine(int a, int b){
	int roota = root(a);
	int rootb = root(b);
	if(sizes[roota] > sizes[rootb]){
		disjoint_set[rootb] = roota;
		sizes[roota] += sizes[rootb];
		sizes[rootb] = sizes[roota];
	} else {
		disjoint_set[roota] = rootb;
		sizes[rootb] += sizes[roota];
		sizes[roota] = sizes[rootb];
	}
}

bool find(int a, int b){
	return (root(a) == root(b));
}

void generate_mst_max(){
	sort(edges.begin(), edges.end(), comparea);
	for(int i = 1; i <= N; i++){
		adj[i].clear();
		disjoint_set[i] = i;
		sizes[i] = 1;
	}
	int count = 0;
	for(edgeInfo e : edges){
		if(count == N-1)break;
		if(!find(e.from, e.to)){
			combine(e.from, e.to);
			adj[e.from].pb(mp(e.to, e.weight));
			adj[e.to].pb(mp(e.from, e.weight));
			count++;
		}
	}
}

void dfs(int curr, int weight){
	visited[curr] = true;
	for(pair<int, int> p : adj[curr]){
		if(!visited[p.first]){
			if(p.second >= weight)dfs(p.first, weight);
		}
	}
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> M;
	for(int i = 0; i < M; i++){
		int ui, vi, zi;
		cin >> ui >> vi >> zi;
		edgeInfo e = {i+1, ui, vi, zi};
		edges.pb(e);
	}
	generate_mst_max();
	cin >> Q;
	for(int i = 0; i < Q; i++){
		int type, ai, bi, wi, xi;
		cin >> type;
		if(type == 2){
			cin >> ai >> bi >> wi;
			memset(visited, false, sizeof(visited));
			dfs(ai, wi);
			cout << visited[bi] << "\n";
		} else {
			cin >> xi >> wi;
			for(edgeInfo &e : edges){
				if(e.id == xi){
					e.weight = wi;
					break;
				}
			}
			generate_mst_max();
		}
	}
	return 0;
}