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
int N, K, L;
int a[505];
int v[505];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> K >> L;
	for(int i = 0; i < N; i++){
		cin >> a[i];
	}
	int c = 0;
	for(int i = 0; i < N; i++){
		if(i == 0)v[i] = max(abs(a[N-1]-a[i]), abs(a[i+1]-a[i]));
		else if(i == N-1)v[i] = max(abs(a[0]-a[i]), abs(a[i-1]-a[i]));
		else v[i] = max(abs(a[i+1]-a[i]), abs(a[i-1]-a[i]));
		if(a[i] >= K && v[i] > L || a[i] < K && v[i] <= L )c++;
	}
	cout << c;
	return 0;
}