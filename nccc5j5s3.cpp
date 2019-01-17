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
int N, M;
vector<pair<int, int>> adj[55];
vector<pair<int, int>> edges;
bool visited[55];

bool dfs(int curr, int dont_use){
	if(curr == N)return true;
	visited[curr] = true;
	bool poss = false;
	for(pair<int, int> p : adj[curr]){
		if(!visited[p.first] && p.second != dont_use){
			poss |= dfs(p.first, dont_use);
		}
	}
	return poss;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> M;
	for(int i = 0; i < M; i++){
		int si, ti;
		cin >> si >> ti;
		pair<int, int> edge = mp(ti, i);
		adj[si].pb(edge);
		edges.pb(edge);
	}
	for(pair<int, int> p : edges){
		memset(visited, false, sizeof(visited));
		if(dfs(1, p.second))cout << "YES\n";
		else cout << "NO\n";
	}
	return 0;
}