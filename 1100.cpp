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
map<int, vector<int>, greater<int>> mpp;
int n;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n;
	for(int i = 0; i < n; i++){
		int x, y;
		cin >> x >> y;
		mpp[y].pb(x);
	}
	for(auto a : mpp){
		for(int x : a.second){
			cout << x << " " << a.first << endl;
		}
	}
	return 0;
}