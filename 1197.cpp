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
string x;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		cin >> x;
		int c = 0;
		if(x[0]-'a'-2 >= 0 && x[1]-'1'-1 >= 0)c++;
		if(x[0]-'a'-2 >= 0 && x[1]-'1'+1 <= 7)c++;
		if(x[0]-'a'-1 >= 0 && x[1]-'1'-2 >= 0)c++;
		if(x[0]-'a'-1 >= 0 && x[1]-'1'+2 <= 7)c++;
		if(x[0]-'a'+2 <= 7 && x[1]-'1'-1 >= 0)c++;
		if(x[0]-'a'+2 <= 7 && x[1]-'1'+1 <= 7)c++;
		if(x[0]-'a'+1 <= 7 && x[1]-'1'-2 >= 0)c++;
		if(x[0]-'a'+1 <= 7 && x[1]-'1'+2 <= 7)c++;
		cout << c << endl;
	}
	return 0;
}