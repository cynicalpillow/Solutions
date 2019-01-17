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
string s;
int counts[27];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> s;
	int n = s.length();
	for(int i = 0; i < n; i++)
		counts[s[i]-'a']++;
	sort(counts, counts+27, greater<int>());
	int total = 0;
	for(int i = 2; i < 26; i++){
		total += counts[i];
	}
	cout << total;
	return 0;
}