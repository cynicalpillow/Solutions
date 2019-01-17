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

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n;
	int currCount = 0;
	int prev = -1;
	for(int i = 0; i < n; i++){
		int x;
		cin >> x;
		if(prev != -1 && prev != x){
			cout << currCount << " " << prev << " ";
			currCount = 1;
		} else {
			currCount++;
		}
		prev = x;
	}
	cout << currCount << " " << prev;
	return 0;
}