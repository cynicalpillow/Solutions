#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int R, C, Q;
map<int, vector<int>> x;
map<int, vector<int>> y;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> R >> C;
	for(int i = 1; i <= R; i++){
		string z;
		cin >> z;
		for(int j = 1; j <= C; j++){
			if(z[j-1] == 'X'){
				x[j].pb(i);
				y[i].pb(j);
			}
		}
	}
	cin >> Q;
	for(int i= 0; i < Q; i++){
		int x1, y1;
		cin >> x1 >> y1;
		if(x[x1].size() > 0 || y[y1].size() > 0)cout << "Y" << "\n";
		else cout << "N" << "\n";
	}
	return 0;
}