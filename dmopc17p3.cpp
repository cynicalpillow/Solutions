#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M;
vector<pair<int, int>> adj[100005];
struct node {
	int dangerous;
	int dist;
	int curr;
};
queue<node> q;
int dangerous[100005];
int min_roads[100005];

void bfs(){
	node n = {0, 0, 1};
	q.push(n);
	while(!q.empty()){
		node x = q.front(); q.pop();
		if(x.curr == N){
			if(dangerous[N] > x.dangerous){
				dangerous[N] = x.dangerous;
				min_roads[N] = x.dist;
			} else if(dangerous[N] == x.dangerous){
				min_roads[N] = min(x.dist, min_roads[N]);
			}
			continue;
		}
		for(pair<int, int> p : adj[x.curr]){
			if(dangerous[p.first] > x.dangerous+p.second){
				node next = {x.dangerous+p.second, x.dist+1, p.first};
				dangerous[p.first] = x.dangerous+p.second;
				min_roads[p.first] = x.dist+1;
				q.push(next);
			} else if(dangerous[p.first] == x.dangerous+p.second && min_roads[p.first] > x.dist+1){
				node next = {dangerous[p.first], x.dist+1, p.first};
				min_roads[p.first] = x.dist+1;
				q.push(next);
			}
		}
	}
	if(dangerous[N] == INFINITE || min_roads[N] == INFINITE)cout << -1;
	else cout << dangerous[N] << " " << min_roads[N];
}

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	memset(dangerous, INFINITE, sizeof(dangerous));
	memset(min_roads, INFINITE, sizeof(min_roads));
	scanf("%i%i", &N, &M);
	for(int i = 0; i < M; i++){
		int ai, bi, ti;
		scanf("%i%i%i", &ai, &bi, &ti);
		adj[ai].pb(mp(bi, ti));
		adj[bi].pb(mp(ai, ti));
	}
	bfs();
	return 0;
}