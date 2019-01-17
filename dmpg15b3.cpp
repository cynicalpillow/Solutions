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
int W, H;
string x;
char a[105][105];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> W >> H;
	getline(cin, x);
	int c = 0;
	for(int i = 0; i < H; i++){
		getline(cin, x);
		for(int j = 0; j < W; j++){
			if(x[j] == '#')a[i][j] = '#';
			else a[i][j] = ' ';
			if(x[j] == 'o')c++;
		}
	}
	for(int i = 0; i < H; i++){
		for(int j= 0; j < W; j++){
			cout << a[i][j];
		}
		cout << endl;
	}
	for(int i = 0; i < c; i++)
		cout << 'o';
	return 0;
}