#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
vector<pair<int, int>> friends;
int N, T;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		int product = 1;
		scanf("%i", &T);
		for(int j = 0; j < T; j++){
			int x;
			scanf("%i", &x);
			product *= x;
		}
		friends.pb(mp(product, i+1));
	}
	sort(friends.rbegin(), friends.rend());
	cout << friends[0].second << "\n";
	cout << friends[1].second << "\n";
	cout << friends[2].second << "\n";
	return 0;
}