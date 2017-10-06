#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, nI;
string z;
int cc[1005];
vector<int> adj[1005];
queue<pair<int, pair<int, int>> q;
int min = INFINITE;
bool visited[1005];

void bfsMin(){
	q.pb(mp(0, mp(0, 0));
	while(!q.empty()){
		pair<int, int> curr = q.top(); q.pop();
		if(curr.first == 0){

		} else {
			for(int i = 0; i < adj[curr.first].size(); i++){
				int error = (!adj[curr.first][i] && !curr.second.second) || (adj[curr.first][i] && curr.second.second) ? 0 : 1;
				q.pb(adj[curr.first][i], error);
			}
		}
	}
	cout << total;
}

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		cin >> z;
		if(z == "C"){
			cin >> z;
			if(z == "AC")cc[i+1] = 0;
			else if(z == "WA")cc[i+1] = -1;
			else cc[i+1] = 1;
		} else {
			scanf("%i", &nI);
			cin >> z;
			if(z == "AC")cc[i+1] = 0;
			else if(z == "WA")cc[i+1] = 1;
			else cc[i+1] = -1;
			adj[nI].pb(i+1);
		}
	}
	bfsMin();
	cout << " ";
	return 0;
}