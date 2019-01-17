#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int dist, N;
int xx, yy;
string dir;
int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		cin >> dir;
		scanf("%i", &dist);
		if(dir == "North"){
			yy += dist;
		} else if(dir == "South"){
			yy -= dist;
		} else if(dir == "East"){
			xx += dist;
		} else {
			xx -= dist;
		}
	}
	cout << xx << " " << yy;
	return 0;
}