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

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> s;
	int n = s.length();
	for(int i = 0; i < n-1; i++){
		bool poss = true;
		for(int j = 0; j < i; j++){
			if(s[j] != s[i-j])poss = false;
		}
		if(poss){
			int high = n-1;
			for(int z = i+1; z < n; z++){
				if(s[z] != s[high--])poss= false;
			}
			if(poss){
				cout << "YES";
				return 0;
			}
		}
	}
	cout <<"NO";
	return 0;
}