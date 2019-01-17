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
int K, N, A;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> K >> N;
	int curr = 0;
	for(int i = 0; i < N; i++){
		cin >> A;
		curr += A;
		curr -= K;
		curr = max(0, curr);
	}
	cout << curr;
	return 0;
}