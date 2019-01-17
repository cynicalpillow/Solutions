#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int x11, y11, x2, y2, x3, y3, D;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i%i%i%i%i%i", &x11, &y11, &x2, &y2, &x3, &y3, &D);
	bool x = (min(pow(x11-x3, 2) + pow(y11-y3, 2), pow(x2-x3, 2) + pow(y2-y3, 2)) <= pow(D, 2));
	if(x)cout << "Yes" << endl;
	else cout << "No" << endl;
	return 0;
}