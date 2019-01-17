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
int N, A, B, C, D;
bool dp[35][35][35][35];

void solve(){
	dp[0][0][0][0] = true;
	for(int i = 0; i <= A; i++){
		for(int j = 0; j <= B; j++){
			for(int z = 0; z <= C; z++){
				for(int y = 0; y <= D; y++){
					bool check = false;
					if(i-2 >= 0 && j-1 >= 0 && y-2 >= 0)
						check |= (!dp[i-2][j-1][z][y-2]);
					if(i-1 >= 0 && j-1 >= 0 && z - 1 >= 0 && y - 1 >= 0)
						check |= (!dp[i-1][j-1][z-1][y-1]);
					if(z - 2 >= 0 && y - 1 >= 0)
						check |= (!dp[i][j][z-2][y-1]);
					if(j-3 >= 0)
						check |= (!dp[i][j-3][z][y]);
					if(i-1 >= 0 && y - 1 >= 0)
						check |= (!dp[i-1][j][z][y-1]);
					dp[i][j][z][y] = check;
				}
			}
		}
	}
	
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	A = B = C = D = 30;
	solve();
	for(int i = 0; i < N; i++){
		cin >> A >> B >> C >> D;
		cout << (dp[A][B][C][D]?"Patrick":"Roland") << endl;
	}
	return 0;
}