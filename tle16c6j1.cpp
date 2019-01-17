#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M, A;
int times[10005];

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i%i", &N, &M, &A);
	memset(times, INFINITE, sizeof(times));
	for(int i = 0; i < A; i++){
		int x, y;
		scanf("%i%i", &x, &y);
		times[x] = y;
	}
	int secs = 0;
	int currPaper = N;
	while(true){
		if(times[secs] != INFINITE)currPaper += times[secs];
		if(currPaper > M){
			cout << "The printer jams at " << secs << " second(s).";
			return 0;
		}
		currPaper--;
		secs++;
		if(currPaper < 0){
			cout << "The printer melts at " << secs << " second(s).";
			return 0;
		}
	}
	return 0;
}