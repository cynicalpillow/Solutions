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
long long cs[1000005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> K;
	for(int i = 0; i < K; i++){
		cin >> cs[i];
	}
	long long count = 0;
	for(int i = 0; i < K-2; i++){
		long long going_up = (N-cs[i+2]+1);
		if(i == 0) count += going_up*(cs[i]);
		else count += (N-cs[i+2]+1)*(cs[i]-cs[i-1]);
	}
	cout << count;
	return 0;
}