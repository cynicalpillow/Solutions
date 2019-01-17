#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M, Hi;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &M);
	int c = 0;
	for(int i = 0; i < M; i++){
		scanf("%i", &Hi);
		if(Hi >= N)c++;
	}
	cout << c;
	return 0;
}