#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int P, Q, W;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> P >> Q >> W;
	double mark = (Q-0.5) - ((100-W)/100.0) * P;
	int finalM = round((mark / ((double) W))*100.0);
	if(finalM > 100)cout << "DROP THE COURSE";
	else cout << max(finalM, 0);
	return 0;
}