#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N;
vector<tuple<int, int, int>> ct;
vector<tuple<int, int, int>> cp;
bool vs[200005];
int greatest = 0;

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		int a, b;
		scanf("%i%i", &a, &b);
		greatest = max(greatest, a);
		ct.pb(make_tuple(a, b, i));
		cp.pb(make_tuple(b, a, i));
	}
	sort(ct.begin(), ct.end());
	sort(cp.begin(), cp.end());
	int chosen = 0;
	int cost = 0;
	for(int i = 0; i < N; i++){
		if(chosen >= get<0>(ct[i])){
			vs[get<2>(ct[i])] = true;
			chosen++;
		} else {
			vector<tuple<int, int, int>>::iterator idx;
			for(idx = cp.begin(); idx != cp.end(); idx++){
				//cout << get<0>(*idx) << " " << (get<0>(ct[i]) == greatest) << endl;
				if(!vs[get<2>(*idx)] && ((get<1>(*idx) > get<0>(ct[i]))||(get<1>(*idx) == greatest))){
					vs[get<2>(*idx)] = true;
					cost += get<0>(*idx);
					chosen++;
					break;
				}
			}
			cp.erase(idx);
		}
	}
	cout << cost;
	return 0;
}