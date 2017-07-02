#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M;
map<string, int> m;
vector<pair<long long, int>> adj[105];
long long steps[105];
bool visited[105];

void dijkstra(){
	queue<pair<int, int>> q;
	memset(steps, INFINITE, sizeof(steps));
	q.push(mp(0, 0));
	steps[0] = 0;
	visited[0] = true;
	while(!q.empty()){
		pair<int, int> x = q.front();
		q.pop();
		for(pair<long long, int> e : adj[x.second]){
			if(!visited[e.second]){
				steps[e.second] = x.first+1;
				visited[e.second]= true;
				q.push(mp(steps[e.second], e.second));
			}
		}
	}
	bool possible = true;
	long long total = 0;
	for(int i = 1; i <= N; i++){
		if(!visited[i]){
			possible = false;
			break;
		}
		int c = INFINITE;
		for(pair<int, int> e : adj[i]){
			if(steps[e.second]+1 == steps[i])c = min(c, e.first);
		}
		if(c < INFINITE)total += c;
	}
	if(possible)cout << total;
	else cout << "Impossible";
}

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &M);
	m["English"] = 0;
	for(int i = 1; i <= N; i++){
		string x;
		cin >> x;
		m[x] = i;
	}
	for(int i = 0; i < M; i++){
		string x, y;
		cin >> x >> y;
		long long w;
		scanf("%lld", &w);
		adj[m[x]].pb(mp(w, m[y]));
		adj[m[y]].pb(mp(w, m[x]));
	}
	dijkstra();
	return 0;
}