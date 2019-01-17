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
int N;
vector<int> p;
vector<int> n;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	int pc = 0;
	int nc = 0;
	int zc = 0;
	for(int i = 0; i < N; i++){
		int x;
		cin >> x;
		if(x < 0){
			n.pb(x);
			nc++;
		} else if(x > 0){
			p.pb(x);
			pc++;
		} else {
			zc++;
		}
	}
	sort(p.begin(), p.end(), greater<int>());
	sort(n.begin(), n.end());
	int ans = 1;
	for(int i : p)
		ans *= i;
	for(int i = 0; i < n.size(); i+=2){
		if(i+1 >= n.size())break;
		else ans *= (n[i]*n[i+1]);
	}
	if(pc == 0 && nc < 2 && zc > 0)ans = 0;
	else if(pc == 0 && nc < 2)ans = n[N-1];
	cout << ans;
	return 0;
}