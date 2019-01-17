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
int arr[105][105];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n;
	int curr = 1;
	for(int j = n-1; j >= 0; j--){
		int originalJ = j;
		for(int i = 0; i < n && j < n; i++)arr[i][j++] = curr++;
		j = originalJ;
	}
	for(int i = 1; i < n; i++){
		int originalI = i;
		for(int j = 0; j < n && i < n; j++)arr[i++][j] = curr++;
		i = originalI;
	}
	for(int i= 0; i < n; i++){
		for(int j = 0; j < n; j++){
			cout << arr[i][j] << " ";
		}
		cout << endl;
	}
	return 0;
}