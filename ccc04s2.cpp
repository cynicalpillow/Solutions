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
int n, k, s;
struct yodel{
	int worstRank;
	int totalScore;
	int id;
};

vector<yodel> conts;
vector<int> sorting;

int compareYodel(int a, int b){
	if(conts[a].totalScore != conts[b].totalScore)
		return conts[a].totalScore > conts[b].totalScore;
	return conts[a].id < conts[b].id;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n >> k;
	for(int i = 0; i < n; i++){
		conts.pb({1, 0, i+1});
		sorting.pb(i);
	}
	for(int i = 0; i < k; i++){
		for(int j = 0; j < n; j++){
			cin >> s;
			conts[j].totalScore += s;
		}
		sort(sorting.begin(), sorting.end(), compareYodel);
		int minScore = INFINITE;
		int rank = 0;
		for(int j : sorting){
			if(conts[j].totalScore < minScore){
				minScore = conts[j].totalScore;
				rank++;
			}
			conts[j].worstRank = max(conts[j].worstRank, rank);
		}
		minScore = INFINITE;
		bool found = false;
		if(i == k-1){
			for(int j : sorting){
				if(minScore > conts[j].totalScore && !found){
					minScore = conts[j].totalScore;
					found = true;
				} else if(minScore > conts[j].totalScore){
					break;
				}
				cout << "Yodeller " << conts[j].id << " is the TopYodeller: score " << conts[j].totalScore << ", worst rank " << conts[j].worstRank << "\n";
			}
		}
	}
	return 0;
}