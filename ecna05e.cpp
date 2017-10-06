#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M, b1, b2, c;
int adj[20][20];
int counts[20];
int m = INFINITE;
vector<pair<int, int>> edges;
bool visitedEdges[25];

bool check(){
	for(int i = 1; i <= N; i++)
			if(counts[i] < 2)return false;
	int count = 1;
	queue<int> q;
	bool visited[20];
	memset(visited, false, sizeof(visited));
	visited[1] = true;
	q.push(1);
	while(!q.empty()){
		int curr = q.front(); q.pop();
		for(int i = 1; i <= N; i++){
			if(adj[curr][i] != -1 && !visited[i]){
				visited[i] = true;
				count++;
				q.push(i);
			}
		}
	}
	return (count == N);
}

void dfs(int curr, int c){
	visitedEdges[curr] = true;
	m = min(m, c);
	for(int i = 0; i < M; i++){
		int z = edges[i].first;
		int x = edges[i].second;
		int temp = adj[z][x];
		adj[z][x] = -1;
		adj[x][z] = -1;
		counts[z]--;
		counts[x]--;
		if(check() && !visitedEdges[i])
			dfs(i, c-temp);
		adj[z][x] = temp;
		adj[x][z] = temp;
		counts[z]++;
		counts[x]++;
	}
}

int main(){
	freopen("input.txt", "r", stdin);
	int testCase = 1;
	while(true){
		m = INFINITE;
		scanf("%i%i", &N, &M);
		if(N == 0 && M == 0)return 0;
		memset(counts, 0, sizeof(counts));
		for(int i = 1; i <= N; i++){
			for(int j = 1; j <= N; j++){
				adj[i][j] = -1;
			}
		}
		int total = 0;
		for(int i = 0; i < M; i++){
			scanf("%i%i%i", &b1, &b2, &c);
			adj[b1][b2] = c;
			adj[b2][b1] = c;
			edges.pb(mp(b1, b2));
			total += c;
			counts[b1]++;
			counts[b2]++;
		}
		if(check())
			m = total;
		if(m != INFINITE){
			for(int i = 0; i < M; i++){
				int z = edges[i].first;
				int x = edges[i].second;
				int temp = adj[z][x];
				adj[z][x] = -1;
				adj[x][z] = -1;
				counts[z]--;
				counts[x]--;
				if(check()){
					memset(visitedEdges, false, sizeof(visitedEdges));
					dfs(i, total-temp);
				}
				adj[z][x] = temp;
				adj[x][z] = temp;
				counts[z]++;
				counts[x]++;
			}
		}
		if(m == INFINITE)cout << "There is no reliable net possible for test case " << testCase << "." << endl;
		else cout << "The minimal cost for test case " << testCase << " is " << m << "." << endl;
		testCase++;
	}
	return 0;
}