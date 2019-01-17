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
int N, ren, x;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		if(i == 0){
			cin >> ren;
		} else {
			cin >> x;
			if(ren <= x){
				ren = -1;
			}
		}
	}
	if(ren == -1)cout << "NO";
	else cout << "YES";
	return 0;
}