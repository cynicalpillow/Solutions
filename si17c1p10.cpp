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
int N, M;
int alice[205];
int carl[205];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		cin >> alice[i];
	}
	int total_alice = 0;
	for(int i = 0; i < N; i++){
		int x;
		cin >> x;
		alice[i] *= x;
		total_alice += alice[i];
	}
	cin >> M;
	for(int i = 0; i < M; i++){
		cin >> carl[i];
	}
	int total_carl = 0;
	for(int i = 0; i < M; i++){
		int x;
		cin >> x;
		carl[i] *= x;
		total_carl += carl[i];
	}
	cout << total_alice << " " << total_carl;
	return 0;
}