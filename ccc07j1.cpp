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
vector<int> bowls;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	int a, b, c;
	cin >> a >> b >> c;
	bowls.pb(a);
	bowls.pb(b);
	bowls.pb(c);
	sort(bowls.begin(), bowls.end());
	cout << bowls[1];
	return 0;
}