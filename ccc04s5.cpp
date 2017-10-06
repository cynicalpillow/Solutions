#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int M, N;
int a[105][105];
int dp[105][105][3];
//third dimension of dp: 0 = left, 1 = down, 2 = up

int d(){
	for(int i = 0; i < N; i++){
		if(i == 0){
			for(int j = M-1; j >= 0; j--){
				if(a[j][i] == -1)continue;
				if(j == M-1)dp[j][i][1]=a[j][i]; //set it to be default
				else dp[j][i][1] = dp[j+1][i][1] + a[j][i]; // from below
			}
		} else {
			for(int j = M-1; j >= 0; j--){
				if(a[j][i] == -1)continue;
				dp[j][i][0] = max(dp[j][i-1][0], max(dp[j][i-1][1], dp[j][i-1][2]))+a[j][i]; //from left
			}
			for(int j = M-1; j >= 0; j--){
				if(a[j][i] == -1)continue;
				if(j != M-1)dp[j][i][1] = max(dp[j+1][i][1], dp[j+1][i][0])+a[j][i]; //from below
			}
			for(int j = 1; j < M; j++){
				if(a[j][i] == -1)continue;
				dp[j][i][2] = max(dp[j-1][i][0], dp[j-1][i][2])+a[j][i];
			}
		}
	}
	return max(dp[M-1][N-1][0], max(dp[M-1][N-1][1], dp[M-1][N-1][2]));
}

int main(){
	freopen("input.txt", "r", stdin);
	while(true){
		scanf("%i%i", &M, &N);

		for(int i = 0; i < M; i++)
			for(int j = 0; j < N; j++)
				dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = -INFINITE;

		if(M == 0 || N == 0)break;
		for(int i = 0; i < M; i++){
			string s;
			cin >> s;
			for(int j = 0; j < N; j++){
				if(s[j] == '.')a[i][j] = 0;
				else if(s[j] == '*')a[i][j] = -1;
				else a[i][j] = s[j]-'0';
			}
		}
		cout << d() << "\n";
	}
	return 0;
}