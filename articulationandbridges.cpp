#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M, X, Y;
struct edge {
	int u;
	int v;
};
vector<edge> adj[15];
int pre[15];
int low[15];
vector<edge> bridges;
vector<int> artPoints;
bool visited[15];
int pre_num = 1;
int cRoot;
int fwd;

bool bridge_compare(edge b1, edge b2){
	if(b1.u == b2.u){
		return b1.v < b2.v;
	} else {
		return b1.u < b2.u;
	}
}

//Gets all the bridges and articulation points
void dfs(int curr, int p){
	pre[curr] = pre_num++;
	low[curr] = pre[curr];
	visited[curr] = true;
	for(edge e : adj[curr]){
		if(!visited[e.v]){
			if(cRoot == curr)fwd++;
			dfs(e.v, curr);
			if(low[e.v] > pre[curr])bridges.pb(e);
			if(low[e.v] >= pre[curr] && cRoot != curr)artPoints.pb(curr);
			low[curr] = min(low[e.v], low[curr]);
		} else if(e.v != p){
			low[curr] = min(low[curr], pre[e.v]);
		}
	}
}

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	memset(visited, false, sizeof(visited));
	scanf("%i%i", &N, &M);
	for(int i = 0; i < M; i++){
		scanf("%i%i", &X, &Y);
		edge e = {X, Y};
		edge e1 = {Y, X};
		adj[X].pb(e);
		adj[Y].pb(e1);
	}
	for(int i = 0; i < N; i++){
		if(!visited[i]){
			cRoot = i;
			fwd = 0;
			dfs(i, i);
			if(fwd > 1)artPoints.pb(i);
		}
	}
	sort(artPoints.begin(), artPoints.end());
	sort(bridges.begin(), bridges.end(), bridge_compare);
	int n = artPoints.size();
	printf("%i\n", n);
	for(int i : artPoints)
		printf("%i ", i);
	printf("\n");
	n = bridges.size();
	printf("%i\n", n);
	for(edge b : bridges)
		printf("%i %i\n", b.u, b.v);
	return 0;
}