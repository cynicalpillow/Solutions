#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N;
string s;

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	cin >> s;
	int sf = 0;
	int fs = 0;
	for(int i = 0; i < N-1; i++){
		if(s[i] == 'S' && s[i+1] == 'F')sf++;
		else if(s[i] == 'F' && s[i+1] == 'S')fs++;
	}
	if(sf > fs)cout << "YES";
	else cout << "NO";
	return 0;
}