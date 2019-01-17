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
long long sums[2000005];
string command;
int N, K, P, A, B;
long long L, Q;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	memset(sums, 0, sizeof(sums));
	for(int i = 0; i < N; i++){
		cin >> command;
		if(command == "ADD"){
			cin >> K >> P;
			sums[P]+=K;
		} else if(command == "ADDRANGE"){
			cin >> A >> B;
			for(int i = A; i <= B; i++)
				sums[i]++;
		} else if(command == "BUYAMT"){
			cin >> Q;
			long long totalCones = 0;
			for(int i = 0; i <= 2000004; i++){
				if(i == 0){
					totalCones += sums[i];
					sums[i] = 0;
				} else {
					long long conesAllowed = min(sums[i], Q/i);
					totalCones += conesAllowed;
					sums[i] -= conesAllowed;
					Q-=conesAllowed*i;
				}
			}
			cout << totalCones << "\n";
		} else if(command == "BUYLOW"){
			cin >> L;
			long long cost = 0;
			for(int i = 0; i <= 2000004; i++){
				if(L-sums[i] <= 0){
					long long curr = min(sums[i], L);
					cost += curr*i;
					sums[i] -= curr;
					L = 0;
					break;
				} else {
					cost += sums[i]*i;
					L-=sums[i];
					sums[i] = 0;
				}
			}
			cout << cost << "\n";
		} else if(command == "BUYHIGH"){
			cin >> L;
			long long cost = 0;
			for(int i = 2000004; i >= 0; i--){
				if(L-sums[i] <= 0){
					long long curr = min(sums[i], L);
					cost += curr*i;
					sums[i] -= curr;
					L = 0;
					break;
				} else {
					cost += sums[i]*i;
					L-=sums[i];
					sums[i] = 0;
				}
			}
			cout << cost << "\n";
		} else if(command == "COST"){
			cin >> L;
			long long cost = 0;
			for(int i = 0; i <= 2000004; i++){
				if(L-sums[i] <= 0){
					cost = i;
					L = 0;
					break;
				} else {
					L-=sums[i];
				}
			}
			if(L > 0)cout << -1 << "\n";
			else cout << cost << "\n";
		} else if(command == "NUMCONES"){
			long long totalCones = 0;
			for(int i = 0; i <= 2000004; i++){
				totalCones += sums[i];
			}
			cout << totalCones << "\n";
		} else {
			long long totalCones = 0;
			for(int i = 0; i <= 2000004; i++){
				totalCones += sums[i]*i;
			}
			cout << totalCones << "\n";
		}
	}
	return 0;
}