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
int n;
int vs[105][105];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n;
	for(int i = 0; i < n; i++){
		for(int j = 0; j < n; j++){
			cin >> vs[i][j];
		}
	}
	int row = 0;
	int i = 0;
	int j = 0;
	for(int row = 0; row < n; row++){
		i = row;
		for(j = 0; i >= 0 && j < n; i--, j++){
			cout << vs[i][j] << " ";
		}
	}
	for(int col = 1; col < n; col++){
		j = col;
		for(i = n-1; i >= 0 && j < n; i--, j++){
			cout << vs[i][j] << " ";
		}
	}
	return 0;
}