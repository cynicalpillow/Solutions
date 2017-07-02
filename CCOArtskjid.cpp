#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M;
int graph[20][20];
int maxi = -INFINITE;
int memo[20][1 << 20];

int dfs(int curr, int subset){
	if(curr == N-1)return 0;
	int xx = subset;
	xx &= ~(1 << (curr-1));
	if(memo[curr][xx] != -1)return memo[curr][xx];
	if(xx == 0 && graph[curr][N-1] > 0)return graph[curr][N-1];
	else if(xx == 0 && graph[curr][N-1] == 0)return -1;
	int* result = &memo[curr][xx];
	*result = -INFINITE;
	for(int i = 0; i < N-1; i++){
		if((((xx)&(1 << i)) != 0)){
			if(graph[curr][i+1] > 0 && xx != 0){
				int dist = dfs(i+1, xx);
				if(dist == -1)continue;
				*result = max(*result, dist+graph[curr][i+1]);
			}
		}
	}
	return *result;
}

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &M);
	for(int i = 0; i < M; i++){
		int x, y, l;
		scanf("%i%i%i", &x, &y, &l);
		graph[x][y] = l;
	}
	memset(memo, -1, sizeof(memo));
	for(int i = 0; i < (1 << (N-1)); i++){
		for(int j = 0; j < N-1; j++){
			if((((i)&(1 << j)) != 0)){
				if(graph[0][j+1] > 0){
					int dist = dfs(j+1, i);
					if(dist == -1)continue;
					maxi = max(graph[0][j+1] + dist, maxi);
				}
			}
		}
	}
	printf("%i", maxi);
	return 0;
}