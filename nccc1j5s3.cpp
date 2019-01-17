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
long long N;
//Utilities
long long lower_bound (long long x) {
	long long low = 1, high = N-1, mid = -1;
	while (low < high) {
		mid = (low + high) / 2;
		long long val = 2LL*(N*(N-1LL)/2LL-(N-1LL-mid)*(N-1LL-mid+1LL)/2LL);
		//N*(N-1LL)/2LL = total
		//(N-1LL-mid)*(N-1LL-mid+1LL)/2LL = Sum from 1 to mid
		if (val >= x)
			high = mid;
		else
			low = mid + 1;
	}
	return low;
}
int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	cout << lower_bound(N*(N-1LL)/2LL);
	//long long dd = ((N*(N-1)))-(((N*(N-1))/2.0));
	//cout << (long long)floor(N-round((-1LL+sqrt(1LL+4LL*dd)/2.0)))-1LL;
	return 0;
}