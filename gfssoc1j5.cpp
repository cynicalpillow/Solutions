#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
queue<pair<int, int>> q;
int dists[1005][1005];
vector<int> adj[1005];
int N, M, T, Q;

void bfs(int st){
	q.push(mp(st, 0));
	while(!q.empty()){
		pair<int, int> curr = q.front(); q.pop();
		for(int j : adj[curr.first]){
			if(dists[st][j] > curr.second+T){
				dists[st][j] = curr.second+T;
				q.push(mp(j, dists[st][j]));
			}
		}
	}
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> M >> T;
	for(int i = 1; i <= N; i++){
		for(int j = 1; j <= N; j++){
			dists[i][j] = INFINITE;
		}
	}
	for(int i = 0; i < M; i++){
		int a, b;
		cin >> a >> b;
		adj[a].pb(b);
	}
	for(int i = 1; i <= N; i++){
		bfs(i);
	}
	cin >> Q;
	for(int i = 0; i < Q; i++){
		int a, b;
		cin >> a >> b;
		if(dists[a][b] == INFINITE)
			cout << "Not enough hallways!" << "\n";
		else 
			cout << dists[a][b] << "\n";
	}
	return 0;
}