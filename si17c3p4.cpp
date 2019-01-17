#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M, ai, bi;
int pre[100005], low[100005];
struct edge{
	int id;
	int u;
	int v;
};
struct component{
	int size;
};
vector<edge> adj[100005];
bool visited[100005];
vector<component> component_list;
stack<edge> edge_stack;
int pre_order = 1;
bool cactus = true;
int cRoot;
int fwd;
bool in_stack[100005];
int linked[100005];
bool articulation[100005];

component make_component(int id){
	component c;
	int count = 1;
	while(!edge_stack.empty() && edge_stack.top().id != id){
		edge_stack.pop();
		count++;
	}
	c.size = count+1;
	edge_stack.pop();
	return c;
}

void find_components(int u, int p, edge e1){
	//cout << u << endl;
	if(!in_stack[e1.id] && p != -1)
		edge_stack.push(e1);
	pre[u] = pre_order++;
	low[u] = pre[u];
	visited[u] = true;
	for(edge e : adj[u]){
		if(!visited[e.v]){
			if(cRoot)fwd++;
			find_components(e.v, u, e);
			if(p == -1 && fwd > 1){
				articulation[u] = true;
				component_list.pb(make_component(e.id));
			}
			if(p != -1 && low[e.v] >= pre[u]){
				articulation[u] = true;
				component_list.pb(make_component(e.id));
			}
			low[u] = min(low[u], low[e.v]);
			linked[u] = linked[e.v];
		} else if(e.v != p){
			if(linked[u] != -1 && linked[u] != e.v && !articulation[u])cactus = false;
			//if(linked[e.v] != -1 && linked[e.v] != u && !articulation[e.v])cactus = false;
			linked[u] = e.v;
			linked[e.v] = u;
			low[u] = min(low[u], pre[e.v]);
		}
	}
}

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	//freopen("input.txt", "r", stdin);
	memset(visited, false, sizeof(visited));
	memset(linked, -1, sizeof(linked));
	scanf("%i%i", &N, &M);
	for(int i = 0; i < M; i++){
		scanf("%i%i", &ai, &bi);
		edge e = {i, ai, bi};
		edge e1 = {i, bi, ai};
		adj[ai].pb(e);
		adj[bi].pb(e1);
	}
	cRoot = 1; fwd = 0;
	edge e;
	find_components(1, -1, e);
	if(!cactus){
		cout << "safe" << endl;
		return 0;
	}
	for(int i = 1; i <= N; i++){
		if(!visited[i]){
			cout << "safe" << endl;
			return 0;
		}
	}
	long long total = 1;
	for(component j : component_list){
		if(j.size > 2)total *= j.size;
		total %= 1000000007;
	}
	cout << total;
	return 0;
}