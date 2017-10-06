#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N;
long long int a;
long long int b;

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		scanf("%lld", &a);
		scanf("%lld", &b);
		printf("%lld\n", (a+b));
	}
	return 0;
}