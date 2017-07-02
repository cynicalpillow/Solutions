#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N;
double vals[22][22];
double memo[1<<21];

bool v[1<<21];
double mx = -INFINITE;

double search(int x, int c){
	if(c == N+1)return 1.0;
	if(v[x])return memo[x];
	v[x] = true;
	double &ret = memo[x];
	ret = 0.0;
	for(int i = 0; i < N; i++){
		if((x & (1 << i))==0){
			double t = vals[c][i] * search((x|(1<<i)), c+1);
			if(t > ret)ret = t;
		}
	}
	return ret;
}

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		for(int j = 0; j < N; j++){
			double x;
			scanf("%lf", &x);
			vals[i][j] = x/100.0000000;
		}
	}
	mx = max(mx, search(0, 0));
	printf("%lf", (mx * 100.0));
	return 0;
}