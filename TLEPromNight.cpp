#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M;
vector<int> aB[105];
vector<int> aG[105];
int residual[105][105];
int parent[105];
int sParent;
bool visited[105];
bool visitedG[105];
int amount;

bool bfs(){
	queue<pair<int, int>> q;
	q.push(mp(0, 0));
	parent[0] = -1;
	visited[0] = true;
	while(!q.empty()){
		pair<int, int> u = q.front();
		q.pop();
		cout<<u.first << " " << u.second << endl;
		if(u.first == -1)return true;
		if(u.first == 1 && u.second == 1)continue;
		if(u.second == 0){
			for(int x : aB[u.first]){
				if(!visited[x] && residual[u.first][x] > 0){
					cout << "hi " << x << endl;
					q.push(mp(x, 1));
					parent[x] = u.first;
					visited[x] = true;
				}
			}
		} else if(u.second == 1){
			for(int x : aB[u.first]){
				if(!visitedG[x] && residual[u.first][x] > 0){
					q.push(mp(x, 2));
					parent[x] = u.first;
					visitedG[x] = true;
				}
			}
		} else {
			for(int x : aG[u.first]){
				q.push(mp(x, 3));
				sParent = u.first;
			}
		}
	}
	return false;
}

void fulkerson(){
	int total_flow = 0;
	bool chosenGirls[105];
	memset(chosenGirls, false, sizeof(chosenGirls));
	while(bfs()){
		memset(visited, false, sizeof(visited));
		memset(visitedG, false, sizeof(visitedG));
		int p_flow = INFINITE;
		int v = sParent;
		chosenGirls[v] = true;
		for(; v != 0; v=parent[v]){
			int u = parent[v];
			p_flow = min(p_flow, residual[u][v]);
		}
		for(v = sParent; v != 0; v=parent[v]){
			int u = parent[v];
			residual[u][v] -= p_flow;
			residual[v][u] += p_flow;
		}
		total_flow += p_flow;
	}
	for(int i = 0; i <= M; i++){
		cout << boolalpha << chosenGirls[i] << " ";
	}
	cout << endl;
	int result = 0;
	for(int i : aB[1]){
		if(chosenGirls[i])continue;
		else result++;
		cout << result << endl;
	}
	cout << result;
}

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &M);
	bool added[105];
	for(int i = 1; i <= N; i++){
		int t;
		scanf("%i", &t);
		if(i == 1)amount = t;
		for(int j = 0; j < t; j++){
			int x;
			scanf("%i", &x);
			aB[i].pb(x);
			residual[i][x] = 1;
			added[x] = true;
		}
	}
	if(amount == 1 && added[aB[1].at(0)]){
		cout << 0;
		return 0;
	}
	for(int i = 1; i <= N; i++){
		residual[0][i] = 1;
		aB[0].pb(i);
	}
	for(int i = 1; i <= M; i++)aG[i].pb(-1);
	fulkerson();
	return 0;
}