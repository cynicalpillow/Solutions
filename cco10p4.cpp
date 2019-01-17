#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int T, N, B;
int ci, vi, ti;
vector<pair<int, int>> parts[6];
int dp[6][3005];

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &T, &N);
	for(int i = 0; i < N; i++){
		scanf("%i%i%i", &ci, &vi, &ti);
		parts[ti].pb(mp(ci, vi));
	}
	scanf("%i", &B);
	for(int i = 1; i <= T; i++){
		for(int j = 0; j <= B; j++){
			for(pair<int, int> z : parts[i]){
				if(j-z.first >= 0){
					dp[i][j] = max(dp[i][j], dp[i-1][j-z.first]+z.second);
				}
			}
		}
	}
	int mmx = 0;
	for(int i = 0; i <= B; i++){
		mmx = max(mmx, dp[T][i]);
	}
	cout << mmx;
	return 0;
}