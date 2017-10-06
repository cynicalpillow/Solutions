#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int G;
double eg, pg;
pair<double, double> vals[1005];

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i", &G);
	for(int i = 0; i < G; i++){
		scanf("%lf%lf", &eg, &pg);
		vals[i] = mp(eg, pg);
	}
	double val = 1;
	for(int i = 0; i < G; i++){
		val *= (1.0000000000000000-(vals[i].first/vals[i].second));
	}
	cout << val;
	return 0;
}