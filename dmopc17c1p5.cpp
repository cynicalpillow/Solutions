#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, Q;
int vals[1000005];

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &Q);
	for(int i = 0; i < N; i++){
		int x;
		scanf("%i", &x);
		vals[i] = x;
	}
	for(int j = 0; j < Q; j++){
		int l, r;
		scanf("%i%i", &l, &r);
		l--;
		r--;
		int max = -INFINITE;
		int count = 0;
		for(int i = l; i <= r; i++){
			if(vals[i] > max){
				max = vals[i];
				count++;
			}
		}
		cout << count << endl;
	}
	return 0;
}