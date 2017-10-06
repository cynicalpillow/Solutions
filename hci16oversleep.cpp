#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int n, m, xs, ys;
int a[1005][1005];
bool visited[1005][1005];
queue<pair<int, pair<int, int>>> q;
int x[][2] = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
int value = -1;

void bfs(){
	visited[ys][xs] = true;
	q.push(mp(0, mp(ys, xs)));
	while(!q.empty()){
		pair<int, pair<int, int>> p = q.front(); q.pop();
		if(a[p.second.first][p.second.second] == 1){
			value = p.first-1;
			break;
		}
		for(int i = 0; i < 4; i++){
			int yc = p.second.first+x[i][0];
			int xc = p.second.second+x[i][1];
			if(yc >= n || yc < 0)continue;
			if(xc < 0 || xc >= m)continue;
			if(visited[yc][xc])continue;
			if(a[yc][xc] == -1)continue;
			visited[yc][xc] = true;
			q.push(mp(p.first+1, mp(yc, xc)));
		}
	}
	cout << value;
}

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &n, &m);
	for(int i = 0; i < n; i++){
		string s;
		cin >> s;
		for(int j = 0; j < m; j++){
			if(s[j] == 'X')a[i][j] = -1;
			else if(s[j] == 'e')a[i][j] = 1;
			else if(s[j] == 's'){
				xs = j;
				ys = i;
			}
		}
	}
	bfs();
	return 0;
}