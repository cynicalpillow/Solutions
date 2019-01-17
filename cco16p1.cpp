#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x7fffffff;
int N, M, K, Ai, Bi;
int disjoint_set[1000005];
int sizes[1000005];
bool visited[1000005];
bool hasCycle[1000005];

int root(int a){
	while(a != disjoint_set[a])
		a = disjoint_set[a] = disjoint_set[disjoint_set[disjoint_set[a]]];
	return a;
}

void uni(int a, int b){
	int roota = root(a);
	int rootb = root(b);
	disjoint_set[roota] = rootb;
	if(roota != rootb)sizes[rootb] += sizes[roota];
	else hasCycle[rootb] = true;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> M >> K;
	for(int i = 1; i <= N; i++){
		disjoint_set[i] = i;
		sizes[i] = 1;
	}
	for(int i = 0; i < M; i++){
		cin >> Ai >> Bi;
		uni(Ai, Bi);
	}
	int total = 0;
	int cuts = 0;
	for(int i = 1; i <= N; i++){
		int rooti = root(i);
		if(!visited[rooti]){
			if(sizes[rooti] >= K){
				total += K*(sizes[rooti]/K);
				cuts += sizes[rooti]/K;
				if(sizes[rooti]%K == 0)cuts--;
				if(hasCycle[rooti] && sizes[rooti] > K)cuts++;//cut off both edges
			}
			visited[rooti] = true;
		}
	}
	cout << total << " " << cuts << endl;
	return 0;
}