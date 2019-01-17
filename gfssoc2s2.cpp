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
int N, T, m;
pair<string, int> arr[11];
vector<pair<string, pair<string, string>>> tri;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> T;
	for(int i = 0; i < N; i++){
		string s;
		int c;
		cin >> s >> c;
		arr[i] = mp(s, c);
	}
	sort(arr, arr+N);
	for(int i = 0; i < N-2; i++){
		for(int j = i+1; j < N-1; j++){
			for(int k = j+1; k < N; k++){
				if(arr[i].second + arr[j].second+arr[k].second <= T){
					tri.pb(mp(arr[i].first, mp(arr[j].first, arr[k].first)));
				}
			}
		}
	}
	sort(tri.begin(), tri.end());
	for(pair<string, pair<string, string>> x : tri){
		cout << x.first << " " << x.second.first << " " << x.second.second << "\n";
	}
	return 0;
}