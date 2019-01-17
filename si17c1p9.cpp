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
int R, C;
string x;
int a[105][105];
int dir[16] = {-1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1};

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> R >> C;
	for(int i = 0; i < R; i++){
		cin >> x;
		for(int j = 0; j < C; j++){
			a[i][j] = 0;
			if(x[j] == 'S')a[i][j] = 1;
		}
	}
	int squares = 0;
	for(int i = 0; i < R; i++){
		for(int j = 0; j < C; j++){
			if(a[i][j])continue;
			int count = 0;
			int adj = 0;
			for(int z = 0; z < 16; z+=2){
				if(i+dir[z] < R && i+dir[z] >= 0 && j+dir[z+1] < C && j+dir[z+1] >= 0)
					adj++;
			}
			for(int z = 0; z < 16; z+=2){
				if(i+dir[z] < R && i+dir[z] >= 0 && j+dir[z+1] < C && j+dir[z+1] >= 0 && a[i+dir[z]][j+dir[z+1]])
					count++;
			}
			if(count < adj/2.0)squares++;
		}
	}
	cout << squares;
	return 0;
}