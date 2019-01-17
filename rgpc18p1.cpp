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

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> M;
	if(N%M == 0)cout << "yes " << (N/M);
	else {
		int i = M;
		for(; i <= N; i++){
			if(N%i == 0){
				cout << "no " << (i-M);
				return 0;
			}
		}
	}
	return 0;
}