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
int N, M, Di, Qi;
int costs[15];
struct deal{
	int cost;
	int quantities[15];
};
vector<deal> deals;
int must_buy[15];
int use[15];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> M;
	for(int i = 1; i <= N; i++){
		cin >> costs[i];
	}
	for(int i = 0; i < M; i++){
		cin >> Di;
		deal d;
		d.cost = Di;
		for(int j = 1; j <= N; j++){
			cin >> Qi;
			d.quantities[j] = Qi;
		}
		deals.pb(d);
	}
	for(int i = 1; i <= N; i++){
		cin >> must_buy[i];
	}
	int min_cost = INFINITE;
	//Find subset of deals to use
	for(int i = 0; i < (1 << M); i++){
		memset(use, 0, sizeof(use));
		bool usable = true;
		int total_cost = 0;
		for(int j = 0; j < M; j++){
			if(i&(1<<j)){
				//Use this deal
				for(int z = 1; z <= N; z++){
					if(use[z] + deals[j].quantities[z] > must_buy[z]){
						usable = false;
						break;
					}
					use[z] += deals[j].quantities[z];
				}
				if(!usable)break;
				total_cost += deals[j].cost;
			}
		}
		if(usable){
			for(int j = 1; j <= N; j++){
				total_cost += (must_buy[j]-use[j])*costs[j];
			}
			min_cost = min(min_cost, total_cost);
		}
	}
	cout << min_cost;
	return 0;
}