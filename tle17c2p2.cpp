#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, K, fi, uj;
bool unlucky[1000005];
int scan[1000005];
int qs[1000005];

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i", &K);
	for(int i = 0; i < K; i++){
		scanf("%i", &uj);
		unlucky[uj] = true;
	}
	scanf("%i", &N);
	int mm = 0;
	for(int i = 0; i < N; i++){
		scanf("%i", &fi);
		qs[i] = fi;
		mm = max(mm, fi);
	}
	scan[0] = 0;
	for(int i = 1; i <= mm; i++){
		if(unlucky[i])scan[i] = scan[i-1];
		else scan[i] = scan[i-1]+1;
	}
	for(int i = 0; i < N; i++)
		cout << scan[qs[i]] << "\n";
	return 0;
}