#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N;
vector<pair<int, int>> adj[50005];
bool good[50005];
bool visited[50005];

void dfs(int curr, int prevColor){
	visited[curr] = true;
	int colorCheck[50005];
	for(pair<int, int> p : adj[curr]){
		if(!visited[p.first]){
			if(p.second == prevColor)
				good[p.first] = false;
		}
	}
	for(pair<int, int> p : adj[curr]){
		if(!visited[p.first]){
			if(colorCheck[p.second] > 1)good[p.first] = false;
			if(!good[curr])good[p.first] = false;
		}
	}
	for(pair<int, int> p : adj[curr]){
		if(!visited[p.first])
			dfs(p.first, p.second);
	}
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N-1; i++){
		int ai, bi ,ci;
		cin >> ai >> bi >> ci;
		adj[ai].pb(mp(bi, ci));
		adj[bi].pb(mp(ai, ci));
	}
	memset(good, true, sizeof(good));
	memset(visited, false, sizeof(visited));
	dfs(1, -1);
	int count = 0;
	for(int i = 1; i <= N; i++)if(good[i])count++;
	cout << count << "\n";
	for(int i = 1; i <= N; i++){
		if(good[i])cout << i << "\n";
	}
	return 0;
}