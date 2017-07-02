#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int K, N;
vector<int> lt;
vector<tuple<int, int, int>> adj[100005];
bool marker[100005];
int lo = 0;
int lid = 0;
int total = 0;
bool visited[100005];
bool visited2[100005];
bool edgeVisited[100005];
map<int, int> emap;
vector<int> currAdded;

void dfs(int curr, int dist){
	visited[curr] = true;
	for(int i = 0; i < adj[curr].size(); i++){
		if(!visited[get<0>(adj[curr][i])]){
			currAdded.pb(get<2>(adj[curr][i]));
			dfs(get<0>(adj[curr][i]), dist+get<1>(adj[curr][i]));
			currAdded.pop_back();
		}
	}
	if(marker[curr])for(int i = 0; i < currAdded.size(); i++)edgeVisited[currAdded[i]] = true;
	if(marker[curr] && dist > lo){
		lo = dist;
		lid = curr;
	}
}

void dfs2(int curr, int dist){
	visited2[curr] = true;
	for(int i = 0; i < adj[curr].size(); i++)if(!visited2[get<0>(adj[curr][i])])dfs2(get<0>(adj[curr][i]), dist+get<1>(adj[curr][i]));
	if(marker[curr] && dist > lo){
		lo = dist;
		lid = curr;
	}
}

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &K);
	for(int i = 0; i < K; i++){
		int k;
		scanf("%i", &k);
		lt.pb(k);
		marker[k] = true;
	}
	for(int i = 0; i < N-1; i++){
		int x, y, d;
		scanf("%i%i%i", &x, &y, &d);
		adj[x].pb(make_tuple(y, d, i));
		adj[y].pb(make_tuple(x, d, i));
		emap.insert(mp(i, d));
	}
	dfs(lt[0], 0);
	dfs2(lid, 0);
	for(int i = 0; i < N-1; i++)if(edgeVisited[i])total += emap[i];
	cout << (lo+(total - lo)*2);
	return 0;
}