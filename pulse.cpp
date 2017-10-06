#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, S, T;

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i%i%i", &N, &S, &T);
	int c = N;
	for(int i = 0; i < N; i++){
		int a;
		scanf("%i", &a);
		if(2*a < S || 2*a > T)c--;
	}
	cout << c;
	return 0;
}