#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back
struct node {
	int id;
	int weight;
	int sun;
	int above;
	bool operator<(const node &rhs) const{
        return weight > rhs.weight;
    }
};

int INFINITE = 0x3f3f3f3f;
int S, N, E, s, t, d, u;
vector<node> adj[1601];
int dists[1601][3605];
priority_queue<node> q;

int dijkstra(){
	//0 = id, 1 = dist, 2 = sunlight
	node start;
	start.id = 0; start.weight = 0; start.sun = 0;
	q.push(start);
	dists[0][0] = 0;
	while(!q.empty()){
		node cr = q.top(); q.pop();
		if(dists[cr.id][cr.sun] < cr.weight)continue;
		if(cr.sun > S)continue;
		if(cr.id == N-1)
			return cr.weight;
		for(int i = 0; i < adj[cr.id].size(); i++){
			if(cr.sun+(adj[cr.id][i].weight*adj[cr.id][i].above) > S)continue;
			node n;
			n.id = adj[cr.id][i].id; n.weight = adj[cr.id][i].weight+cr.weight; n.sun = cr.sun+(adj[cr.id][i].weight*adj[cr.id][i].above);
			if(dists[adj[cr.id][i].id][n.sun] > n.weight){
				dists[adj[cr.id][i].id][n.sun] = n.weight;
				q.push(n);
			}
		}
	}
	return -1;
}

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i%i%i", &S, &N, &E);

	memset(dists, INFINITE, sizeof(dists));

	for(int i = 0; i < E; i++){
		scanf("%i%i%i%i", &s, &t, &d, &u);
		node z;
		z.id = t; z.weight = d; z.above = u;
		node y;
		y.id = s; y.weight = d; y.above = u;
		adj[s].pb(z);
		adj[t].pb(y);
	}
	int result = dijkstra();
	cout << result;
	return 0;
}