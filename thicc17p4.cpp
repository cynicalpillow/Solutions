#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N;
string original;

int dp(string z){
	int m[105][105];
	int n = original.length();
	int n1 = z.length();
	m[0][0] = 0;
	for(int i = 0; i <= n; i++){
		for(int j = 0; j <= n1; j++){
			if(i == 0 && j == 0)continue;
			if(i == 0){
				m[i][j] = m[i][j-1]+1;
			} else if(j == 0){
				m[i][j] = m[i-1][j]+1;
			} else if(z[j-1] != original[i-1]){
				m[i][j] = min(m[i-1][j-1]+1, min(m[i-1][j]+1, m[i][j-1]+1)); //replacing, deleting, inserting
			} else {
				m[i][j] = m[i-1][j-1];
			}
		}
	}
	return m[n][n1];
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> original;
	cin >> N;
	double ans = INFINITE;
	for(int i = 0; i < N; i++){
		int z;
		cin >> z;
		double total = 0;
		for(int j = 0; j < z; j++){
			string x;
			cin >> x;
			if(((total)/((double)z)) < ans)total+=dp(x);
		}
		ans = min(((double)(total)/((double)z)), ans);
	}
	printf("%lf", ans);
	return 0;
}