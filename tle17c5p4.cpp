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
string s, t;
int q;
vector<int> c;
int original_q[100005];
int vals[1000005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> s >> t >> q;
	int max_ai = 0;
	for(int i = 0; i < q; i++){
		int x;
		cin >> x;
		original_q[i] = x;
		max_ai = max(max_ai, x);
	}
	c.pb(0);
	vals[1] = 0;
	int curr_length = 0;
	int curr_on = 0;
	int sl = s.length();
	int tl = t.length();
	int counter = 0;
	for(int i = 2; i <= max_ai; i++){
		curr_on = c[counter];
		int curr = (curr_on?t[curr_length]-'0':s[curr_length]-'0');
		curr_length++;
		curr_length %= (curr_on?tl:sl);
		if(curr_length == 0)counter++;
		vals[i] = curr;
		c.pb(curr);
	}
	for(int i = 0; i < q; i++){
		cout << vals[original_q[i]] << "\n";
	}
	return 0;
}