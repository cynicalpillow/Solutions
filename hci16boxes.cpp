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
int N, X, Y;
vector<pair<int, int>> boxes;
int dp[100005];
map<int, int, greater<int>> mpp;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		cin >> X >> Y;
		boxes.pb(mp(X, Y));
	}
	sort(boxes.begin(), boxes.end());
	for(int i = 0; i < N; i++){
		cout << boxes[i].first << " " << boxes[i].second << endl;
	}
	int ans = 1;
	dp[0] = 1;
	mpp.insert(mp(1, 0));
	for(int i = 1; i < N; i++){
		dp[i] = 1;
		for(auto const & item : mpp){
			if(boxes[item.second].first < boxes[i].first && boxes[item.second].second < boxes[i].second){
				cout << item.first << " " << i << endl;
				dp[i] = max(dp[i], item.first+1);
				break;
			}
		}
		cout << mpp.size() << endl;
		ans = max(ans, dp[i]);
		if(boxes[mpp[dp[i]]].first + boxes[mpp[dp[i]]].second >= boxes[i].first + boxes[i].second)mpp.insert(mp(dp[i], i));
	}
	cout << ans;
	return 0;
}