#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, R, a, b, r, X, Y;
vector<int> adj[200005];
bool marked[200005];
queue<pair<int, int>> q;
bool vi[200005];
int previ[200005];
pair<int, int> path;
int result = INFINITE;

void bfs(){
	q.push(mp(X, 0));
	vi[X] = true;
	while(!q.empty()){
		pair<int, int> s = q.front(); q.pop();
		bool found = false;
		for(int j : adj[s.first]){
			if(j == Y){
				path = mp(j, s.second+1);
				previ[j] = s.first;
				found = true;
				break;
			}
			if(!vi[j]){
				vi[j] = true;
				previ[j] = s.first;
				q.push(mp(j, s.second+1));
			}
		}
		if(found)break;
	}
	while(!q.empty())
		q.pop();
}

int bfs2(int start){
	int smallest = INFINITE;
	if(marked[start])return 0;
	memset(vi, false, sizeof(vi));
	q.push(mp(start, 0));
	vi[start] = true;
	while(!q.empty()){
		pair<int, int> curr = q.front(); q.pop();
		if(curr.second+1 >= result)break;
		bool found = false;
		for(int j : adj[curr.first]){
			if(marked[j]){
				smallest = curr.second+1;
				found = true;
				break;
			}
			if(!vi[j]){
				vi[j] = true;
				q.push(mp(j, curr.second+1));
			}
		}
		if(found)break;
	}
	while(!q.empty())
		q.pop();
	return smallest;
}

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	memset(previ, -1, sizeof(previ));
	scanf("%i%i", &N, &R);
	for(int i = 0; i < N-1; i++){
		scanf("%i%i", &a, &b);
		adj[a].pb(b);
		adj[b].pb(a);
	}
	for(int i = 0; i < R; i++){
		scanf("%i", &r);
		marked[r] = true;
	}
	scanf("%i%i", &X, &Y);
	bfs();
	int curr = path.first;
	result = min(result, bfs2(curr));
	while(previ[curr] > 0){
		curr = previ[curr];
		result = min(result, bfs2(curr));
	}
	cout << result;
	return 0;
}