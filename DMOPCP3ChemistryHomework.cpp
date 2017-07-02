#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int graph[1001][1001];
int counts[1001];
bool hydrogen[1001];
int N, M, x, y;
bool visited[1001][1001];

int search(int curr){
	int total = 0;
	for(int i = 1; i <= N; i++){
		for(int j = 1; j <= N; j++){
			if(visited[i][j])continue;
			if(graph[i][j] > 2)return -1;
			else if (graph[i][j] == 2 && ((hydrogen[i] && !hydrogen[j])||(!hydrogen[i] && hydrogen[j])))return -1;
			else if(graph[i][j] >= 1 && (hydrogen[i] && hydrogen[j]))return -1;
			else if(graph[i][j] == 1 && ((hydrogen[i] && !hydrogen[j])||(!hydrogen[i] && hydrogen[j]))){
				visited[i][j] = visited[j][i] = true;
				total += 413;
			} else if(graph[i][j] == 1 && (!hydrogen[i] && !hydrogen[j])){
				visited[i][j] = visited[j][i] = true;
				total += 346;
			} else if(graph[i][j] == 2 && (!hydrogen[i] && !hydrogen[j])){
				visited[i][j] = visited[j][i] = true;
				total += 615;
			}
		}
	}
	return total;
}

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &M);
	while(M--){
		scanf("%i%i", &x, &y);
		graph[x][y]++;
		graph[y][x]++;
		counts[x]++;
		counts[y]++;
	}
	bool check = true;
	for(int i = 1; i <= N; i++){
		if(counts[i] == 1){
			hydrogen[i] = true;
		} else if(counts[i] == 4){
			hydrogen[i] = false;
		} else {
			check = false;
			break;
		}
	}
	if(!check){
		printf("%s", "Impossible");
		return 0;
	}
	int result = search(1);
	if(result == -1)printf("%s", "Impossible");
	else {
		int hs = 0;
		for(int i = 1; i <= N; i++){
			if(hydrogen[i])hs++;
		}
		printf("%i\n", result);
		printf("%s", "C");
		if(N-hs > 1)printf("%i", (N-hs));
		printf("%s", "H");
		if(hs > 1)printf("%i", hs);
	}
	return 0;
}