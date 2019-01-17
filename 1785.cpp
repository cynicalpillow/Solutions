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
int N;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	if(N <= 4)cout << "few";
	else if(N <= 9)cout << "several";
	else if(N <= 19)cout << "pack";
	else if(N <= 49)cout << "lots";
	else if(N <= 99)cout << "horde";
	else if(N <= 249)cout << "throng";
	else if(N <= 499)cout << "swarm";
	else if(N <= 999)cout << "zounds";
	else cout << "legion";
	return 0;
}