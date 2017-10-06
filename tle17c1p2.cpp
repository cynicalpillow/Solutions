#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int F, N, ei, dj;
string si, tj;
map<string, int> foodList;
vector<pair<int, string>> poss;
int m = 0;

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i", &F);
	for(int i = 0; i < F; i++){
		cin >> si;
		scanf("%i", &ei);
		foodList[si] = ei;
	}
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		cin >> tj;
		scanf("%i", &dj);
		poss.push_back(mp(dj, tj));
	}
	sort(poss.begin(), poss.end());
	int energy = 0;
	int currDist = 0;
	for(int i = 0; i < N; i++){
		if(currDist != poss[i].first && energy - (poss[i].first - currDist) < 0)break;
		if(currDist == poss[i].first){
			energy += foodList[poss[i].second];
		} else {
			energy = energy - (poss[i].first - currDist)+foodList[poss[i].second];
			currDist = poss[i].first;
		}
		//cout << energy << endl;
		m = i+1;
	}
	cout << m;
	return 0;
}