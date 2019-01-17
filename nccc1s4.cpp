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
int N, M, K, S, T;
int a[5005];
int b[5005];
int c[5005];
int d[5005];
vector<int> adj[1005];
bool visited[1005];
bool newStart[1005];

bool dfs(int u, int v){
	visited[u] = true;
	if(u == v)return true;
	bool x = false;
	for(int a : adj[u]){
		if(!visited[a])
			x |= dfs(a, v);
	}
	return x;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> M >> K >> S >> T;
	for(int i = 0; i < M; i++){
		int ai, bi, ci, di;
		cin >> ai >> bi >> ci >> di;
		a[i] = ai;
		b[i] = bi;
		c[i] = ci;
		d[i] = di;
	}
	int total = 0;
	for(int i = 1; i <= K; i++){
		for(int i = 1; i <= N; i++)adj[i].clear();
		for(int j = 0; j < M; j++){
			if(c[j] <= i && d[j] >= i){
				adj[a[j]].pb(b[j]);
				newStart[b[j]] = true;
				visited[a[j]] = visited[b[j]] = false;
			}
		}
		if(newStart[T])total += dfs(S, T);
	}
	cout << total;
	return 0;
}