#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int MOD = 1000000007;
int N, M;
vector<int> graph[20002];
bool ent[20002];
bool dest[20002];
int dp[20002];
queue<int> q;
int mins[20002];

int findPaths(int curr){
	if(!dest[curr])return 1;
	if(dp[curr] != -1)return dp[curr];
	int* result = &dp[curr];
	*result = 0;
	for(auto x : graph[curr])*result = (*result%MOD + findPaths(x)%MOD)%MOD;
	return *result%MOD;
}
void bfs(int start){
	q.push(start);
	mins[start] = 1;
	while(!q.empty()){
		int curr = q.front(); q.pop();
		for(auto x : graph[curr]){
			if(mins[x] > mins[curr]+1){
				mins[x] = mins[curr]+1;
				q.push(x);
			}
		}
	}
}
int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &M);
	memset(dp, -1, sizeof(dp));
	memset(mins, INFINITE, sizeof(mins));
	for(int i = 0; i < M; i++){
		int x, y;
		scanf("%i%i", &x, &y);
		graph[x].pb(y);
		ent[y] = true;
		dest[x] = true;
	}
	int paths = 0;
	for(int i = 0; i < N; i++)if(!ent[i])paths += findPaths(i);
	printf("%i\n", (paths%MOD));
	for(int i = 0; i < N; i++)if(!ent[i])bfs(i);
	for(int i = 0; i < N; i++)if(!dest[i])printf("%i ", mins[i]);
	return 0;
}