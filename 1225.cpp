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
int n;
long long place[46][2]; //0 == red, 1 == white

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n;
	for(int i = 0; i < n; i++){
		if(i == 0){
			place[i][1] = place[i][0] = 1;
		} else {
			//place a red
			place[i][0] = 0;
			place[i][0] += place[i-1][1];
			if(i-2 >= 0)place[i][0] += place[i-2][1];
			//place a white
			place[i][1] = 0;
			place[i][1] += place[i-1][0];
			if(i-2 >= 0)place[i][1] += place[i-2][0];
		}
	}
	cout << place[n-1][0]+place[n-1][1] << endl;
	return 0;
}