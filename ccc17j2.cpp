#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, K;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &K);
	long long total = N;
	for(int i = 1; i <= K; i++)
		total += N*(pow(10, i));
	cout << total;
	return 0;
}