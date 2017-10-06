#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M;
vector<tuple<int, int, int>> edges;
int nodes[50001];
vector<tuple<int, int, int>> mstree;
int smallest = 0;
int secondmost = INFINITE;
bool marked[100001];

int root(int a, int x[]){
	while(x[a] != a){
		a = x[a] = x[x[a]];
	}
	return a;
}

bool find(int a, int b, int x[]){
	return (root(a, x) == root(b, x));
}
void un(int a, int b, int x[]){
	int roota = root(a, x);
	int rootb = root(b, x);
	if(roota != rootb)x[roota] = rootb;
}
void smst(tuple<int, int, int> dontinclude){
	bool notincluded = false;
	int se = smallest - get<0>(dontinclude);
	cout << se << endl;
	int idx = 0;
	for(auto x : edges){
		int w = get<0>(x);
		int v = get<1>(x);
		int u = get<2>(x);
		if(marked[idx])continue;
		if(!notincluded && w == get<0>(dontinclude) && v == get<1>(dontinclude) && u == get<2>(dontinclude)){
			notincluded = true;
			continue;
		}
		if(w + se > secondmost)break;
		if(!find(v, u, nodes)){
			se += w;
			un(v, u, nodes);
		}
	}
	cout << se << endl;
	if(se > smallest)secondmost = min(secondmost, se);
}
void mst(){
	int idx = 0;
	for(auto x : edges){
		int w = get<0>(x);
		int v = get<1>(x);
		int u = get<2>(x);
		if(!find(v, u, nodes)){
			smallest += w;
			un(v, u, nodes);
			mstree.pb(x);
			marked[idx]= true;
		}
		idx++;
	}
}
int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &M);
	for(int i = 1; i <= N; i++)nodes[i] = i;
	for(int i = 0; i < M; i++){
		int w, v, u;
		scanf("%i%i%i", &v, &u, &w);
		edges.push_back(make_tuple(w, v, u));
	}
	sort(edges.begin(), edges.end());
	mst();
	if(edges.size() == N-1){
		cout << -1;
		return 0;
	}
	sort(mstree.begin(), mstree.end());
	for(auto x : mstree){
		int temp = nodes[get<1>(x)];
		nodes[get<1>(x)] = get<1>(x);
		cout << "hi" << endl;
		if(find(get<1>(x), get<2>(x), nodes)){
			temp = nodes[get<2>(x)];
			nodes[get<2>(x)] = get<2>(x);
			cout << "connected " << find(get<1>(x), get<2>(x), nodes) << endl;
			smst(x);
			nodes[get<2>(x)] = temp;
		} else {
			cout << "connected " <<find(get<1>(x), get<2>(x), nodes) << endl;
			smst(x);
			nodes[get<1>(x)] = temp;
		}
	}
	//if(secondmost == INFINITE || secondmost == smallest) cout << -1;
	/*else*/ cout << secondmost;
	return 0;
}