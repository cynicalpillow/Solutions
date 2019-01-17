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
int N, A, B, C;
long long xi;
long long vals[1000005];
long long dp[1000005];

long long compute(long long x){
	return (A*x*x + B*x + C);
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> A >> B >> C;
	fori(i, 1, N+1){
		cin >> xi;
		vals[i] = vals[i-1]+xi;
	}
	int best = 1;
	fori(i, 1, N+1){
		dp[i] = compute(vals[i]-vals[i-1])+dp[i-1];
		fori(j, best, i){
			long long x = compute(vals[i]-vals[j-1])+dp[j-1];
			if(x > dp[i]){
				dp[i] = x;
				best = j;
			}
		}
	}
	cout << dp[N];
	return 0;
}