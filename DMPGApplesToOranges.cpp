#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M;
map<string, int> mpp;
vector<pair<int, double>> adj[505];
double mem[505];

bool bfs(int curr){
	queue<pair<int, double>> q;
	q.push(mp(curr, 1.0));
	mem[curr] = 1.0;
	while(!q.empty()){
		pair<int, double> x = q.front();
		q.pop();
		if(x.first == mpp["APPLES"] && x.second > 1.0 && ceil(x.second) == x.second)return true;
		for(pair<int, double> p : adj[x.first]){
			pair<int, double> y = mp(p.first, p.second*x.second);
			if(y.second > mem[p.first]){
				mem[p.first] = y.second;
				q.push(y);
			}
		}
	}
	return false;
}

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &M);
	for(int i = 0; i < N; i++){
		string x;
		cin >> x;
		mpp[x] = i;
	}
	for(int i = 0; i < M; i++){
		string x, y;
		double amt;
		cin >> x;
		cin >> y;
		scanf("%lf", &amt);
		adj[mpp[x]].pb(mp(mpp[y], amt));
	}
	bool ans = bfs(mpp["APPLES"]);
	if(ans)cout<<"YA";
	else cout<<"NAW";
	return 0;
}