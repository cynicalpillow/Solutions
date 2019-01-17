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
int n, ai;
int secs[1005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n;
	for(int i = 0; i < n; i++){
		cin >> ai;
		secs[i] = ai;
	}
	int cmax = 0;
	int cmid = 0;
	for(int i = 0; i < n-2; i++){
		if(secs[i] + secs[i+1] + secs[i+2] > cmax){
			cmax = secs[i] + secs[i+1] + secs[i+2];
			cmid = i+2;
		}
	}
	cout << cmax << " " << cmid;
	return 0;
}