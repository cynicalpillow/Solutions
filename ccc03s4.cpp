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
int N;
string s;
set<string> xx;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		cin >> s;
		int x = s.length();
		for(int j = 0; j < x; j++){
			for(int y = j; y < x; y++){
				xx.insert(s.substr(j, (y-j)+1));
			}
		}
		cout << xx.size()+1 << "\n";
		xx.clear();
	}
	return 0;
}