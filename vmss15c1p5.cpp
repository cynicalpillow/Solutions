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
int N, S, R, V, A, B;
string E;
struct apple {
	string name;
	int V, A, B;
	//A == cost, B == space;
	//R == total moneys, S == space
};
apple apples[15];
int dp[1005][1005];
int counts[1005][1005][15];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> R >> S;
	for(int i = 0; i < N; i++){
		cin >> E >> V >> A >> B;
		apples[i] = {E, V, A, B};
	}
	dp[0][0] = 0;
	for(int i = 0; i <= R; i++){
		for(int j = 0; j <= S; j++){
			dp[i][j] = 0;
			for(int z = 0; z < N; z++){
				if(i-apples[z].A >= 0 && j-apples[z].B >= 0){
					if(dp[i][j] < dp[i-apples[z].A][j-apples[z].B] + apples[z].V){
						dp[i][j] = dp[i-apples[z].A][j-apples[z].B] + apples[z].V;
						for(int f = 0; f < N; f++)
							counts[i][j][f] = counts[i-apples[z].A][j-apples[z].B][f];
						counts[i][j][z]++;
					}
				}
			}
			if(dp[i][j-1] > dp[i][j]){
				dp[i][j] = dp[i][j-1];
				for(int f = 0; f < N; f++)
					counts[i][j][f] = counts[i][j-1][f];
			}
		}
	}
	cout << dp[R][S] << endl;
	for(int i = 0; i < N; i++)
		cout << apples[i].name << " " << counts[R][S][i] << endl;
	return 0;
}