#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, Q, a, b, q;
int BIT[100005];
int original[100005];
vector<pair<int, pair<int, pair<int, int>>>> queries;
vector<int> mpp[20005];

void update(int X, int C){
	while(X <= N){
		BIT[X] += C;
		X += (X&-X);
	}
}

int sum(int X){
	int ret = 0;
	while(X > 0){
		ret += BIT[X];
		X-=(X&-X);
	}
	return ret;
}

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 1; i <= N; i++){
		int x;
		scanf("%i", &x);
		mpp[x].pb(i);
	}
	scanf("%i", &Q);
	for(int i = 0; i < Q; i++){
		scanf("%i%i%i", &a, &b, &q);
		a++;
		b++;
		pair<int, pair<int, pair<int, int>>> pi = mp(q, mp(a, mp(b, i)));
		queries.pb(pi);
	}		
	sort(queries.rbegin(), queries.rend());
	int qC = 0;
	for(int j = 20001; j >= 0; j--){
		for(int z : mpp[j])
			update(z, j);
		while(qC < Q && queries[qC].first >= j){
			original[queries[qC].second.second.second] = sum(queries[qC].second.second.first) - sum(queries[qC].second.first-1);
			qC++;
		}
		if(qC >= Q)break;
	}
	for(int i = 0; i < Q; i++)
		cout << original[i] << "\n";
	return 0;
}