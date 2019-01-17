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
int n, s, x;
double t;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n;
	double h = 0;
	for(int i = 0; i < n; i++){
		cin >> s >> x >> t;
		h += (t*(s*sin(x*(M_PI/180.000000))));
	}
	cout << round(sqrt(2*9.8*h));
	return 0;
}