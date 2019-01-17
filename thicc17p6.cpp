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
int N, T, Ai, Bi, Ci, total;
vector<pair<int, int>> adj[200005];
int counts[200005];
bool visited[200005];
bool visited1[200005];
bool visited2[200005];
int ld1 = 0;
int ld2 = 0;
int ldId1, ldId2;
int ldists1[200005];
int ldists2[200005];

//Diameter finding
void dfs1(int id, int dist){
	visited[id] = true;
	if(dist > ld1){
		ld1 = dist;
		ldId1 = id;
	}
	for(pair<int, int> j : adj[id]){
		if(!visited[j.first]){
			dfs1(j.first, dist+j.second);
		}
	}
}

void dfs2(int id, int dist){
	visited1[id] = true;
	if(dist > ld2){
		ld2 = dist;
		ldId2 = id;
	}
	ldists1[id] = dist;
	for(pair<int, int> j : adj[id]){
		if(!visited1[j.first]){
			dfs2(j.first, dist+j.second);
		}
	}
}
//Find distance from both points of diameter to other nodes
void dfs3(int id, int dist){
	visited2[id] = true;
	ldists2[id] = dist;
	for(pair<int, int> j : adj[id]){
		if(!visited2[j.first]){
			dfs3(j.first, dist+j.second);
		}
	}
}

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &T);
	for(int i = 0; i < N-1; i++){
		scanf("%i%i%i", &Ai, &Bi, &Ci);
		total += Ci;
		counts[Ai]++;
		counts[Bi]++;
		adj[Ai].pb(mp(Bi, Ci));
		adj[Bi].pb(mp(Ai, Ci));
	}
	dfs1(1, 0);
	dfs2(ldId1, 0);
	dfs3(ldId2, 0);
	for(int i = 1; i <= N; i++){
		if(counts[i] != T)continue;
		if(T == 1){
			//Leaf
			int maxD = max(ldists1[i], ldists2[i]);
			//Another diameter point
			if(maxD == ldists1[ldId2] || maxD == ldists2[ldId1])
				maxD = ld2 + (total-ld2)*2;
			else
				maxD = maxD + (total-maxD)*2;
			printf("%i %i\n", i, maxD);
		} else {
			int maxD = max(ldists1[i], ldists2[i]);
			maxD = maxD + (total-maxD)*2;
			printf("%i %i\n", i, maxD);
		}
	}
	return 0;
}