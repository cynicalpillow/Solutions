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
int K, N;
int prm[105];

bool solve(){
	int maxSoFar = 1;
	int maxHere = 1;
	for(int i = 1; i < N; i++){
		if(prm[i - 1] < prm[i]){
			maxHere++;
		} else {
			maxSoFar = max(maxSoFar, maxHere);
			if(maxSoFar > K)return false;
			maxHere = 1;
		}
	}
	maxSoFar = max(maxSoFar, maxHere);
	return (maxSoFar == K);
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> K;
	for(int i = 0; i < N; i++){
		prm[i] = i+1;
	}
	int total = 0;
    do {
    	total += solve();
    } while(next_permutation(prm, prm+N));
    cout << total;
	return 0;
}