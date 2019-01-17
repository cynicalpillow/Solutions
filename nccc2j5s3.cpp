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
int m, n;
int arr[35][35];
int dp[35][35][2];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> m >> n;
	for(int i = 0; i < m; i++){
		string s;
		cin >> s;
		int n = s.length();
		for(int j = 0; j < n; j++){
			if(s[j] == 'B')arr[i][j] = -1;
			else if(s[j] == 'R')arr[i][j] = -2;
			else arr[i][j] = 0;
		}
	}
	for(int i = 0; i < m; i++){
		for(int j = 0; j < n; j++){
			if(arr[i][j] == -1){
				for(int k = i; k >= 0; k--){
					for(int z = j; z >= 0; z--){
						if(arr[k][z] == -2){
							cout << "0";
							return 0;
						}
						arr[k][z] = -1;
					}
				}
			} else if(arr[i][j] == -2){
				for(int k = i; k < m; k++){
					for(int z = j; z < n; z++){
						if(arr[k][z] = -1){
							cout << "0";
							return 0;
						}
						arr[k][z] = -2;
					}
				}
			}
		}
	}
	cout << 3;
	return 0;
}