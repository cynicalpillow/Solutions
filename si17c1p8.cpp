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
int N, T;
int a[15];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> T;
	for(int i = 0; i < N; i++){
		cin >> a[i];
	}
	int count = 0;
	for(int i = 0; i < (1 << N); i++){
		int total = 0;
		for(int j = 0; j < N; j++){
			if((1 << j)&i){
				total += a[j];
			}
		}
		if(total == T)count++;
	}
	cout << count;
	return 0;
}