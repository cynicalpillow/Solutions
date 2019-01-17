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
string x, y;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> x >> y;
	if(y[y.size()-1] == 's'){
		cout << x << "-tu les " << y << " ?";
	} else if(y[y.size()-1] == 'e'){
		cout << x << "-tu la " << y << " ?";
	} else {
		cout << x << "-tu le " << y << " ?";
	}
	return 0;
}