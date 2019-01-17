#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int C, N;
int foods[1005];
bool dp[1000001];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> C >> N;
	for(int i = 0; i < N; i++){
		int x;
		cin >> x;
		foods[i] = x;
	}
	dp[0] = true;
	int mmx = 0;
	for(int i = 0; i < N; i++)
		for(int j = C-1; j >= 0; j--)
			if(j-foods[i] >= 0 && dp[j-foods[i]])
				dp[j] = true;
	for(int i = C-1; i >= 0; i--){
		if(dp[i])
			mmx = max(mmx, i);
	}
	cout << mmx;
	return 0;
}