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
int N, d, v;
double lastDist;
double lastV;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	double totalTime = 0;
	for(int i = 0; i < N; i++){
		cin >> d >> v;
		if(i == 0){
			lastDist = d;
			lastV = v;
		} else {
			if(v > lastV){
				totalTime += (lastDist-d)/lastV;
				lastDist = d;
				lastV = v;
			}
		}
	}
	totalTime += lastDist/lastV;
	printf("%.5lf", totalTime);
	return 0;
}