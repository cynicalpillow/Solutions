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
int N, T, a, b, c, S;
long long diff_array[1000005];
long long sums[1000005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> T;
	memset(diff_array, 0, sizeof(0));
	for(int i = 0; i < T; i++){
		cin >> a >> b >> c;
		diff_array[a]+=c;
		diff_array[b+1]-=c;
	}
	cin >> S;
	sums[0] = 0;
	for(int i = 1; i <= N; i++){
		sums[i] = sums[i-1]+diff_array[i];
	}
	for(int i = 1; i <= N; i++){
		sums[i] = sums[i-1]+sums[i];
	}
	int L, R;
	L = R = 1;
	int maxChocolates = 0;
	while(L < N || R < N){
		if(sums[R] - sums[L-1] <= S)maxChocolates = max(maxChocolates, R-L+1);
		if((R == N || sums[R] - sums[L-1] > S) && L+1 <= R)L++;
		else R++;
	}
	cout << maxChocolates;
	return 0;
}