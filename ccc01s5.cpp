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
int m, n;
vector<string> A;
vector<string> B;
int path[105];
int steps;
bool found = false;
map<pair<string, string>, bool> visited;

void dfs(string a, string b, int c){
	if(c > m)return;
	if(a.find(b) != 0 && b.find(a) != 0 && a != b)return;
	if(a == b && a != ""){
		steps = c;
		found = true;
		return;
	}
	visited[mp(a, b)] = true;
	for(int i = 0; i < n; i++){
		string a1 = a+A[i];
		string b1 = b+B[i];
		pair<string, string> curr = mp(a1, b1);
		if(!visited[curr]){
			dfs(a1, b1, c+1);
			if(found){
				path[c] = i;
				return;
			}
		}
	}
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> m >> n;
	for(int i = 0; i < n; i++){
		string s;
		cin >> s;
		A.pb(s);
	}
	for(int j = 0; j < n; j++){
		string s;
		cin >> s;
		B.pb(s);
	} 
	dfs("", "", 0);
	if(found){
		cout << steps << "\n";
		for(int i = 0; i < steps; i++){
			cout << path[i]+1 << "\n";
		}
	} else {
		cout << "No solution.";
	}
	return 0;
}