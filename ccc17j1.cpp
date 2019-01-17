#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int x, y;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &x, &y);
	if(x > 0 && y > 0) cout << "1";
	else if(x < 0 && y > 0)cout << "2";
	else if(x < 0 && y < 0)cout << "3";
	else cout << "4";
	return 0;
}