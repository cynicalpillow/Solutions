#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M;
int arr[1005][1005];
bool visited[1005][1005];
queue<pair<int,int>> q;

void bfs(int y, int x){
	visited[y][x] = true;
	q.push(mp(y, x));
	while(!q.empty()){
		pair<int, int> pp = q.front();q.pop();
		if(arr[pp.first][pp.second] == 0 && pp.first - 1 >= 0 && !visited[pp.first-1][pp.second]){
			visited[pp.first-1][pp.second] = true;
			q.push(mp(pp.first-1, pp.second));
		}
		if(arr[pp.first][pp.second] == 1 && pp.second + 1 < M && !visited[pp.first][pp.second+1]){
			visited[pp.first][pp.second+1] = true;
			q.push(mp(pp.first, pp.second+1));
		}
		if(arr[pp.first][pp.second] == 2 && pp.first + 1 < N && !visited[pp.first+1][pp.second]){
			visited[pp.first+1][pp.second] = true;
			q.push(mp(pp.first+1, pp.second));
		}
		if(arr[pp.first][pp.second] == 3 && pp.second - 1 >= 0 && !visited[pp.first][pp.second-1]){
			visited[pp.first][pp.second-1] = true;
			q.push(mp(pp.first, pp.second-1));
		}
		if(pp.first - 1 >= 0 && arr[pp.first-1][pp.second] == 2 && !visited[pp.first-1][pp.second]){
			visited[pp.first-1][pp.second] = true;
			q.push(mp(pp.first-1, pp.second));
		}
		if(pp.first + 1 < N && arr[pp.first+1][pp.second] == 0 && !visited[pp.first+1][pp.second]){
			visited[pp.first+1][pp.second] = true;
			q.push(mp(pp.first+1, pp.second));
		}
		if(pp.second - 1 >= 0 && arr[pp.first][pp.second-1] == 1 && !visited[pp.first][pp.second-1]){
			visited[pp.first][pp.second-1] = true;
			q.push(mp(pp.first, pp.second-1));
		}
		if(pp.second + 1 < M && arr[pp.first][pp.second+1] == 3 && !visited[pp.first][pp.second+1]){
			visited[pp.first][pp.second+1] = true;
			q.push(mp(pp.first, pp.second+1));
		}
	}
}

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &M);
	for(int i = 0; i < N; i++){
		string line;
		cin >> line;
		for(int j = 0; j < M; j++){
			if(line[j] == 'N')arr[i][j] = 0;
			else if(line[j] == 'E')arr[i][j] = 1;
			else if(line[j] == 'S')arr[i][j] = 2;
			else arr[i][j] = 3;
			visited[i][j] = false;
		}
	}
	int count = 0;
	for(int i = 0; i < N; i++){
		for(int j = 0; j < M; j++){
			if(!visited[i][j]){
				count++;
				bfs(i, j);
			}
		}
	}
	cout << count;
	return 0;
}