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
int matrix[11][11];
bool visited[11][11];
vector<pair<int, int>> fires;
queue<pair<int, pair<int, int>>> q;

void bfs(){
	for(pair<int, int> pp : fires){
		q.push(mp(0, pp));
		visited[pp.first][pp.second] = true;
	}
	int mx = 0;
	while(!q.empty()){
		pair<int, pair<int, int>> pp = q.front(); q.pop();
		mx = max(mx, pp.first);
		if(pp.second.first + 1 < 10 && !visited[pp.second.first+1][pp.second.second] && matrix[pp.second.first+1][pp.second.second] == 1){
			q.push(mp(pp.first+1, mp(pp.second.first+1, pp.second.second)));
			visited[pp.second.first+1][pp.second.second] = true;
		}
		if(pp.second.first - 1 >= 0 && !visited[pp.second.first-1][pp.second.second] && matrix[pp.second.first-1][pp.second.second] == 1){
			q.push(mp(pp.first+1, mp(pp.second.first-1, pp.second.second)));
			visited[pp.second.first-1][pp.second.second] = true;
		}
		if(pp.second.second + 1 < 10 && !visited[pp.second.first][pp.second.second+1] && matrix[pp.second.first][pp.second.second+1] == 1){
			q.push(mp(pp.first+1, mp(pp.second.first, pp.second.second+1)));
			visited[pp.second.first][pp.second.second+1] = true;
		}
		if(pp.second.second - 1 >= 0 && !visited[pp.second.first][pp.second.second-1] && matrix[pp.second.first][pp.second.second-1] == 1){
			q.push(mp(pp.first+1, mp(pp.second.first, pp.second.second-1)));
			visited[pp.second.first][pp.second.second-1] = true;
		}
	}
	for(int i = 0; i < 10; i++){
		for(int j = 0; j < 10; j++){
			if(matrix[i][j] == 1 && !visited[i][j]){
				cout << -1 << endl;
				return;
			}
		}
	}
	cout << mx << endl;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	for(int i = 0; i < 5; i++){
		for(int j = 0; j < 10; j++){
			string z;
			cin >> z;
			for(int k = 0; k < 10; k++){
				if(z[k] == 'T')
					matrix[j][k] = 1;
				else if(z[k] == 'F'){
					matrix[j][k] = 1;
					fires.pb(mp(j, k));
				} else {
					matrix[j][k] = 0;
				}
				visited[j][k] = false;
			}
		}
		string placeholder;
		cin >> placeholder;
		bfs();
		fires.clear();
	}
	return 0;
}