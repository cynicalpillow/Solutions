#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, R;
int pre[1005], low[1005];
bool visited[1005];
int pre_order = 1;
struct component {
	int count = 0;
};
vector<component> biconnected; //All biconnected components
int componentId[1005]; //Each node's associated component
struct edge {
	int u;
	int v;
	int id;
};
vector<edge> adj[1005]; //adjacency list
vector<edge> bridges; //Actual bridges
bool marked[1005]; //Bridge ids

void dfs(int c, int p){
	pre[c] = pre_order++;
	low[c] = pre[c];
	visited[c] = true;
	for(edge i : adj[c]){
		if(!visited[i.v]){
			dfs(i.v, c);
			if(low[i.v] > pre[c]){
				bridges.pb(i);
				marked[i.id] = true;
			}
			low[c] = min(low[c], low[i.v]);
		} else if(i.v != p){
			low[c] = min(low[c], pre[i.v]);
		}
	}
}

//This creates the components
void dfsComponents(int c, int cId){
	visited[c] = true;
	componentId[c] = cId; //Sets each node to a certain component
	for(edge i : adj[c])
		if(!visited[i.v] && !marked[i.id])
			dfsComponents(i.v, cId);
}

int solve(){
	memset(visited, false, sizeof(visited));
	for(int i = 1; i <= N; i++)
		if(!visited[i])
			dfs(i, i);
	memset(visited, false, sizeof(visited));
	int cx = 0;
	for(int i = 1; i <= N; i++){
		if(!visited[i]){
			component c;
			dfsComponents(i, cx);
			biconnected.pb(c);
			cx++;
		}
	}
	for(edge e : bridges){
		biconnected[componentId[e.u]].count++; //Increase the number of edges coming from components
		biconnected[componentId[e.v]].count++;
	}
	int penants = 0;
	for(component c : biconnected)
		if(c.count == 1)penants++;
	//The min number of edges needed to create a biconnected tree is ceil(num of penants/2)
	return ceil(penants/2.0);
}

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &R);
	for(int i = 0; i < R; i++){
		int x, y;
		scanf("%i%i", &x, &y);
		edge e1 = {x, y, i};
		edge e2 = {y, x, i};
		adj[x].pb(e1);
		adj[y].pb(e2);
	}
	printf("%i", solve());
	return 0;
}