#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int a, b, c, d, t;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i%i%i%i", &a, &b, &c, &d, &t);
	int dist = (abs(a-c)+abs(b-d));
	if((t % 2 == 0 && dist % 2 ==0) || (t % 2 != 0 && dist % 2 !=0))cout << "Y";
	else cout << "N";
	return 0;
}