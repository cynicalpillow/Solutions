#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, H;
long long gi[5005];
long long hi[5005];
long long qi[5005];
long long ti[5005];
long long dp[2][5005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> H;
	for(int i = 0; i < N; i++){
		long long g, h, q, t;
		cin >> g >> h >> q >> t;
		gi[i] = g;
		hi[i] = h;
		qi[i] = q;
		ti[i] = t;
	}
	long long mx = 0;
	for(int i = 0; i < N; i++){
		//check visiting
		for(int j = hi[i]; j <= H; j++)
			dp[1][j] = max(dp[1][j], dp[0][j-hi[i]]+gi[i]);
		//check doing quests
		for(int j = hi[i]+ti[i]; j <= H; j++)
			dp[1][j] = max(dp[1][j], dp[1][j-ti[i]]+qi[i]);
		//move previous up
		for(int j = 0; j <= H; j++){
			dp[0][j] = max(dp[1][j], dp[0][j]);
			dp[1][j] = 0;
			mx = max(dp[0][j], mx);
		}
	}
	cout << mx;
	return 0;
}