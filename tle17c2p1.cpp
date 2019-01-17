#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
string s;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> s;
	int n = s.length();
	int c = 0;
	for(int i = 2; i < n; i++){
		if(s[i] == 'r'){
			cout << " x)";
			c--;
		} else {
			cout << "(c" << s[i] << "r" << " ";
			c++;
		}
	}
	for(int i = 0; i < c; i++)
		cout << ")";
	return 0;
}