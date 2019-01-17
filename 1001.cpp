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
vector<double> outputs;
long long x;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	while(cin >> x){
		outputs.pb(sqrt(x));
	}
	for(int i = outputs.size()-1; i >= 0; i--){
		printf("%.4f\n", outputs[i]);
	}
	return 0;
}