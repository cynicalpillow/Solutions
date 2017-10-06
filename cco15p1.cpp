#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, W, ti;
vector<int> a;
long long vals[100005];
long long pfwd[100010], pbck[100010];

int main(){
	freopen("input.txt", "r", stdin);
	memset(vals, -INFINITE, sizeof(vals));
	scanf("%i%i", &N, &W);
	for(int i = 0; i < N; i++){
		scanf("%i", &ti);
		a.pb(ti);
	}
	sort(a.begin(), a.end());
    cout << (max(a[N-1], W) - min(a[0], W)) << " ";
	//Max
	long long ma = 0;
	int mIdx = N-1;
	for(int i = 0; i < N && mIdx >= i; i++){
		if(i == 0){
			vals[i] = abs(W - a[i]);
			vals[mIdx] = abs(W - a[mIdx]);
			long long lower = vals[i];
			long long higher = vals[mIdx];
			//check lower
			vals[i] = max(abs(a[mIdx] - a[i]) + higher, abs(W-a[i]) + higher);
			//check higher
			vals[mIdx] = max(abs(a[mIdx] - a[i]) + lower, abs(W-a[mIdx]) + lower);
		} else {
			if(i == mIdx){
				vals[i] = max(vals[i], max(max(abs(a[i] - a[i-1]) + vals[i-1], abs(W-a[i]) + vals[i-1]), max(abs(a[mIdx+1] - a[i]) + vals[mIdx+1], abs(W-a[i]) + vals[mIdx+1])));
				vals[mIdx] = max(vals[mIdx], max(max(abs(a[mIdx] - a[i-1]) + vals[i-1], abs(W-a[mIdx]) + vals[i-1]), max(abs(a[mIdx+1] - a[mIdx]) + vals[mIdx+1], abs(W-a[mIdx]) + vals[mIdx+1])));
			} else {
				vals[i] = max(max(abs(a[i] - a[i-1]) + vals[i-1], abs(W-a[i]) + vals[i-1]), max(abs(a[mIdx+1] - a[i]) + vals[mIdx+1], abs(W-a[i]) + vals[mIdx+1]));
				vals[mIdx] = max(max(abs(a[mIdx] - a[i-1]) + vals[i-1], abs(W-a[mIdx]) + vals[i-1]), max(abs(a[mIdx+1] - a[mIdx]) + vals[mIdx+1], abs(W-a[mIdx]) + vals[mIdx+1]));

				long long lower = vals[i];
				long long higher = vals[mIdx];
				vals[i] = max(vals[i], max(abs(a[mIdx] - a[i]) + higher, abs(W-a[i]) + higher));
				vals[mIdx] = max(vals[mIdx], max(abs(a[mIdx] - a[i]) + lower, abs(W-a[mIdx]) + lower));
			}
		}
		ma = max(ma, max(vals[i], vals[mIdx]));
		mIdx--;
	}
	cout << ma << endl;
	return 0;
}