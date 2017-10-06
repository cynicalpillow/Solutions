#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int n;
string s;

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i", &n);
	int x = 0;
	for(int i = 0; i < n; i++){
		cin >> s;
		if(s.length() <= 10)x++;
	}
	cout << x;
	return 0;
}