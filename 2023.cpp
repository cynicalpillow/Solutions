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
string s;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n;
	int curr = 0;
	int steps = 0;
	for(int i = 0; i < n; i++){
		cin >> s;
		if(s[0] == 'A' || s[0] == 'P' || s[0] == 'O' || s[0] == 'R'){
			steps += abs(0-curr);
			curr = 0;
		} else if(s[0] == 'B' || s[0] == 'M' || s[0] == 'S'){
			steps += abs(1-curr);
			curr = 1;
		} else {
			steps += abs(2-curr);
			curr = 2;
		}
	}
	cout << steps;
	return 0;
}