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
int n;
string st;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n;
	int c = 2;
	for(int i = 0; i < n; i++){
		c++;
		cin >> st;
		if(st.length() >= 4 && st.substr(st.length()-4, 4) == "+one")c++;
	}
	if(c == 13)c++;
	cout << c*100;
	return 0;
}