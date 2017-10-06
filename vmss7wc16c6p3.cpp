#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M;
vector<int> adj[100005];
int pre[100005], low[100005]; //Pre order number, and low link value
bool artPoints[100005]; //Articulation points checking array
bool visited[100005];
int cnt = 1;
int cRoot;
int fwd;

void artP(int i, int p){
	pre[i] = cnt++; //Set preorder number
	low[i] = pre[i]; //Set low link to pre order
	visited[i] = true;
	for(int j = 0; j < adj[i].size(); j++){
		if(!visited[adj[i][j]]){
			if(cRoot == i)fwd++; //Increase forward edges if this is the root node
			artP(adj[i][j], i); //Move down the tree
			if(low[adj[i][j]] >= pre[i])artPoints[i] = true; 
			/*
			 *If the deeper node's low link is higher than the current node's pre order number, 
			 *this node is an articulation point
			 */
			low[i] = min(low[i], low[adj[i][j]]); //Set the current low link to be minimum
		} else if(adj[i][j] != p){
			low[i] = min(low[i], pre[adj[i][j]]); 
			//This checks for back edges. 
			/*If it's already visited, the low link value is the lowest of the current, 
			 *and the pre order number of the node we want to visit
			 */
		}
	}
}

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	memset(artPoints, false, sizeof(artPoints));
	memset(visited, false, sizeof(visited));
	scanf("%i%i", &N, &M);
	for(int i = 0; i < M; i++){
		int p1, p2;
		scanf("%i%i", &p1, &p2);
		adj[p1].pb(p2);
		adj[p2].pb(p1);
	}
	for(int i = 1; i <= N; i++){
		if(!visited[i]){
			cRoot = i; fwd = 0; //To check the root node
			artP(i, i);
			artPoints[i] = (fwd > 1); //If foward edges are more than 1, then its a cut vertex
		}
	}
	int tt = 0;
	for(int i = 1; i <= N; i++)
		if(artPoints[i])tt++;
	printf("%i\n", tt);
	for(int i = 1; i <= N; i++)
		if(artPoints[i])printf("%i\n", i);
	return 0;
}