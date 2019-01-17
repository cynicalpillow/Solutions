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
int n, m, a, b;
int bonds[1005][1005];
bool visited[1005][1005];
int carbon[1005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n >> m;
	memset(bonds, 0, sizeof(bonds));
	for(int i = 0; i < m; i++){
		cin >> a >> b;
		if(bonds[a][b] == 1){
			bonds[a][b] = 2;
			bonds[a][b] = 2;
		} else if(bonds[a][b] == 0){
			bonds[a][b] = 1;
			bonds[b][a] = 1;
		} else {
			cout << "Impossible";
			return 0;
		}
		carbon[a]++;
		carbon[b]++;
		if(carbon[a] > 4 || carbon[b] > 4){
			cout << "Impossible";
			return 0;
		}
	}
	int total = 0;
	for(int i = 1; i <= n; i++){
		for(int j = 1; j <= n; j++){
			if(!visited[i][j]){
				if(bonds[i][j] == 1 && (carbon[i] == 1||carbon[j] == 1)){
					total += 413;
				} else if(bonds[i][j] == 1 && carbon[i] == 4 && carbon[j] == 4){
					total += 346;
				} else if(bonds[i][j] == 2 && carbon[i] == 4 && carbon[j] == 4){
					total += 615;
				} else {
				}
				visited[i][j] = true;
				visited[j][i] = true;
			}
		}
	}
	int Cs = 0;
	int Hs = 0;
	for(int i = 1; i <= n; i++){
		if(carbon[i] == 4)Cs++;
		else if(carbon[i] == 1)Hs++;
		else {
			cout << "Impossible";
			return 0;
		}
	}
	cout << total << endl;
	cout << "C" << (Cs == 1?"":to_string(Cs)) << "H" << (Hs == 1?"":to_string(Hs));
	return 0;
}