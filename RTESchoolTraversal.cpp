#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int dist[6005][6005];
int N, Q;
priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q;
vector<pii> adjlist[6005];
bool nS[6005];
vector<int> first;
vector<int> second;

void dijkstra(int start){
	q = priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>>();
	dist[start][start] = 0;
	q.push(mp(0, start));
	while(!q.empty()){
		pii curr = q.top(); q.pop();
		if(dist[start][curr.second] < curr.first)continue;
		for(int i = 0; i < adjlist[curr.second].size(); i++){
			if(dist[start][curr.second] + adjlist[curr.second][i].second < dist[start][adjlist[curr.second][i].first]){
				dist[start][adjlist[curr.second][i].first] = dist[start][curr.second] + adjlist[curr.second][i].second;
				q.push(mp(dist[start][adjlist[curr.second][i].first], adjlist[curr.second][i].first));
			}
		}
	}
}
int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N; i++)for(int j = 0; j < N; j++)dist[i][j] = INFINITE;
	for(int i = 0; i < N-1; i++){
		int x, y, d;
		scanf("%i%i%i", &x, &y, &d);
		adjlist[x].pb(mp(y, d));
		adjlist[y].pb(mp(x, d));
	}
	scanf("%i", &Q);
	ios_base::sync_with_stdio(false);
	for(int i = 0; i < Q; i++){
		int x, y;
		scanf("%i%i", &x, &y);
		nS[x] = true;
		first.pb(x);
		second.pb(y);
	}
	for(int i = 0; i < N; i++)if(nS[i])dijkstra(i);
	for(int i = 0; i < first.size(); i++){
		cout << dist[first[i]][second[i]] << "\n";
	}
	return 0;
}