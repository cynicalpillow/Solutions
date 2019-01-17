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
map<int, vector<pair<int, int>>> products;
bool units[205];
vector<pair<int, int>> productsToI[205];
set<int> eachI[205];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	//unit
	cout << "Units:" << "\n";
	for(int i = 0; i <= N-1; i++){
		for(int j = 0; j <= N-1; j++){
			if((i*j)%N == 1){
				cout << i << "\n";
				units[i] = true;
			}
			productsToI[(i*j)%N].pb(mp(i, j));
			products[(i*j)%N].pb(mp(i, j));
			eachI[i].insert((i*j)%N);
		}
	}
	//Irreducibles
	cout << "Irreducibles:" << "\n";
	for(int i = 0; i <= N-1; i++){
		if(units[i] || i == 0)continue;
		bool check = true;
		for(pair<int, int> p : productsToI[i]){
			if(!units[p.first] && !units[p.second]){
				check = false;
				break;
			}
		}
		if(check)cout << i << "\n";
	}
	//Primes
	cout << "Primes:" << "\n";
	for(int i = 0; i <= N-1; i++){
		if(units[i] || i == 0)continue;
		int overCheck = true;
		for(int j = 0; j <= N-1; j++){
			for(pair<int, int> p : products[(i*j)%N]){
				bool check1 = false;
				bool check2 = false;
				if(eachI[i].count(p.first) > 0)check1 = true;
				if(eachI[i].count(p.second) > 0)check2 = true;
				if(!check1 && !check2){
					overCheck = false;
					break;
				}
			}
			if(!overCheck)break;
		}
		if(overCheck)cout << i << "\n";
	}
	return 0;
}