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
int N;
int grid[205][205];
bool visited[205][205];
vector<pair<int, int>> cs;
int maxCount = 0;

void dfs(int r, int c, int currC, int runningCount){
	visited[r][c] = true;

	if(maxCount == cs.size())return;

	//Last C visited
	if(currC == cs.size()-1 && grid[r][c] == 4){
		visited[r][c] = false;
		maxCount = max(maxCount, runningCount+1);
		return;
	}
	//If found, go to next C
	if(grid[r][c] == 4)dfs(cs[currC+1].first, cs[currC+1].second, currC+1, runningCount+1);

	bool poss = false;
	//Horizontal & vertical
	if(r+1 < N && !visited[r+1][c] && grid[r+1][c] == grid[r][c]+1){
		dfs(r+1, c, currC, runningCount);
		poss = true;
	}
	if(c+1 < N && !visited[r][c+1] && grid[r][c+1] == grid[r][c]+1){
		dfs(r, c+1, currC, runningCount);
		poss = true;
	}
	if(r-1 >= 0 && !visited[r-1][c] && grid[r-1][c] == grid[r][c]+1){
		dfs(r-1, c, currC, runningCount);
		poss = true;
	}
	if(c-1 >= 0 && !visited[r][c-1] && grid[r][c-1] == grid[r][c]+1){
		dfs(r, c-1, currC, runningCount);
		poss = true;
	}
	//Diagonals
	if(r+1 < N && c+1 < N && !visited[r+1][c+1] && grid[r+1][c+1] == grid[r][c]+1){
		dfs(r+1, c+1, currC, runningCount);
		poss = true;
	}
	if(c+1 < N && r-1 >= 0 && !visited[r-1][c+1] && grid[r-1][c+1] == grid[r][c]+1){
		dfs(r-1, c+1, currC, runningCount);
		poss = true;
	}
	if(r-1 >= 0 && c-1 >= 0 && !visited[r-1][c-1] && grid[r-1][c-1] == grid[r][c]+1){
		dfs(r-1, c-1, currC, runningCount);
		poss = true;
	}
	if(c-1 >= 0 && r+1 < N && !visited[r+1][c-1] && grid[r+1][c-1] == grid[r][c]+1){
		dfs(r+1, c-1, currC, runningCount);
		poss = true;
	}
	maxCount = max(maxCount, runningCount);
	if(currC == cs.size()-1){
		visited[r][c] = false;
		return;
	}
	if(!poss)dfs(cs[currC+1].first, cs[currC+1].second, currC+1, runningCount);
	//reset
	visited[r][c] = false;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		string s;
		cin >> s;
		for(int j = 0; j < N; j++){
			if(s[j] == 'C')grid[i][j] = 1;
			else if(s[j] == 'A')grid[i][j] = 2;
			else if(s[j] == 'L')grid[i][j] = 3;
			else grid[i][j] = 4;
			if(s[j] == 'C')cs.pb(mp(i, j));
		}
	}
	if(cs.size() < 1)cout << 0;
	else {
		dfs(cs[0].first, cs[0].second, 0, 0);
		cout << maxCount;
	}
	return 0;
}