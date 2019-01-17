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
int vals[26];
string s;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	int c = 1;
	for(int i = 0; i < 26; i++){
		vals[i] = c;
		if(c == 3)c = 1;
		else c++;
	}
	getline(cin, s);
	int n = s.length();
	int cost = 0;
	for(int i = 0; i < n; i++){
		if(s[i] >= 'a' && s[i] <= 'z'){
			cost += vals[s[i]-'a'];
		} else if(s[i] == '.'){
			cost++;
		} else if(s[i] == ','){
			cost+=2;
		} else if(s[i] == '!'){
			cost+=3;
		} else {
			cost++;
		}
	}
	cout << cost;
	return 0;
}