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
int flooring, r, c;
int grid[30][30];
bool visited[30][30];
vector<int> rooms;

int dfs(int r1, int c1){
	if(visited[r1][c1] || grid[r1][c1] == -1)return 0;
	visited[r1][c1] = true;
	int total = 1;
	if(r1+1 < r)total += dfs(r1+1, c1);
	if(c1+1 < c)total += dfs(r1, c1+1);
	if(r1-1 >= 0)total += dfs(r1-1, c1);
	if(c1-1 >= 0)total += dfs(r1, c1-1);
	return total;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> flooring >> r >> c;
	for(int i = 0; i < r; i++){
		string s;
		cin >> s;
		for(int j = 0; j < c; j++){
			if(s[j] == 'I')grid[i][j] = -1;
			else grid[i][j] = 0;
		}
	}
	for(int i = 0; i < r; i++){
		for(int j = 0; j < c; j++){
			if(!visited[i][j] && grid[i][j] != -1)rooms.pb(dfs(i, j));
		}
	}
	sort(rooms.begin(), rooms.end(), greater<int>());
	int count = 0;
	for(int i : rooms){
		if(flooring - i == 0){
			count++;
			flooring -= i;
			break;
		} else if(flooring - i < 0)break;
		flooring -= i;
		count++;
	}
	cout << count << (count == 1 ? " room, ":" rooms, ") << flooring << " square metre(s) left over";
	return 0;
}