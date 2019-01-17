#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

priority_queue<pair<double, pair<int, int>>, vector<pair<double, pair<int, int>>>, greater<pair<double, pair<int, int>>>> q;
int V, E, m, n;
double d, s;
vector<pair<double, int>> adj[1005];
double minTimes[1005][1005];
double minInt[1005][1005];

void dijkstra(){
	q.push(mp(0, mp(0, 1)));
	for(int i = 0; i <= V; i++){
		for(int j = 0; j <= V; j++){
			minTimes[i][j] = -1.0;
		}
	}
	while(!q.empty()){
		pair<double, pair<int, int>> curr = q.top(); q.pop();
		if(curr.second.second == V){
			cout << curr.second.first << endl;
			cout << round((curr.first/0.75)-curr.first) << endl;
			return;
		}
		for(pair<double, int> pp : adj[curr.second.second]){
			if(minTimes[curr.second.second][pp.second] > curr.first + pp.first || minTimes[curr.second.second][pp.second] == -1){
				minInt[curr.second.second][pp.second] = curr.second.first+1;
				minTimes[curr.second.second][pp.second] = curr.first + pp.first;
				q.push(mp(curr.first+pp.first, mp(curr.second.first+1, pp.second)));
			} else if(minTimes[curr.second.second][pp.second] == curr.first + pp.first && minInt[curr.second.second][pp.second] > curr.second.first+1){
				minInt[curr.second.second][pp.second] = curr.second.first+1;
				minTimes[curr.second.second][pp.second] = curr.first + pp.first;
				q.push(mp(curr.first+pp.first, mp(curr.second.first+1, pp.second)));
			}
		}
	}
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> V >> E;
	for(int i = 0; i < E; i++){
		cin >> m >> n >> d >> s;
		adj[m].pb(mp((d/s)*60.0, n));
		adj[n].pb(mp((d/s)*60.0, m));
	}
	dijkstra();
	return 0;
}