#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N;
int a[60];

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	int x;
	for(int i = 0; i < N; i++){
		scanf("%i", &x);
		a[i] = x;
	}
	sort(a, a+N);
	for(int i = 0; i < N; i++){
		printf("%i\n", a[i]);
	}
	return 0;
}