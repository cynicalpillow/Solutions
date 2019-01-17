#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M, T;
vector<pair<int, pair<int, int>>> frs;
int grid[25][25];
int dists[10][10];
int md[25][25];
queue<pair<int, pair<int, int>>> q;
bool visited[25][25];
bool vv[10];
int res = INFINITE;

void dfs(int curr, int d){
	vv[curr] = true;
	bool allVisited = true;
	for(int i = 1; i <= T+1; i++){
		if(!vv[i] && dists[curr][i] != INFINITE){
			dfs(i, d+dists[curr][i]);
			allVisited = false;
		}
	}
	if(allVisited)res = min(res, d);
	vv[curr] = false;
}

void bfs(pair<int, pair<int, int>> st){
	q.push(mp(0, st.second));
	md[st.second.first][st.second.second] = 0;
	visited[st.second.first][st.second.second] = true;
	while(!q.empty()){
		pair<int, pair<int, int>> crr = q.front(); q.pop();
		if(grid[crr.second.first][crr.second.second]){
			dists[st.first][grid[crr.second.first][crr.second.second]] = min(dists[st.first][grid[crr.second.first][crr.second.second]], crr.first);
		}
		if(crr.second.first+1 < N && (!visited[crr.second.first+1][crr.second.second] || md[crr.second.first+1][crr.second.second] > crr.first+1) && grid[crr.second.first+1][crr.second.second] != -1){
			visited[crr.second.first+1][crr.second.second] = true;
			q.push(mp(crr.first + 1, mp(crr.second.first+1, crr.second.second)));
			md[crr.second.first+1][crr.second.second] = crr.first+1;
		}
		if(crr.second.first-1 >= 0 && (!visited[crr.second.first-1][crr.second.second] || md[crr.second.first-1][crr.second.second] > crr.first+1) && grid[crr.second.first-1][crr.second.second] != -1){
			visited[crr.second.first-1][crr.second.second] = true;
			q.push(mp(crr.first + 1, mp(crr.second.first-1, crr.second.second)));
			md[crr.second.first-1][crr.second.second] = crr.first+1;
		}
		if(crr.second.second+1 < M && (!visited[crr.second.first][crr.second.second+1] || md[crr.second.first][crr.second.second+1] > crr.first+1) && grid[crr.second.first][crr.second.second+1] != -1){
			visited[crr.second.first][crr.second.second+1] = true;
			q.push(mp(crr.first + 1, mp(crr.second.first, crr.second.second+1)));
			md[crr.second.first][crr.second.second+1] = crr.first+1;
		}
		if(crr.second.second-1 >= 0 && (!visited[crr.second.first][crr.second.second-1] || md[crr.second.first][crr.second.second-1] > crr.first+1) && grid[crr.second.first][crr.second.second-1] != -1){
			visited[crr.second.first][crr.second.second-1] = true;
			q.push(mp(crr.first + 1, mp(crr.second.first, crr.second.second-1)));
			md[crr.second.first][crr.second.second-1] = crr.first+1;
		}
	}
}

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i%i", &N, &M, &T);
	for(int i = 0; i <= T+1; i++){
		for(int j = 0; j <= T+1; j++){
			dists[i][j] = INFINITE;
			md[i][j] = INFINITE;
		}
	}
	int c = 2;
	for(int i = 0; i < N; i++){
		string s;
		cin >> s;
		for(int j = 0; j < M; j++){
			if(s[j] == 'X'){
				grid[i][j] = -1;
			} else if(s[j] == 'H'){
				grid[i][j] = c;
				frs.pb(mp(c, mp(i, j)));
				c++;
			} else if(s[j] == 'G'){
				grid[i][j] = 1;
				frs.pb(mp(1, mp(i, j)));
			}
		}
	}
	for(pair<int, pair<int, int>> fr : frs){
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				visited[i][j] = false;
		bfs(fr);
	}
	dfs(1, 0);
	cout << res << endl;
	return 0;
}