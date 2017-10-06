#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, S, x, y, as, bs, ts;
int adj[1000][1000];
int dists[1000][1000];
long counts[1000];
vector<pair<pair<int, int>, int>> seekers;
bool visited[500];

bool dfs(int curr, int end, int t){
	visited[curr] = true;
	if(curr == end){
		counts[curr] += dists[curr][t];
		return true;
	}
	bool x = false;
	for(int i = 1; i <= N; i++){
		if(adj[curr][i] == 1 && !visited[i])
			x |= dfs(i, end, t);
	}
	if(x)counts[curr] += dists[curr][t];
	return x;
}

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &S);
	memset(dists, INFINITE, sizeof(dists));
	for(int i = 0; i < N-1; i++){
		scanf("%i%i", &x, &y);
		adj[x][y] = 1;
		adj[y][x] = 1;
		dists[x][y] = 1;
		dists[y][x] = 1;
	}
	for(int i = 0; i < S; i++){
		scanf("%i%i%i", &as, &bs, &ts);
		seekers.pb(mp(mp(as, bs), ts));
	}
	for(int k = 1; k <= N; k++){
		for(int i = 1; i <= N; i++){
			for(int j = 1; j <= N; j++){
				if(i == j)dists[i][j] = 0;
				if(dists[i][k] + dists[k][j] < dists[i][j])
					dists[i][j] = dists[i][k] + dists[k][j];
			}
		}
	}
	for(int i = 0; i < seekers.size(); i++){
		memset(visited, false, sizeof(visited));
		dfs(seekers[i].first.first, seekers[i].first.second, seekers[i].second);
	}
	for(int i = 1; i <= N; i++){
		cout << counts[i] << " ";
	}
	return 0;
}