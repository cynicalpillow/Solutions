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
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	for(int j = 0; j < 5; j++){
		scanf("%i%i", &N, &M);
		int i = 0;
		for(; (1 << (i+1)) <= min(N, M); i++){}
		int leftOverN = N%(1 << i);
		int leftOverM = M%(1 << i);
		int total = 0;
		total += (N/(1<<i))*(M/(1<<i));
		cout << total << endl;
		while(true){
			int z = 0;
			for(; (1 << (z+1)) <= min(N-leftOverN, leftOverM); z++){}
			total += ((N-leftOverN)/(1<<z))*(leftOverM/(1<<z));
			leftOverM %= (1<<z);
			if(leftOverM == 0)break;
		}
		cout << total << endl;
		while(true){
			int z = 0;
			for(; (1 << (z+1)) <= min(M, leftOverN); z++){}
			total += (M/(1<<z))*(leftOverN/(1<<z));
			M %= (1<<z);
			if(M == 0)break;
		}
		cout << total << endl;
	}
	return 0;
}