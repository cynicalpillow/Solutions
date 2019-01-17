#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int R, C, S, B, K, M, T, V, c, r;
pair<int, int> snowflakes[55][55];
int dp[55][55][55][55];

int solve(int t, int col, int temp, int leftover){
	if(t == R+1 || leftover == 0)
		return 0;
	int &res = dp[t][col][temp][leftover];
	if(res != -1)
		return res;
	res = 0;
	for(int i = -M; i <= M; i++){
		if(i+col < 1 || i+col > C)
			continue;
		res = max(res, solve(t+1, i+col, temp, leftover)); //dont choose
		//choose
		if(snowflakes[t+1][i+col].first != -1 && temp > snowflakes[t+1][i+col].second){
			res = max(res, snowflakes[t+1][i+col].first + solve(t+1, i+col, temp-snowflakes[t+1][i+col].second, leftover-1));
		}
	}
	return res;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	memset(dp, -1, sizeof(dp));
	cin >> R >> C >> S >> B >> K >> M;
	for(int i = 0; i <= R; i++){
		for(int j = 0; j <= C; j++){
			snowflakes[i][j] = mp(-1, -1);
		}
	}
	for(int i = 0; i < S; i++){
		cin >> T >> V >> c >> r;
		snowflakes[r][c] = mp(V, T);
	}
	int total = solve(0, 1, B, K);
	cout << total;
	return 0;
}