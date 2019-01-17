#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
string s;
map<string, int> prefixes;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> s;
	int size = s.length();
	for(int i = 0; i < size; i++){
		string sub = s.substr(0, i+1);
		if(sub == string(sub.rbegin(), sub.rend()))
			prefixes[sub]++;
	}
	for(int i = 1; i < size; i++){
		for(int j = 1; j <= i; j++){
			string sub = s.substr(j, i-j+1);
			if(prefixes.find(sub) != prefixes.end() && sub == string(sub.rbegin(), sub.rend())){
				prefixes[sub]++;
			}
		}
	}
	int c = 0;
	for (auto const& x : prefixes){
		if(x.second >= 2){
			int ss = x.first.length();
			c = max(c, ss);
		}
	}
	cout << c;
	return 0;
}