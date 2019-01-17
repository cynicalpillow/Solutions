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
int N, on, off;
pair<int, int> stations[100005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	int total_off = 0;
	for(int i = 0; i < N-1; i++){
		cin >> on >> off;
		stations[i] = mp(on, off);
		total_off += off;
	}
	int currently_on = stations[0].first;
	int not_first = 0;
	for(int i = 1; i < N-1; i++){
		int getting_off = stations[i].second;
		if(not_first - getting_off >= 0){
			int temp = not_first;
			not_first -= getting_off;
			getting_off -= temp;
		}
		if(getting_off > 0){
			int temp = currently_on;
			currently_on -= getting_off;
			getting_off -= temp;
		}
		not_first += stations[i].first;
	}
	cout << max(0, stations[0].first - total_off) << endl << currently_on;
	return 0;
}