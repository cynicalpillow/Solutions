#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M;
int vals[100005];
map<long, long> maap;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &M);
	for(int i= 0; i < N; i++){
		int x;
		scanf("%i", &x);
		vals[i] = x;
	}
	maap[0]++;
	long long zz = 0;
	for(int i = 0; i < N; i++){
		zz = (vals[i]+zz)%M;
		maap[zz]++;
	}
	long long total = 0;
	for(auto const& x : maap)
		total += x.second*(x.second-1)/2;
	printf("%lld", total);
	return 0;
}