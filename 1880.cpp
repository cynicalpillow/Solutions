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
int n, a;
map<int, int> mpp;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n;
	for(int i = 0; i < n; i++){
		cin >> a;
		mpp[a] = 1;
	}
	cin >> n;
	for(int i = 0; i < n; i++){
		cin >> a;
		mpp[a]++;
	}
	cin >> n;
	for(int i = 0; i < n; i++){
		cin >> a;
		mpp[a]++;
	}
	int c = 0;
	for(auto it : mpp){
		if(it.second == 3)c++;
	}
	cout << c;
	return 0;
}