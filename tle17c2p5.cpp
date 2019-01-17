#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, P, Q;
string result;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i%i", &N, &P, &Q);
	if(N >= 2)cout << "C 1 3";
	else cout << "C -1";
	fflush(stdout);
	return 0;
}