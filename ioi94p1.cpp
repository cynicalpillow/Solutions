#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N;
int arr[105][105];
int dp[105][105];

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N; i++)for(int j = 0; j < N; j++)arr[i][j] = dp[i][j] = -INFINITE;
	int start = 1;
	for(int j = 0; j < N; j++){
		int x;
		for(int i = 0; i < start; i++){
			scanf("%i", &x);
			arr[j][i] = x;
			dp[j][i] = x;
		}
		start++;
	}
	for(int i = 0; i < N; i++){
		for(int j = 0; j < N; j++){
			if(arr[i][j] == -INFINITE)continue; //Skip this
			if(i == 0)continue; //Skip
			if(j == 0)dp[i][j] = max(dp[i][j], dp[i-1][j]+arr[i][j]); //Check first
			else if(j == i)dp[i][j] = max(dp[i][j], dp[i-1][j-1]+arr[i][j]); //Check end
			else dp[i][j] = max(dp[i][j], max(dp[i-1][j]+arr[i][j], dp[i-1][j-1]+arr[i][j])); //Inbetween
		}
	}
	int m = 0;
	for(int j = 0; j < N; j++)m = max(dp[N-1][j], m);
	printf("%i", m);
	return 0;
}