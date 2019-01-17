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
double K, P, X;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> K >> P >> X;
	printf("%.3f", min((((K*P)/(ceil(sqrt((K*P)/X))))+(X*(ceil(sqrt((K*P)/X))))), (((K*P)/(floor(sqrt((K*P)/X))))+(X*(floor(sqrt((K*P)/X)))))));
	return 0;
}