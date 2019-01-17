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
int N, K, A, B, longestFirst, longestFirstID, longestSecond, longestSecondID, max_diameter;
vector<int> adj[100005];
int out_degree[100005];
bool visited1[100005]; //dfs1
bool visited2[100005]; //dfs2
bool visited3[100005]; //dfs3
bool in_diameter[100005];
int lengths[100005];

//Find diameter
void dfs1(int id, int dist){
	visited1[id] = true;
	if(dist > longestFirst){
		longestFirst = dist;
		longestFirstID = id;
	}
	for(int i : adj[id]){
		if(!visited1[i]){
			dfs1(i, dist+1);
		}
	}
}

void dfs2(int id, int dist){
	visited2[id] = true;
	if(dist > longestSecond){
		longestSecond = dist;
		longestSecondID = id;
	}
	for(int i : adj[id]){
		if(!visited2[i]){
			dfs2(i, dist+1);
		}
	}
}

//Go through the other components not including
int dfs3(int id){
	visited3[id] = true;
	if(id == longestSecondID)return in_diameter[longestSecondID];
	for(int i : adj[id]){
		if(!visited3[i]){
			in_diameter[id] |= dfs3(i);
		}
	}
	return in_diameter[id];
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> K;
	for(int i = 0; i < N-1; i++){
		cin >> A >> B;
		adj[A].pb(B);
		adj[B].pb(A);
		out_degree[A]++;
		out_degree[B]++;
	}
	dfs1(1, 0);
	dfs2(longestFirstID, 0);
	if(K == 1){
		//one shortcut
		cout << (N-1-longestSecond)*2 + (longestSecond+1) << endl;
	} else {
		//two shortcuts
		in_diameter[longestSecondID] = true;
		dfs3(longestFirstID);
		int diameter1 = longestSecond;
		for(int i = 1; i <= N; i++){
			if(in_diameter[i]){
				memset(visited1, false, sizeof(visited1));
				memset(visited2, false, sizeof(visited2));
				longestFirst = 0;
				longestFirstID = i;
				longestSecondID = i;
				longestSecond = 0;
				for(int j : adj[i]){
					if(in_diameter[j]){
						visited1[j] = visited2[j] = true;
					}
				}
				dfs1(i, 0);
				dfs2(longestFirstID, 0);
				max_diameter = max(longestSecond, max_diameter);
			}
		}
		cout << (N-1-diameter1-max_diameter)*2 + (diameter1+1) + (max_diameter+1) << endl;
	}
	return 0;
}