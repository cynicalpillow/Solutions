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
int matrix[105][105];
int N;
bool foundIdentity = false;
int identity;
int inverses[105];
bool foundInverse[105];

void solve(){
	//Find i
	for(int i = 1; i <= N; i++){
		bool check = true;
		for(int j = 1; j <= N; j++){
			if(matrix[i][j] == j && matrix[j][i] == j){}
			else check = false;
		}
		if(check && foundIdentity){
			cout << "no" << "\n";
			return;
		} else if(check){
			identity = i;
			foundIdentity = true;
		}
	}
	//Check associativity
	for(int i = 1; i <= N; i++){
		for(int j = 1; j <= N; j++){
			for(int k = 1; k <= N; k++){
				if(matrix[matrix[i][j]][k] != matrix[i][matrix[j][k]]){
					cout << "no" << "\n";
					return;
				}
			}
		}
	}
	memset(foundInverse, false, sizeof(foundInverse));
	//Check inverses
	for(int i = 1; i <= N; i++){
		for(int j = 1; j <= N; j++){
			if(matrix[i][j] == identity){
				if(foundInverse[i]){
					cout << "no" << "\n";
					return;
				}
				inverses[i] = j;
				foundInverse[i] = true;
			}
		}
	}
	for(int i = 1; i <= N; i++){
		if(i != inverses[inverses[i]]){
			cout << "no" << "\n";
			return;
		}
	}
	for(int i = 1; i <= N; i++){
		if(matrix[i][inverses[i]] != identity || matrix[inverses[i]][i] != identity){
			cout << "no" << "\n";
			return;
		}
	}
	cout << "yes" << "\n";
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	while(true){
		cin >> N;
		if(N == 0)return 0;
		for(int i = 1; i <= N; i++){
			for(int j = 1; j <= N; j++){
				int x;
				cin >> x;
				matrix[i][j] = x;
			}
		}
		solve();
		foundIdentity = false;
	}
	
	return 0;
}