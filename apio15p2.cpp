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
int N, M, Bi, Pi;
set<int> buildings[30005];
priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q;
int dists[30005]; //Distance to each building
int start, en;

//We move to the next doge, and switch

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> M;
	for(int i = 0; i < M; i++){
		cin >> Bi >> Pi;
		buildings[Bi].insert(Pi);
		if(i == 1)en = Bi;
		else if(i == 0)start = Bi;
	}
	memset(dists, INFINITE, sizeof(dists));
	dists[start] = 0;
	q.push(mp(0, start));
	while(!q.empty()){
		pair<int, int> p = q.top(); q.pop();
		if(p.first > dists[p.second])continue;
		for(int ps : buildings[p.second]){
			for(int j = p.second+ps; j < N; j+=ps){
				if(dists[j] > abs(j-p.second)/ps + p.first){
					dists[j] = abs(j-p.second)/ps + p.first;
					q.push(mp(dists[j], j));
				}
				if(buildings[j].count(ps))break;
			}
			for(int j = p.second-ps; j >= 0; j-=ps){
				if(dists[j] > abs(j-p.second)/ps + p.first){
					dists[j] = abs(j-p.second)/ps + p.first;
					q.push(mp(dists[j], j));
				}
				if(buildings[j].count(ps))break;
			}
		}
	}
	if(dists[en] == INFINITE)cout << -1;
	else cout << dists[en];
	return 0;
}