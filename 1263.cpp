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
int n, m;
int freq[10005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n >> m;
	for(int i = 0; i < m; i++){
		int x;
		cin >> x;
		freq[x]++;
	}
	for(int i = 1; i <= n; i++){
		printf("%.2f", ((double)freq[i]/(double)m)*100);
		printf("%s\n", "%");
	}
	return 0;
}