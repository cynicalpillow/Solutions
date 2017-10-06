#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

struct Node {
	int dist;
	int id;
	int x;
	int y;
	bool operator<(const Node &rhs) const{
        return dist > rhs.dist;
    }
};

int INFINITE = 0x3f3f3f3f;
int N, M, X, Y;
double R;
pair<pair<int, int>, double> team[3005];
pair<pair<int, int>, double> enemy[3005];
priority_queue<Node> q;
int dists[3005];

int dijkstra(pair<pair<int, int>, double> arr[], int start, int baller, int size){
	dists[start] = 0;
	q.push({0, start, arr[start].first.first, arr[start].first.second});
	while(!q.empty()){
		Node curr = q.top();q.pop();
		for(int i = 0; i < size; i++){
			if(arr[curr.id].second < sqrt(pow(arr[i].first.first - curr.x, 2) + pow(arr[i].first.second - curr.y, 2)))continue;
			if(dists[i] > curr.dist+1){
				dists[i] = curr.dist+1;
				q.push({dists[i], i, arr[i].first.first, arr[i].first.second});
			}
		}
	}
	return dists[baller];
}

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &M);
	int baller1, baller2;
	for(int i = 0; i < N; i++){
		scanf("%i%i%lf", &X, &Y, &R);
		team[i] = mp(mp(X, Y), R);
		if(R == 9001)baller1 = i;
	}
	for(int i = 0; i < M; i++){
		scanf("%i%i%lf", &X, &Y, &R);
		enemy[i] = mp(mp(X, Y), R);
		if(R == 9001)baller2 = i;
	}
	memset(dists, INFINITE, sizeof(dists));
	int r1, r2;
	int start;
	double val = -INFINITE;
	for(int i = 0; i < N; i++){
		if(val < team[i].first.second){
			val = team[i].first.second;
			start = i;
		}
	}
	r1 = dijkstra(team, start, baller1, N);
	memset(dists, INFINITE, sizeof(dists));
	val = -INFINITE;
	for(int i = 0; i < M; i++){
		if(val < enemy[i].first.second){
			val = enemy[i].first.second;
			start = i;
		}
	}
	r2 = dijkstra(enemy, start, baller2, M);
	if(r1 == r2){
		cout << "SUDDEN DEATH";
	} else if(r1 < r2){
		cout << "We are the champions!";
	} else if(r2 < r1){
		cout << ":'(";
	}
	return 0;
}