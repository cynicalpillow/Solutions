#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair

int INFINITE = 0x3f3f3f3f;
priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q;
vector<pii> adjlist[1001];
int dist[1001];
int N, M, u, v, w;

void dijkstra(){
	dist[1] = 0;
	q.push(mp(0, 1));
	while(!q.empty()){
		pii curr = q.top(); q.pop();
		if(dist[curr.second] < curr.first)continue;
		for(int i = 0; i < adjlist[curr.second].size(); i++){
			if(dist[curr.second] + adjlist[curr.second][i].second < dist[adjlist[curr.second][i].first]){
				dist[adjlist[curr.second][i].first] = dist[curr.second] + adjlist[curr.second][i].second;
				q.push(mp(dist[adjlist[curr.second][i].first], adjlist[curr.second][i].first));
			}
		}
	}
	for(int i = 1; i <= N; i++){
		if(dist[i] != INFINITE)printf("%i \n", dist[i]);
		else printf("%i \n", -1);
	}
}

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i %i", &N, &M);
	memset(dist, 0x3f3f3f3f, sizeof(dist));
	while(M--){
		scanf("%i %i %i", &u, &v, &w);
		adjlist[u].push_back(mp(v, w));
		adjlist[v].push_back(mp(u, w));
	}
	dijkstra();
	return 0;
}

