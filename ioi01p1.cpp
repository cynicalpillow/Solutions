#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int instruction, S, X, Y, A, L, B, R, T;
int BIT[1030][1030];

void update(int x, int y, int val){
	int i = y;
	while(i <= S){
		int j = x;
		while(j <= S){
			BIT[i][j] += val;
			j += j&-j;
		}
		i += i&-i;
	}
}
int sum(int x, int y){
	int i = y;
	int ret = 0;
	while(i > 0){
		int j = x;
		while(j > 0){
			ret += BIT[i][j];
			j -= j&-j;
		}
		i -= i&-i;
	}
	return ret;
}
int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &instruction, &S);
	for(int i = 0; i <= S; i++)
		for(int j = 0; j <= S; j++)
			BIT[i][j] = 0;
	while(true){
		scanf("%i", &instruction);
		if(instruction == 1){
			scanf("%i%i%i", &X, &Y, &A);
			update(X+1, Y+1, A);
		} else if(instruction == 2){
			scanf("%i%i%i%i", &L, &B, &R, &T); //(L, B) -> (R, T)
			cout << (sum(R+1, T+1) - sum(R+1, B) - sum(L, T+1) + sum(L, B)) << "\n";
		} else {
			return 0;
		}
	}
	return 0;
}