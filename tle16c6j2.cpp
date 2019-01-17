#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, F;
int vals[1005];
vector<int> smallest;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &F, &N);
	for(int i = 0; i < F; i++){
		int x;
		scanf("%i", &x);
		vals[i] = x;
	}
	sort(vals, vals+F);
	long long result = 0;
	for(int i = 0; i < N; i++)
		smallest.pb(vals[i]);
	sort(smallest.begin(), smallest.end());
	int count = 1;
	long long z;
	for(int i = smallest.size()-1; i >= 0; i--){
		z = smallest[i];
		for(int j = 1; j < count; j++)
			z = (z*smallest[i])%1000000007;
		result += (z % 1000000007);
		count++;
	}
	cout << result % 1000000007;
	return 0;
}