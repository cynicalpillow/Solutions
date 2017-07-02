#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M, Q;
vector<pair<int, int>> adj[100005];
long longest = 0;
int longestId = 0;
bool visited[100005];
bool sVisited[100005];
vector<long> diameters;
vector<int> dIds;
long ds[100005];
pair<int, long> parent[100005];
vector<int> currAdded;

void dfs(int id, long dist){
	visited[id] = true;
	for(int i = 0; i < adj[id].size(); i++)if(!visited[adj[id][i].first])dfs(adj[id][i].first, dist+adj[id][i].second);
	if(longest < dist){
		longest = dist;
		longestId = id;
	}
}
void dfs2(int id, long dist){
	sVisited[id] = true;
	for(int i = 0; i < adj[id].size(); i++)if(!sVisited[adj[id][i].first])dfs2(adj[id][i].first, dist+adj[id][i].second);
	if(longest < dist){
		longest = dist;
		longestId = id;
	}
}
void dfsPath(int id, long dist, int prev, long prevlen){
	sVisited[id] = true;
	currAdded.pb(id);
	ds[id] = max(ds[id], dist);
	parent[id] = mp(prev, prevlen);
	for(int i = 0; i < adj[id].size(); i++)if(!sVisited[adj[id][i].first])dfsPath(adj[id][i].first, dist+adj[id][i].second, id, adj[id][i].second);
	if(longest < dist){
		longest = dist;
		longestId = id;
		dIds = currAdded;
	}
	currAdded.pop_back();
}

void solveLargest(){
	for(int i = 1; i <= N; i++){
		if(visited[i])continue;
		dfs(i, 0);
		longest = 0;
		dfs2(longestId, 0);
		diameters.pb(longest);
		longest = 0;
		longestId = 0;
	}
	long result = 0;
	for(int i = 0; i < diameters.size(); i++)result += diameters[i];
	cout << result + (diameters.size()-1);
}
void solveSmallest(){
	long mR = 0;
	for(int i = 1; i <= N; i++){
		if(visited[i])continue;
		dfs(i, 0);
		longest = 0;
		dfsPath(longestId, 0, longestId, 0);
		long maxmin = -1;
		if(dIds.size() == 0)maxmin = 0;
		else if(dIds.size() == 1)maxmin = 0;
		else {
			long d = 0;
			for(int i = dIds.size()-1; i >= 0; i--){
				d += parent[dIds[i]].second;
				ds[parent[dIds[i]].first] = max(ds[parent[dIds[i]].first], d);
				if(maxmin == -1)maxmin = ds[parent[dIds[i]].first];
				else maxmin = min(ds[parent[dIds[i]].first], maxmin);
			}
		}
		if(mR == 0)mR = maxmin;
		else {
			if(maxmin == mR)mR = maxmin+1;
			else if(maxmin > mR)mR = maxmin;
		}
		longest = 0;
		longestId = 0;
		vector<int>().swap(dIds);
	}
	if(mR == 0)mR++;
	cout << mR;
}
int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i%i%i", &N, &M, &Q);
	memset(ds, 0, sizeof(ds));
	for(int i = 0; i < M; i++){
		int x, y, z;
		scanf("%i%i%i", &x, &y, &z);
		adj[x].pb(mp(y, z));
		adj[y].pb(mp(x, z));
	}
	if(Q == 1)solveLargest();
	else solveSmallest();
	return 0;
}