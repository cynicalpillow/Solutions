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
int N, f;
bool prime[100005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	memset(prime, true, sizeof(prime));
	for(int i = 2; i*i <= 100005; i++){
		if(prime[i]){
			for(int j = i*2; j<=100005; j+=i){
				prime[j] = false;
			}
		}
	}
	int count = 0;
	for(int i = 0; i < N; i++){
		cin >> f;
		if(prime[f] && f != 1)count++;
	}
	cout << count;
	return 0;
}