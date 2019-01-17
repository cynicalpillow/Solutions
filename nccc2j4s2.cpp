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
vector<int> sides;
int x, y, x2, y2, x3, y3;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> x >> y >> x2 >> y2 >> x3 >> y3;
	sides.pb(x); sides.pb(y); sides.pb(x2); sides.pb(y2); sides.pb(x3); sides.pb(y3);
	if(sides[0] == sides[2] && sides[1]+sides[3] == sides[4] && sides[5] + sides[0] == sides[4])cout<<"YES";
	swap(sides[0], sides[1]);
	if(sides[0] == sides[2] && sides[1]+sides[3] == sides[4] && sides[5] + sides[0] == sides[4])cout<<"YES";
	swap(sides[0], sides[1]);
	if(sides[0] == sides[4] && sides[1]+sides[5] == sides[3] && sides[4] + sides[0] == sides[2])cout<<"YES";
	swap(sides[0], sides[1]);
	if(sides[0] == sides[4] && sides[1]+sides[5] == sides[3] && sides[4] + sides[0] == sides[2])cout<<"YES";
	swap(sides[0], sides[1]);
	if(sides[2] == sides[4] && sides[3]+sides[5] == sides[1] && sides[4] + sides[0] == sides[2])cout<<"YES";
	return 0;
}