#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M;
int c[105];
int r[105];
vector<pair<int, int>> adj[105];
int flow[105][105];
bool visited[105];
int levels[105];
queue<pair<int, int>> q;

bool bfs(){
	memset(levels, -1, sizeof(levels));
	q.push(mp(0, 0));
	levels[0] = 0;
	while(!q.empty()){
		pair<int, int> x = q.front();
		q.pop();
		if(x.first != N-1 && r[x.first] >= c[x.first])continue;
		for(pair<int, int> e : adj[x.first]){
			if(levels[e.first] < 0 && flow[x.first][e.first] < e.second){
				levels[e.first] = x.second+1;
				q.push(mp(e.first, levels[e.first]));
			}
		}
	}
	return (levels[N-1] < 0) ? false : true;
}

int dfs(int curr, int fl){
	if(curr == N-1)return fl;
	if(curr != N-1 && r[curr] >= c[curr])return 0;
	for(pair<int, int> e : adj[curr]){
		if(levels[e.first] == levels[curr]+1 && flow[curr][e.first] < e.second){
			int c_flow = min(c[curr] - r[curr], min(fl, e.second-flow[curr][e.first]));
			int t_flow = dfs(e.first, c_flow);
			if(t_flow > 0){
				flow[curr][e.first] += t_flow;
				flow[e.first][curr] -= t_flow;
				r[curr] += t_flow;
				return t_flow;
			}
		}
	}
	return 0;
}

void dinic(){
	int tt = 0;
	while(bfs()){
		memset(visited, false, sizeof(visited));
		while(int f = dfs(0, INFINITE))tt += f;
	}
	cout << tt;
}

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N-1; i++){
		int x;
		scanf("%i", &x);
		c[i] = x;
	}
	scanf("%i", &M);
	for(int i = 0; i < M; i++){
		int x, y;
		scanf("%i%i", &x, &y);
		x--;
		y--;
		adj[x].pb(mp(y, INFINITE));
	}
	dinic();
	return 0;
}