#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 1000000005;
int N, D;
int dists[100005];
vector<pair<int, int>> going_up;
vector<pair<int, int>> going_down;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> D;
	for(int i = 1; i <= N; i++){
		int x;
		cin >> x;
		dists[i] = x;
	}
	int lowestGoingUp = INFINITE;
	int lowestGoingDown = INFINITE;
	int highestGoingDown = -INFINITE;
	int highestGoingUp = -INFINITE;
	int lowest = INFINITE;
	int highest = -INFINITE;
	bool goingDownExists = false;
	bool goingUpExists = false;
	for(int j = 0; j < D; j++){
		int x, y;
		cin >> x >> y;
		if(dists[y] > dists[x])lowestGoingUp = min(lowestGoingUp, min(dists[y], dists[x]));
		if(dists[y] > dists[x])highestGoingUp = max(highestGoingUp, max(dists[y], dists[x]));
		if(dists[y] < dists[x])lowestGoingDown = min(lowestGoingDown, min(dists[y], dists[x]));
		if(dists[y] < dists[x])highestGoingDown = max(highestGoingDown, max(dists[y], dists[x]));
		highest = max(highest, max(dists[y], dists[x]));
		lowest = min(lowest, min(dists[y], dists[x]));
		if(dists[y] > dists[x])goingUpExists = true;
		if(dists[y] < dists[x])goingDownExists = true;
	}
	long long up = abs(highest - lowestGoingUp)+(goingDownExists?abs(highest-lowestGoingDown):0);
	long long down = abs(highestGoingDown - lowest)+(goingUpExists?abs(lowest - highestGoingUp):0);
	cout << min(up, down) << endl;
	return 0;
}