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
int X, Y, Z, W;
long long taxes[22][22][22][22];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> X >> Y >> Z >> W;
	for(int i = 0; i < X; i++){
		for(int j = 0; j < Y; j++){
			for(int k = 0; k < Z; k++){
				for(int y = 0; y < W; y++){
					cin >> taxes[i][j][k][y];
				}
			}
		}
	}
	for(int i = 0; i < X; i++){
		for(int j = 0; j < Y; j++){
			
		}
	}
	return 0;
}