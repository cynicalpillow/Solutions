#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, K, pi, ti, Q, T, P, M, pi1, ti1;
int your_team[200];
int other_team[200];

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
    memset(your_team, 0, sizeof(your_team));
    memset(other_team, 0, sizeof(other_team));
	freopen("input.txt", "r", stdin);
	scanf("%i%i%i%i%i%i", &N, &K, &Q, &P, &T, &M);
	int total = 0;
	int total_other = 0;
	for(int i = 0; i < N; i++){
		scanf("%i%i", &pi, &ti);
		your_team[ti] = pi;
		total += pi+(ceil((double)(T-ti)/(double)M));
	}
	for(int i = 0; i < K; i++){
		scanf("%i%i", &pi1, &ti1);
		other_team[ti1] = pi1;
		total_other += pi1+(ceil((double)(T-ti1)/(double)M));
	}
	if(total_other > total){
		cout << "bamboozled";
		return 0;
	}
	int total_score = 0;
	int other_score = 0;
	int count_other = 0;
	int count_you = 0;
	int rr = 0;
	for(int i = 0; i <= 100*T; i++){
		rr = i;
		if(total_score > other_score+((N-count_other)*P+(ceil((double)(T-i)/(double)M)*(N-count_other))))
			break;
		if(your_team[i] > 0){
			total_score += (your_team[i] + ceil((double)(T-i)/(double)M));
			count_you++;
		}
		if(other_team[i] > 0){
			other_score += (other_team[i] + ceil((double)(T-i)/(double)M));
			count_other++;
		}
	}
	if(rr > T)cout << "bamboozled";
	else cout << rr;
	return 0;
}