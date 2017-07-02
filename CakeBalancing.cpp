#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int L, R, W;
int leftVals[11];
int rightVals[11];
int memo[1<<10][1<<10][2];

int search(int l, int r, int lT, int rT, int f){
	if(abs(lT - rT) > W)return INFINITE;
	if(l == ((1<<L)-1) && r == ((1<<R)-1))return 0;
	if(memo[l][r][f] != -1)return memo[l][r][f];
	int* result = &memo[l][r][f];
	*result = INFINITE;
	for(int i = 0; i < L; i++){
		if(!(l & (1 << i))){
			int dist = search(l|(1 << i), r, lT - leftVals[i], rT, 0);
			if(dist == -1)continue;
			*result = min(dist+f, *result);
		}
	}
	for(int i = 0; i < R; i++){
		if(!(r & (1 << i))){
			int dist = search(l, r|(1 << i), lT, rT - rightVals[i], 1);
			if(dist == -1)continue;
			*result = min(dist+(1-f), *result);
		}
	}
	if(*result == INFINITE)*result = -1;
	return *result;
}

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i%i%i", &L, &R, &W);
	for(int i = 0; i < (1 << L); i++){
		for(int j = 0; j < (1 << R); j++){
			memo[i][j][0] = memo[i][j][1] = -1;
		}
	}
	int leftTotal = 0;
	for(int i = 0; i < L; i++){
		int x;
		scanf("%i", &x);
		leftVals[i] = x;
		leftTotal+=x;
	}
	int rightTotal = 0;
	for(int i = 0; i < R; i++){
		int x;
		scanf("%i", &x);
		rightVals[i] = x;
		rightTotal += x;
	}
	int val = min(search(0, 0, leftTotal, rightTotal, 0), search(0, 0, leftTotal, rightTotal, 1))+1;
	printf("%i", val);
	return 0;
}