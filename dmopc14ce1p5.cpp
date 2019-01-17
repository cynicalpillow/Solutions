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
int n, Hi, Ei, Pi, s;
vector<pair<int, pair<int, int>>> teachers;
int dp[55][1005][105];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n;
	for(int i = 0; i < n; i++){
		cin >> Hi >> Ei >> Pi;
		teachers.pb(mp(Hi, mp(Ei, Pi)));
	}
	cin >> s;
	for(int i = 0; i < n; i++){
		for(int j = 1; j <= s; j++){
			for(int k = 1; k <= (1+Hi/Ei); k++){
				if(i == 0){
					if(j - teachers[i].second.second >= 0)dp[i][j][k] = dp[i][j-teachers[i].second.second][k]
				} else {

				}
			}
		}
	}
	return 0;
}