#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int fr;
string s, s1;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> s1 >> s;

	fr = stoi(s1);

	if((fr != 416 && fr != 647 && fr != 437) || (s.length() != 7) || (s.length()+s1.length() != 10))
		cout << "invalid";
	else if(fr == 416)
		cout << "valuable";
	else 
		cout << "valueless";
	return 0;
}