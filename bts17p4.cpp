#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M, J, pi, ti;
int val[100005];
int dists[100005];
priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q;

int dijkstra(int x, int time){
	memset(dists, INFINITE, sizeof(dists));
	q.push(mp(time, x));
	while(!q.empty()){
		pair<int, int> curr = q.top(); q.pop();
		if(curr.second == 0)return curr.first;
		for(int i = max(0, curr.second-J); i < curr.second; i++){
			if(val[i] != -1 && dists[i] > max(val[i], curr.first)){
				dists[i] = max(val[i], curr.first);
				q.push(mp(max(val[i], curr.first), i));
			}
		}
	}
	return INFINITE;
}

int main(){
	freopen("input.txt", "r", stdin);
	memset(val, -1, sizeof(val));
	val[0] = 0;
	scanf("%i%i%i", &N, &M, &J);
	for(int i = 0; i < M; i++){
		scanf("%i%i", &pi, &ti);
		val[pi] = ti;
	}
	int m = dijkstra(N+1, 0);
	cout << (m == INFINITE ? -1 : m);
	return 0;
}