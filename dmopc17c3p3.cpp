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
int N, Si;
int vals[25];
int memo1[1<<21];
int memo2[1<<21];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		cin >> Si;
		vals[i] = Si;
	}
	for(int i = 1; i < (1<<N); i++){
		int total = 0;
		for(int j = 0; j < N; j++)
			if((i&(1<<j)))total += vals[j];
		memo1[i] = total;
	}
	for(int i = 1; i < (1<<N); i++){
		int total = 0;
		for(int j = 0; j < N; j++)
			if((i&(1<<j)))total += vals[j];
		memo2[i] = total;
	}
	int subset1 = 0;
	int subset2 = 0;
	int mindiff = INFINITE;
	for(int i = 1; i < (1<<N); i++){
		for(int j = 1; j < (1<<N); j++){
			if(i != j){
				bool check = true;
				for(int z = 0; z < N; z++){
					if((i&(1<<z)) && (j&(1<<z))){
						check = false;
						break;
					}
				}
				if(check){
					if(abs(memo1[i]-memo2[j]) < mindiff){
						mindiff = abs(memo1[i]-memo2[j]);
						subset1 = i;
						subset2 = j;
						break;
					}
				}
			}
		}
	}
	for(int i = 0; i < N; i++){
		if((subset1&(1<<i))){
			cout << (i+1) << " ";
		}
	}
	cout << endl;
	for(int i = 0; i < N; i++){
		if((subset2&(1<<i))){
			cout << (i+1) << " ";
		}
	}
	return 0;
}