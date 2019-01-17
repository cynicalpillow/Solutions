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
int n, k;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n >> k;
	int leftOver = 0;
	int leftOverD = 0;
	for(int i = 0; i < n; i++){
		int x;
		cin >> x;
		leftOver += max(0, x-k);
		leftOverD += max(0, k-x);
	}
	cout << leftOver << " " << leftOverD;
	return 0;
}