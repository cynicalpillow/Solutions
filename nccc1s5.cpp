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
string x;

bool is_valid(){
	int N = x.length();
	for(int i = 0; i < N; i++){
		if(x[i] != x[N-i-1])return false;
	}
	return true;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> x;
	if(x.length() == 1 || is_valid())cout << 0;
	else cout << rand()%x.length();
	return 0;
}