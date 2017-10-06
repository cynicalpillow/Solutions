#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M, a, b, x;
int types[200005];
vector<int> adj[200005];
bool visited[200005];
bool visited2[200005];
stack<int> sorted;
bool allSame = true;
int changes[200005];
	
void topsort(int a){
	visited[a] = true;
	for(int i = 0; i < adj[a].size(); i++)
		if(!visited[adj[a][i]])topsort(adj[a][i]);
	sorted.push(a);
}

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &M);
	memset(&visited, false, sizeof(visited));
	memset(&visited2, false, sizeof(visited2));
	memset(&changes, -INFINITE, sizeof(changes));
	int prev = -1;
	for(int i = 0; i < N; i++){
		scanf("%i", &x);
		types[i] = x;

		if(prev == -1)prev = x;
		if(prev != x)allSame = false;
	}
	for(int i = 0; i < M; i++){
		scanf("%i%i", &a, &b);
		adj[a].pb(b);
	}
	for(int i = 0; i < N; i++)
		if(!visited[i])topsort(i);
	queue<pair<int, int>> q;
	for(int i = 0; i < N; i++){
		if(!visited2[sorted.top()]){
			q.push(mp(sorted.top(), 0));
			changes[sorted.top()] = 0;
			visited2[sorted.top()] = true;
			while(!q.empty()){
				pair<int, int> curr = q.front();
				q.pop();
				for(int j = 0; j < adj[curr.first].size(); j++){
					if(types[adj[curr.first][j]] != types[curr.first]){
						if(changes[adj[curr.first][j]] < curr.second + 1){
							changes[adj[curr.first][j]] = curr.second + 1;
							q.push(mp(adj[curr.first][j], curr.second+1));
							visited2[adj[curr.first][j]] = true;
						}
					} else {
						if(changes[adj[curr.first][j]] < curr.second){
							changes[adj[curr.first][j]] = curr.second;
							q.push(mp(adj[curr.first][j], curr.second));
							visited2[adj[curr.first][j]] = true;
						}
					}
				}
			}
		}
		sorted.pop();
	}
	int maxmin = -INFINITE;
	for(int i = 0; i < N; i++)
		maxmin = max(changes[i], maxmin);
	if(maxmin == 0 && !allSame)maxmin++;
	cout << maxmin;
	return 0;
}
