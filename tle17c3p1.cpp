#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int x0, y0;
int xi, yi;
int N;
pair<int, int> points[100005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> x0 >> y0;
	cin >> N;
	for(int i = 0; i < N; i++){
		cin >> xi >> yi;
		points[i] = mp(xi, yi);
	}
	
	return 0;
}