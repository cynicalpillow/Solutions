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
int a[3];
int b[3];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> a[0] >> a[1] >> a[2] >> b[0] >> b[1] >> b[2];
	sort(a, a+3);
	sort(b, b+3);
	int count = 0;
	for(int i = 0; i < 3; i++){
		if(a[i] <= b[i])count++;
	}
	if(count == 3)cout << "Y";
	else cout << "N";
	return 0;
}