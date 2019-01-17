#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M, X, Y, ai, bi, ci, Q, f1, l1;
struct edge {
	int id;
	int ai;
	int bi;
	int ci;
};
struct better_tuple {
	int id;
	int v;
	int ci;
};
vector<better_tuple> adj[100005];
vector<edge> edges;
typedef pair<int, int> P;
priority_queue<P, vector<P>, greater<P>> q;
long long distsX[100005];
long long distsY[100005];
long long verticeStorm[100005];

void dijkstraX(){
	q.push(mp(0, X));
	distsX[X] = 0;
	while(!q.empty()){
		pair<int, int> curr = q.top(); q.pop();
		if(distsX[curr.second] < curr.first)continue;
		for(better_tuple p : adj[curr.second]){
			if(distsX[p.v] > curr.first + p.ci){
				distsX[p.v] = curr.first + p.ci;
				q.push(mp(distsX[p.v], p.v));
			}
		}
	}
}
void dijkstraY(){
	q.push(mp(0, Y));
	distsY[Y] = 0;
	while(!q.empty()){
		pair<int, int> curr = q.top(); q.pop();
		if(distsY[curr.second] < curr.first)continue;
		for(better_tuple p : adj[curr.second]){
			if(distsY[p.v] > curr.first + p.ci){
				distsY[p.v] = curr.first + p.ci;
				q.push(mp(distsY[p.v], p.v));
			}
		}
	}
}

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
    memset(distsX, INFINITE, sizeof(distsX));
    memset(distsY, INFINITE, sizeof(distsY));
	freopen("input.txt", "r", stdin);
	scanf("%i%i%i%i", &N, &M, &X, &Y);
	for(int i = 0; i < M; i++){
		scanf("%i%i%i", &ai, &bi, &ci);
		edge e = {i, ai, bi, ci};
		edges.pb(e);
		better_tuple e1 = {i, bi, ci};
		better_tuple e2 = {i, ai, ci};
		adj[ai].pb(e1);
		adj[bi].pb(e2);
	}
	dijkstraX();
	dijkstraY();
	for(int i = 1; i <= N; i++){
		if(distsX[i] == distsY[i])
			verticeStorm[i] = distsX[i];
		else
			verticeStorm[i] = -1;
	}
	scanf("%i", &Q);
	for(int i = 1; i <= Q; i++){
		scanf("%i%i", &f1, &l1);
		if(f1 == 1){
			printf("%lld\n", verticeStorm[l1]);
		} else {
			l1--;
			if(verticeStorm[edges[l1].ai] != -1 && verticeStorm[edges[l1].bi] != -1){
				printf("%lld\n", min(verticeStorm[edges[l1].ai], verticeStorm[edges[l1].bi]));
			} else if((verticeStorm[edges[l1].ai] != -1 || verticeStorm[edges[l1].bi] != -1)){
				if(verticeStorm[edges[l1].ai] != -1) {
					if(verticeStorm[edges[l1].ai] == min(distsX[edges[l1].bi], distsY[edges[l1].bi]) + edges[l1].ci){
						printf("%i\n", -1);
					} else {
						printf("%lld\n", verticeStorm[edges[l1].ai]);
					}
				} else {
					if(min(distsX[edges[l1].ai], distsY[edges[l1].ai]) + edges[l1].ci == verticeStorm[edges[l1].bi]){
						printf("%i\n", -1);
					} else {
						printf("%lld\n", verticeStorm[edges[l1].bi]);
					}
				}
			} else if(distsX[edges[l1].bi] > distsY[edges[l1].bi] && distsX[edges[l1].ai] < distsY[edges[l1].ai]){
				printf("%.2f\n", (distsX[edges[l1].ai] + distsY[edges[l1].bi] + edges[l1].ci)/2.00000);
			} else if(distsX[edges[l1].ai] > distsY[edges[l1].ai] && distsX[edges[l1].bi] < distsY[edges[l1].bi]){
				printf("%.2f\n", (distsX[edges[l1].bi] + distsY[edges[l1].ai] + edges[l1].ci)/2.00000);
			} else {
				printf("%i\n", -1);
			}
		}
	}
	return 0;
}