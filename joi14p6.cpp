#include <bits/stdc++.h>
#include "secret.h"
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int tree[1005][1005];

void build(int l, int r){
	if(r - l <= 0)return;
	int m = (l+r)/2;
	for(int i = m-1; i >= l; i--){
		if(tree[i][m] == -1){
			tree[i][m] = Secret(tree[i][i+1], tree[i+1][m]); //Update left subtrees (From i+1 to m and from i+1 to i current)
		}
	}
	for(int i = m+1; i <= r; i++){
		if(tree[m][i] == -1){
			tree[m][i] = Secret(tree[m][i-1], tree[i-1][i]); //Update right subtrees (From i-1 to current i, and from i-1 to the mid point)
		}
	}
	build(l, m);
	build(m+1, r);
}

void Init(int N, int A[]){
	for(int i = 0; i <= N; i++){
		for(int j = 0; j <= N; j++){
			tree[i][j] = -1;
		}
	}
	for(int i = 0; i < N; i++){
		tree[i][i+1] = A[i];
	}
	build(0, N);
}

int Query(int L, int R){
	for(int i = L+1; i <= R; i++){
		if(tree[L][i] != -1 && tree[i][R+1] != -1){
			return Secret(tree[L][i], tree[i][R+1]);
		}
	}
	return tree[L][R+1];
}
