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
int curr[1050][1050];
int temp[1050][1050];
struct matrix{
	int r, c;
	int grid[500][500];
};
vector<matrix> matrices;
int N;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		int r, c;
		cin >> r >> c;
		matrix x;
		x.r = r;
		x.c = c;
		for(int j = 0; j < r; j++){
			for(int k = 0; k < c; k++){
				int z;
				cin >> z;
				x.grid[j][k] = z;
			}
		}
		matrices.pb(x);
	}
	for(int i = 0; i < matrices[0].r; i++){
		for(int j = 0; j < matrices[0].c; j++){
			curr[i][j] = matrices[0].grid[i][j];
		}
	}

	int currRow = matrices[0].r;
	int currCol = matrices[0].c;
	for(int i = 1; i < N; i++){
		for(int z = 0; z < currRow; z++){
			for(int y = 0; y < currCol; y++){
				for(int j = 0; j < matrices[i].r; j++){
					for(int k = 0; k < matrices[i].c; k++){
						temp[z*matrices[i].r+j][y*matrices[i].c+k] = curr[z][y] * matrices[i].grid[j][k];
					}
				}
			}
		}
		currRow *= matrices[i].r;
		currCol *= matrices[i].c;
		for(int j = 0; j < currRow; j++){
			for(int z = 0; z < currCol; z++){
				curr[j][z] = temp[j][z];
			}
		}
	}
	int maxItem = -INFINITE;
	int minItem = INFINITE;
	int maxRSum = -INFINITE;
	int minRSum = INFINITE;
	int maxCSum = -INFINITE;
	int minCSum = INFINITE;
	for(int i = 0; i < currRow; i++){
		int currSum = 0;
		for(int j = 0; j < currCol; j++){
			currSum += curr[i][j];
			maxItem = max(maxItem, curr[i][j]);
			minItem = min(minItem, curr[i][j]);
		}
		maxRSum = max(maxRSum, currSum);
		minRSum = min(minRSum, currSum);
	}
	for(int j = 0; j < currCol; j++){
		int currSum = 0;
		for(int i = 0; i < currRow; i++){
			currSum += curr[i][j];
		}
		maxCSum = max(maxCSum, currSum);
		minCSum = min(minCSum, currSum);
	}
	cout << maxItem << endl << minItem << endl << maxRSum << endl << minRSum << endl << maxCSum << endl << minCSum;
	return 0;
}