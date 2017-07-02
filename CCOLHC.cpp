#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

long long INFINITE = 0x3f3f3f3f3f3fLL;
long long diameter = 0;
long long counter = 0;
int N;
vector<int> graph[400002];
bool visited[400002];
long long level[400002];
long long nlevel[400002];
void dfs(int curr){
	visited[curr] = true;
	level[curr] = 0;
	nlevel[curr] = 1;
	for(auto x : graph[curr]){
		if(visited[x])continue;
		dfs(x);
		if(level[curr] + level[x] + 1 > diameter){
			diameter = level[curr]+level[x]+1;
			counter = nlevel[curr]*nlevel[x];
		} else if(level[curr] + level[x] + 1 == diameter){
			counter += nlevel[curr]*nlevel[x];
		}
		if(level[curr] < level[x]+1){
			level[curr] = level[x]+1;
			nlevel[curr] = nlevel[x];
		} else if (level[curr] == level[x]+1){
			nlevel[curr] += nlevel[x];
		}
	}
}
int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N-1; i++){
		int x, y;
		scanf("%i %i", &x, &y);
		graph[x].pb(y);
		graph[y].pb(x);
	}
	dfs(1);
	cout << diameter+1 << " " << counter;
	return 0;
}