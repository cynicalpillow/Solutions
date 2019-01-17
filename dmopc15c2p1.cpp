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
int N, K;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	//freopen("input.txt", "r", stdin);
	cin >> N >> K;
	int total = 0;
	int swords = 0;
	while(N > 0){
		N--;
		swords++;
		total++;
		if(swords == K){
			N++;
			swords = 0;
		}
	}
	cout << total << endl;
	return 0;
}