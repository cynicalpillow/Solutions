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
vector<pair<double, double>> sheeps;
pair<double, double> possible_sheep[105];
set<pair<double, double>> ans;

int main(){
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		double x, y;
		cin >> x >> y;
		sheeps.pb(mp(x, y));
	}
	for(int i = 0; i <= 100000; i++){
		double currPos = i/100.;
		double minDistance = -1;
		int counter = 0;
		for(pair<double, double> x : sheeps){
			possible_sheep[counter] = mp(sqrt((x.first-currPos)*(x.first-currPos)+(x.second)*(x.second)), counter);
			counter++;
		}
		sort(possible_sheep, possible_sheep+N);
		for(pair<double, double> x : possible_sheep){
			if(minDistance == -1)minDistance = x.first;
			else if(minDistance < x.first)break;
			ans.insert(sheeps[x.second]);
		}
	}
	for(pair<double, double> x : ans){
		cout << "The sheep at (";
		printf("%.2f", x.first);
		cout << ", ";
		printf("%.2f", x.second);
		cout << ") might be eaten.\n";
	}
	return 0;
}