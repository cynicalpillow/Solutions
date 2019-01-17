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
int N, X;
int cactus[105];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> X;
	for(int i = 0; i < N; i++)
		cin >> cactus[i];
	sort(cactus, cactus+N);
	int count = 0;
	for(int i = 0; i < N-2; i++){
		for(int j = i+1; j < N-1; j++){
			for(int k = j+1; k < N; k++){
				if(cactus[j] == X)count++;
			}
		}
	}
	cout << count;
	return 0;
}