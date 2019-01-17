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
int N, K;
long long a[100005];
long long dp[100005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> K;
	for(int i = 1; i <= N; i++)
		cin >> a[i];
	long long ans = 0;
	for(int i = 1; i <= N; i++){
		dp[i] = dp[i-1];
		if(i-K-1 >= 0)dp[i] = max(dp[i], dp[i-K-1]+a[i]);
		else dp[i] = max(dp[i], a[i]);
		ans = max(dp[i], ans);
	}
	cout << ans;
	return 0;
}