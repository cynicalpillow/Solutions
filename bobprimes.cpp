#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M;
int vals[101];
bool primes[10005];
bool results[10005][10005];
int costs[10005][10005];
vector<int> pnums;

void seive(){
	for(int i = 2; i < 10005; i++)primes[i] = true;
	for(int i = 2; i < 10005; i++){
		if(primes[i]){
			pnums.pb(i);
			for(int j = 2; j * i < 10005; j++)primes[j*i] = false;
		}
	}
}

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &M);
	for(int i = 0; i < M; i++){
		int x;
		scanf("%i", &x);
		vals[i] = x;
	}
	seive();
	results[0][0] = true;
	for(int i = 0; i < M; i++){
		for(int j = 1; j < N+1; j++){
			int x = 0;
			while(x < pnums.size() && vals[i] * pnums[x] < N+1 && !results[i][j]){
				if(i == 0){
					if(j-(vals[i] * pnums[x]) == 0)results[i][j] = true;
				} else {
					if(j-(vals[i] * pnums[x]) > 0 && results[i-1][j-(vals[i] * pnums[x])])results[i][j] = true;
				}
				x++;
			}
		}
	}
	bool check = false;
	for(int i = 0; i < N+1; i++){
		if(primes[i] && results[M-1][i])check = true;
	}
	if(check)printf("%s", "its primetime");
	else printf("%s", "not primetime");
	return 0;
}