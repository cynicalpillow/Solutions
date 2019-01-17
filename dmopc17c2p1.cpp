#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, vi, ci;
vector<pair<int, int>> items;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		scanf("%i%i", &ci, &vi);
		items.pb(mp(ci, vi));
	}
	long long totalC = 0;
	long long totalV = 0;
	for(pair<int, int> p : items){
		if(totalV + p.second > totalV){
			totalV += p.second;
			totalC += p.first;
		}
	}
	cout << totalC;
	return 0;
}