#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int x1, yy1, x2, y2, L, K;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i%i%i%i%i", &x1, &yy1 , &K, &x2, &y2, &L);
	cout << ((abs(y2-yy1)/max(abs(K-L), abs(1-L))+(abs(y2-yy1)%max(abs(K-L), abs(1-L)) != 0))+(abs(x2-x1)/max(abs(K-L), abs(1-L))+(abs(x2-x1)%max(abs(K-L), abs(1-L))!= 0)));
	return 0;
}