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
int vals[1005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++)
		cin >> vals[i];
	int count = 0;
	for(int i = 0; i < N; i++){
		for(int j = 1; j < N; j++){
			if(vals[j-1] < vals[j]){
				int temp = vals[j-1];
				vals[j-1] = vals[j];
				vals[j] = temp;
				count++;
			}
		}
	}
	cout << count;
	return 0;
}