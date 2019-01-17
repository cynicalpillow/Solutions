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
int N;
int counts[200005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	memset(counts, 0, sizeof(counts));
	for(int i = 0; i < N; i++)counts[i] = 1;
	for(int i = 0; i < N-1; i++){
		int x;
		cin >> x;
		counts[x]++;
	}
	int total_nodes = 0;
	int prod = 1;
	for(int i = 0; i < N; i++){
		total_nodes += counts[i];
		if(counts[i])prod*=counts[i];
	}
	cout << prod*(total_nodes*2+10);
	return 0;
}