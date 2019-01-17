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
int a1, b1, a2, b2, a3, b3;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> a1 >> b1 >> a2 >> b2 >> a3 >> b3;
	int startb = (a2-a1);
	int starta = (b3-startb-b2);
	cout << starta << " " << startb;
	return 0;
}